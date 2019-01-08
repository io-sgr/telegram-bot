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
public class SetGameScorePayload {

    private final long userId;
    private final long score;
    private final Boolean force;
    private final Boolean disableEditMessage;
    private final Long chatId;
    private final Long messageId;
    private final String inlineMessageId;

    /**
     * @param userId             User identifier.
     * @param score              New score, must be non-negative.
     * @param force              Optional. Pass True, if the high score is allowed to decrease. This can be useful when
     *                           fixing mistakes or banning cheaters.
     * @param disableEditMessage Optional. Pass True, if the game message should not be automatically edited to include
     *                           the current scoreboard.
     * @param chatId             Required if inline_message_id is not specified. Unique identifier for the target chat.
     * @param messageId          Required if inline_message_id is not specified. Identifier of the sent message.
     */
    public SetGameScorePayload(long userId, long score, Boolean force, Boolean disableEditMessage, Long chatId, Long messageId) {
        this(userId, score, force, disableEditMessage, chatId, messageId, null);
    }

    /**
     * @param userId             User identifier.
     * @param score              New score, must be non-negative.
     * @param force              Optional. Pass True, if the high score is allowed to decrease. This can be useful when
     *                           fixing mistakes or banning cheaters.
     * @param disableEditMessage Optional. Pass True, if the game message should not be automatically edited to include
     *                           the current scoreboard.
     * @param inlineMessageId    Required if chat_id and message_id are not specified. Identifier of the inline
     *                           message.
     */
    public SetGameScorePayload(long userId, long score, Boolean force, Boolean disableEditMessage, String inlineMessageId) {
        this(userId, score, force, disableEditMessage, null, null, inlineMessageId);
    }

    private SetGameScorePayload(long userId, long score, Boolean force, Boolean disableEditMessage, Long chatId, Long messageId, String inlineMessageId) {
        this.userId = userId;
        if (score < 0) {
            throw new IllegalArgumentException(String.format("Score should be greater than or equal to zero, but got %d", score));
        }
        this.score = score;
        this.force = force;
        this.disableEditMessage = disableEditMessage;
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

    @JsonProperty("score")
    public long getScore() {
        return this.score;
    }

    @JsonProperty("force")
    public Boolean getForce() {
        return this.force;
    }

    @JsonProperty("disable_edit_message")
    public Boolean getDisableEditMessage() {
        return this.disableEditMessage;
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
