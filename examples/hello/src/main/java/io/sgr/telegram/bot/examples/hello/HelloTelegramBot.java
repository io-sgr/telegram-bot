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
import io.sgr.telegram.bot.api.models.http.ApiErrorResponse;
import io.sgr.telegram.bot.api.models.http.SendMessagePayload;
import io.sgr.telegram.bot.engine.BotEngine;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class HelloTelegramBot {

    public static void main(String... args) {
        final String botApiToken = System.getenv("BOT_API_TOKEN");
        final BotApi botApi = new BotApiBuilder().build();
        final BotEngine engine = new BotEngine(botApi, botApiToken, (Update update) -> {
            if (update == null) {
                return false;
            }
            if (update.getMessage() == null) {
                // Not what we want, but still mark as handled.
                return true;
            }
            try {
                final SendMessagePayload payload = new SendMessagePayload(update.getMessage().getChat().getId(), "Hello Telegram!");
                botApi.sendMessage(botApiToken, payload).get();
            } catch (ExecutionException e) {
                if (e.getCause() instanceof ApiCallException) {
                    Optional<ApiErrorResponse> error = ((ApiCallException) e.getCause()).getErrorResponse();
                    error.ifPresent(apiErrorResponse -> System.err.println(apiErrorResponse.getDescription()));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        });
        engine.start();
    }

}
