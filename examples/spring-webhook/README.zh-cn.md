# 示例：基于 Spring Boot 的 Telegram 机器人(Webhook)

*用其他语言阅读: [English](README.md), [简体中文](README.zh-cn.md).*

这里我们展示了如何构建一个简单的机器人，它会在收到消息之后回复："Hello Telegram！"。

本质上它是使用一个[Controller](src/main/java/io/sgr/telegram/bot/examples/spring/webhook/UpdateController.java)类监听来自 Telegram 服务器的消息并做出相应，非常简单。
