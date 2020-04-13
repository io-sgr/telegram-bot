# Telegram Bot Library
[![Build Status](https://travis-ci.org/io-sgr/telegram-bot.svg?branch=master)](https://travis-ci.org/io-sgr/telegram-bot) [![codecov](https://codecov.io/gh/io-sgr/telegram-bot/branch/master/graph/badge.svg)](https://codecov.io/gh/io-sgr/telegram-bot)

非常简单易用的 Telegram Bot 工具库。

*用其他语言阅读: [English](README.md), [简体中文](README.zh-cn.md).*

##如何使用
在你项目的 `pom.xml` 加入如下依赖：
```xml
<dependency>
    <groupId>io.sgr.telegram</groupId>
    <artifactId>telegram-bot.engine</artifactId>
    <version>1.1.1</version>
</dependency>
```
如果你使用 Gradle 来构建你的应用，引入依赖的方法是类似的，你应该知道该怎么做。

##示例程序
这里有几个例子：
* [基于 Java 命令行的 Telegram 机器人](examples/hello/README.zh-cn.md)。
* [基于 Spring Boot 的 Telegram 机器人(Webhook)](examples/spring-webhook/README.zh-cn.md)。
* [基于 Spring Boot 的 Telegram 机器人(CLI)](examples/spring-cli/README.zh-cn.md)。

## 许可协议

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
