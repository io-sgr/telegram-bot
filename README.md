# Telegram Bot Library
[![Build Status](https://travis-ci.org/io-sgr/telegram-bot.svg?branch=master)](https://travis-ci.org/io-sgr/telegram-bot) [![codecov](https://codecov.io/gh/io-sgr/telegram-bot/branch/master/graph/badge.svg)](https://codecov.io/gh/io-sgr/telegram-bot)

A java library which help you build Telegram Bots in a fashion way.

*Read this in other languages: [English](README.md), [简体中文](README.zh-cn.md).*

## CUT THE CRAP AND SHOW ME THE CODE!!!
[HelloTelegramBot.java](examples/hello/src/main/java/io/sgr/telegram/bot/examples/hello/HelloTelegramBot.java)
```java
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
```

# License

    Copyright 2017-2019 SgrAlpha
   
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
   
       http://www.apache.org/licenses/LICENSE-2.0
   
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
