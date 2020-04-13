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

package io.sgr.telegram.bot.examples.spring.cli;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;

import io.sgr.telegram.bot.api.BotApi;
import io.sgr.telegram.bot.api.exceptions.ApiCallException;
import io.sgr.telegram.bot.api.models.Update;
import io.sgr.telegram.bot.api.models.http.ApiErrorResponse;
import io.sgr.telegram.bot.api.models.http.SendMessagePayload;
import io.sgr.telegram.bot.engine.BotEngine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Nonnull;

@SpringBootApplication
public class BotApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(BotApplication.class);

    private final BotApi botApi;

    @Autowired
    public BotApplication(@Value("${bot.apiToken}") @Nonnull final String botApiToken) {
        checkArgument(!isNullOrEmpty(botApiToken), "Missing API token for bot!");
        this.botApi = BotApi.newBuilder(botApiToken).setLogger(LOGGER).build();
    }

    public static void main(String... args) {
        SpringApplication.run(BotApplication.class, args);
    }

    @Override
    public void run(String... args) {
        final BotEngine engine = new BotEngine(botApi).setBotUpdateProcessor((Update update) -> {
            if (update.getMessage() == null) {
                // Not what we want, ignored, but still send a success signal so it can deal with the next update.
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
        });
        engine.start();
    }

}
