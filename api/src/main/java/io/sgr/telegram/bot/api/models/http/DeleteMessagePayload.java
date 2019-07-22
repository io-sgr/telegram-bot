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
 *
 */

package io.sgr.telegram.bot.api.models.http;

import static io.sgr.telegram.bot.api.utils.Preconditions.notEmptyString;
import static io.sgr.telegram.bot.api.utils.Preconditions.notNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author SgrAlpha
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeleteMessagePayload {

    private final String chatId;
    private final Long messageId;

    /**
     * @param chatId         Unique identifier for the target chat or username of the target channel (in the
     *                       format @channelusername)
     * @param messageId      Identifier of the message to delete
     */
    public DeleteMessagePayload(String chatId, Long messageId) {
        notEmptyString(chatId, "Chat ID should be provided");
        this.chatId = chatId;
        notNull(messageId, "Message ID should be provided");
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
     * @return the messageId
     */
    @JsonProperty("message_id")
    public Long getMessageId() {
        return this.messageId;
    }

}
