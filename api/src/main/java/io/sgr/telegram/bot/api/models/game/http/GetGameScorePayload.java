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
package io.sgr.telegram.bot.api.models.game.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.sgr.telegram.bot.api.utils.Preconditions;

/**
 * @author SgrAlpha
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetGameScorePayload {

    private final long userId;
    private final Long chatId;
    private final Long messageId;
    private final String inlineMessageId;

    /**
     * @param userId    User identifier.
     * @param chatId    Required if inline_message_id is not specified. Unique identifier for the target chat.
     * @param messageId Required if inline_message_id is not specified. Identifier of the sent message.
     */
    public GetGameScorePayload(long userId, Long chatId, Long messageId) {
        this(userId, chatId, messageId, null);
    }

    /**
     * @param userId          User identifier.
     * @param inlineMessageId Required if chat_id and message_id are not specified. Identifier of the inline message.
     */
    public GetGameScorePayload(long userId, String inlineMessageId) {
        this(userId, null, null, inlineMessageId);
    }

    private GetGameScorePayload(long userId, Long chatId, Long messageId, String inlineMessageId) {
        this.userId = userId;
        if (Preconditions.isEmptyString(inlineMessageId)) {
            Preconditions.notNull(chatId, "Chat ID should be provided");
            this.chatId = chatId;
            Preconditions.notNull(messageId, "Message ID should be provided");
            this.messageId = messageId;
            this.inlineMessageId = null;
        } else {
            this.chatId = null;
            this.messageId = null;
            this.inlineMessageId = inlineMessageId;
        }
    }

    @JsonProperty("user_id")
    public long getUserId() {
        return this.userId;
    }

    @JsonProperty("chat_id")
    public Long getChatId() {
        return this.chatId;
    }

    @JsonProperty("message_id")
    public Long getMessageId() {
        return this.messageId;
    }

    @JsonProperty("inline_message_id")
    public String getInlineMessageId() {
        return this.inlineMessageId;
    }

}
