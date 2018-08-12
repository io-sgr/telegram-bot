# Telegram Bot Library
[![Build Status](https://travis-ci.org/sgr-io/telegram-bot.svg?branch=master)](https://travis-ci.org/sgr-io/telegram-bot) [![codecov](https://codecov.io/gh/sgr-io/telegram-bot/branch/master/graph/badge.svg)](https://codecov.io/gh/sgr-io/telegram-bot)

非常简单易用的 Telegram Bot 工具库。

*用其他语言阅读: [English](README.md), [简体中文](README.zh-cn.md).*

## 别废话，上 code ！
```java
public class HelloTelegramBot {

    public static void main(String... args) {
        final String botApiToken = System.getenv("BOT_API_TOKEN");
        final BotApiClient botApiClient = BotApiClient.newBuilder().setSkipRetry(true).build();
        BotEngine engine = new BotEngine(botApiClient, botApiToken, (Update update) -> {
            if (update == null) {
                return false;
            }
            if (update.getMessage() == null) {
                // Not what we want, but still mark as handled.
                return true;
            }
            try {
                SendMessagePayload payload = new SendMessagePayload(update.getMessage().getChat().getId(), "Hello Telegram!");
                botApiClient.sendMessage(botApiToken, payload);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        });
        engine.start();
    }

}
```

# 许可协议

    Copyright 2017-2018 SgrAlpha
   
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
   
       http://www.apache.org/licenses/LICENSE-2.0
   
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
