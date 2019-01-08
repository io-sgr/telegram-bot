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
package io.sgr.telegram.bot.api.models.http;

import static io.sgr.telegram.bot.api.utils.Preconditions.notEmptyString;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author SgrAlpha
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ForwardMessagePayload {

    private final String chatId;
    private final String fromChatId;
    private final Boolean disableNotification;
    private final long messageId;

    /**
     * @param chatId              Unique identifier for the target chat or username of the target channel (in the
     *                            format @channelusername)
     * @param fromChatId          Unique identifier for the chat where the original message was sent (or channel
     *                            username in the format @channelusername).
     * @param disableNotification Optional. Sends the message silently. iOS users will not receive a notification,
     *                            Android users will receive a notification with no sound.
     * @param messageId           Optional. Message identifier in the chat specified in from_chat_id.
     */
    public ForwardMessagePayload(String chatId, String fromChatId, Boolean disableNotification, long messageId) {
        notEmptyString(chatId, "Chat ID should be provided");
        this.chatId = chatId;
        notEmptyString(fromChatId, "Content of the message should be provided");
        this.fromChatId = fromChatId;
        this.disableNotification = disableNotification;
        this.messageId = messageId;
    }

    /**
     * @return the chatId
     */
    @JsonProperty("chat_id")
    public String getChatId() {
        return this.chatId;
    }

    /**
     * @return the fromChatId
     */
    @JsonProperty("from_chat_id")
    public String getFromChatId() {
        return this.fromChatId;
    }

    /**
     * @return the disableNotification
     */
    @JsonProperty("disable_notification")
    public Boolean getDisableNotification() {
        return this.disableNotification;
    }

    /**
     * @return the messageId
     */
    @JsonProperty("message_id")
    public long getMessageId() {
        return this.messageId;
    }

}
