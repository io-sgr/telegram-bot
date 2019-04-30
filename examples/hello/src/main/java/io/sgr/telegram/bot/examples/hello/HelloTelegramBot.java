/*
 * Copyright 2017-2019 SgrAlpha
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.sgr.telegram.bot.examples.hello;

import io.sgr.telegram.bot.api.BotApi;
import io.sgr.telegram.bot.api.BotApiBuilder;
import io.sgr.telegram.bot.api.exceptions.ApiCallException;
import io.sgr.telegram.bot.api.models.Update;
import io.sgr.telegram.bot.api.models.http.SendMessagePayload;
import io.sgr.telegram.bot.engine.BotEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;

/**
 * @author SgrAlpha
 */
public class HelloTelegramBot {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloTelegramBot.class);

    public static void main(String... args) {
        final String botApiToken = System.getenv("BOT_API_TOKEN");
        final BotApi botApi = new BotApiBuilder(botApiToken).setLogger(LOGGER).build();
        final BotEngine engine = new BotEngine(botApi).setBotUpdateProcessor((Update update) -> {
            if (update.getMessage() == null) {
                // Not what we want, but still mark as handled.
                return true;
            }
            try {
                final SendMessagePayload payload = new SendMessagePayload(update.getMessage().getChat().getId(), "Hello Telegram!");
                botApi.sendMessage(payload).get();
            } catch (ExecutionException e) {
                if (e.getCause() instanceof ApiCallException) {
                    ((ApiCallException) e.getCause())
                            .getErrorResponse()
                            .ifPresent(apiErrorResponse -> LOGGER.error(apiErrorResponse.getDescription().orElse("Unknown error!")));
                }
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage(), e);
            }
            return true;
        });
        engine.start();
    }

}
