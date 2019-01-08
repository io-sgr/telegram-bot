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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.sgr.telegram.bot.api.models.markups.InlineKeyboardMarkup;
import io.sgr.telegram.bot.api.utils.JsonUtil;

/**
 * @author SgrAlpha
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SendGamePayload {

    private final String chatId;
    private final String gameShortName;
    private final Boolean disableNotification;
    private final Long replyToMessageId;
    private final InlineKeyboardMarkup replyMarkup;

    /**
     * @param chatId              Unique identifier for the target chat.
     * @param gameShortName       Short name of the game, serves as the unique identifier for the game. Set up your
     *                            games via Botfather.
     * @param disableNotification Optional. Sends the message silently. Users will receive a notification with no
     *                            sound.
     * @param replyToMessageId    Optional. If the message is a reply, ID of the original message.
     * @param replyMarkup         A JSON-serialized object for an inline keyboard. If empty, one ‘Play game_title’
     *                            button will be shown. If not empty, the first button must launch the game.
     */
    public SendGamePayload(String chatId, String gameShortName, Boolean disableNotification, Long replyToMessageId, InlineKeyboardMarkup replyMarkup) {
        this.chatId = chatId;
        this.gameShortName = gameShortName;
        this.disableNotification = disableNotification;
        this.replyToMessageId = replyToMessageId;
        this.replyMarkup = replyMarkup;
    }

    @JsonProperty("chat_id")
    public String getChatId() {
        return this.chatId;
    }

    @JsonProperty("game_short_name")
    public String getGameShortName() {
        return this.gameShortName;
    }

    @JsonProperty("disable_notification")
    public Boolean getDisableNotification() {
        return this.disableNotification;
    }

    @JsonProperty("reply_to_message_id")
    public Long getReplyToMessageId() {
        return this.replyToMessageId;
    }

    @JsonProperty("reply_markup")
    public String getReplyMarkupJson() throws JsonProcessingException {
        if (this.replyMarkup == null) {
            return null;
        }
        return JsonUtil.getObjectMapper().writeValueAsString(this.replyMarkup);
    }

    @JsonIgnore
    public InlineKeyboardMarkup getReplyMarkup() {
        return this.replyMarkup;
    }

}
