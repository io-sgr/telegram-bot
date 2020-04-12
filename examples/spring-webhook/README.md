# Example: Spring Boot Based Telegram Bot (Webhook)

*Read this in other languages: [English](README.md), [简体中文](README.zh-cn.md).*

This is a simple demonstration about how to build an echo bot which will response "Hello Telegram!" every time when message received.

Basically it uses a [Controller](src/main/java/io/sgr/telegram/bot/examples/spring/webhook/UpdateController.java) class to listen to updates from Telegram server, it's very easy to understand.
