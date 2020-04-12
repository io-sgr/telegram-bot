/*
 * Copyright 2017-2020 SgrAlpha
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package io.sgr.telegram.bot.examples.spring.webhook;

import static java.util.Objects.isNull;

import io.sgr.telegram.bot.api.BotApi;
import io.sgr.telegram.bot.api.exceptions.ApiCallException;
import io.sgr.telegram.bot.api.models.Update;
import io.sgr.telegram.bot.api.models.http.ApiErrorResponse;
import io.sgr.telegram.bot.api.models.http.SendMessagePayload;
import io.sgr.telegram.bot.engine.BotUpdateProcessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Nonnull;

@RestController
public class UpdateController implements BotUpdateProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateController.class);

    private final String botApiToken;
    private final BotApi botApi;

    public UpdateController(@Value("${bot.apiToken}") final String botApiToken) {
        this.botApiToken = botApiToken;
        this.botApi = BotApi.newBuilder(botApiToken).setLogger(LOGGER).build();
    }

    @PostMapping(path = "/update/{apiToken}")
    @ResponseBody
    public ResponseEntity<?> receiveUpdate(@PathVariable("apiToken") final String botAndApiToken, @RequestBody final Update update) {
        if (!this.botApiToken.equalsIgnoreCase(botAndApiToken)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (handleUpdate(update)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public boolean handleUpdate(@Nonnull final Update update) {
        LOGGER.info(update.toJson());
        if (isNull(update.getMessage())) {
            return true;
        }
        final SendMessagePayload payload = new SendMessagePayload(update.getMessage().getChat().getId(), "Hello Telegram!");
        botApi.sendMessage(payload) // Send response message asynchronously without blocking next incoming update.
                .exceptionally(e -> {
                    // Something wrong when sending message, you might want to at least log it.
                    final Throwable cause = e.getCause();
                    if (cause instanceof ApiCallException) {
                        final String message = ((ApiCallException) cause).getErrorResponse()
                                .flatMap(ApiErrorResponse::getDescription)
                                .orElse(cause.getMessage());
                        LOGGER.error(message, e);
                    } else {
                        LOGGER.error(e.getMessage(), e);
                    }
                    return null;    // Return null because no message been sent.
                })
                .thenAccept(message -> {
                    // Nullable. Do anything you want with sent message here, or ignore it directly.
                });
        return true;
    }
}
