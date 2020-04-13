# Telegram Bot Library
[![Build Status](https://travis-ci.org/io-sgr/telegram-bot.svg?branch=master)](https://travis-ci.org/io-sgr/telegram-bot) [![codecov](https://codecov.io/gh/io-sgr/telegram-bot/branch/master/graph/badge.svg)](https://codecov.io/gh/io-sgr/telegram-bot)

A java library which help you build Telegram Bots in a fashion way.

*Read this in other languages: [English](README.md), [简体中文](README.zh-cn.md).*

## How to Use
Include following dependency in your `pom.xml`
```xml
<dependency>
    <groupId>io.sgr.telegram</groupId>
    <artifactId>telegram-bot.engine</artifactId>
    <version>1.1.1</version>
</dependency>
```
Gradle is similar, I'm pretty sure you will know what to do.

## Examples
We have several examples:
* [Java Application Based Telegram Bot](examples/hello/README.md).
* [Spring Boot Based Telegram Bot (Webhook)](examples/spring-webhook/README.md).
* [Spring Boot Based Telegram Bot (CLI)](examples/spring-cli/README.md).

## License

    Copyright 2017-2020 SgrAlpha
   
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
   
       http://www.apache.org/licenses/LICENSE-2.0
   
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
