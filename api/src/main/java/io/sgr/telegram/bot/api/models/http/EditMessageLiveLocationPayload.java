/*
 * Copyright 2017-2020 SgrAlpha
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package io.sgr.telegram.bot.api.models.http;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;
import static java.util.Objects.nonNull;

import io.sgr.telegram.bot.api.models.markups.InlineKeyboardMarkup;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author SgrAlpha
 */
public class EditMessageLiveLocationPayload {

    private final String chatId;
    private final Long messageId;
    private final String inlineMessageId;
    private final float latitude;
    private final float longitude;
    private final InlineKeyboardMarkup replyMarkup;

    /**
     * @param chatId          Optional. Required if inline_message_id is not specified. Unique identifier for the target
     *                        chat or username of the target channel (in the format @channelusername).
     * @param messageId       Optional. Required if inline_message_id is not specified. Identifier of the sent message.
     * @param inlineMessageId Optional. Required if chat_id and message_id are not specified. Identifier of the inline
     *                        message.
     * @param latitude        Latitude of new location.
     * @param longitude       Longitude of new location.
     * @param replyMarkup     Optional. A JSON-serialized object for an inline keyboard.
     */
    public EditMessageLiveLocationPayload(
            final String chatId, final Long messageId, final String inlineMessageId,
            final float latitude, final float longitude,
            final InlineKeyboardMarkup replyMarkup) {
        if (isNullOrEmpty(inlineMessageId)) {
            checkArgument(!isNullOrEmpty(chatId), "Chat ID is required if inline_message_id is not specified");
            this.chatId = chatId;
            checkArgument(nonNull(messageId), "Message ID is required if inline_message_id is not specified");
            this.messageId = messageId;
            this.inlineMessageId = null;
        } else {
            this.chatId = null;
            this.messageId = null;
            this.inlineMessageId = inlineMessageId;
        }
        checkArgument(Math.abs(latitude) <= 90, String.format("Invalid latitude: %f", latitude));
        this.latitude = latitude;
        checkArgument(Math.abs(longitude) <= 180, String.format("Invalid longitude: %f", longitude));
        this.longitude = longitude;
        this.replyMarkup = replyMarkup;
    }

    /**
     * @return the chatId
     */
    @JsonProperty("chat_id")
    public String getChatId() {
        return chatId;
    }

    /**
     * @return the messageId
     */
    @JsonProperty("message_id")
    public Long getMessageId() {
        return messageId;
    }

    /**
     * @return the inlineMessageId
     */
    @JsonProperty("inline_message_id")
    public String getInlineMessageId() {
        return inlineMessageId;
    }

    /**
     * @return the latitude
     */
    @JsonProperty("latitude")
    public float getLatitude() {
        return latitude;
    }

    /**
     * @return the longitude
     */
    @JsonProperty("longitude")
    public float getLongitude() {
        return longitude;
    }

    /**
     * @return the replyMarkup
     */
    @JsonProperty("reply_markup")
    public InlineKeyboardMarkup getReplyMarkup() {
        return replyMarkup;
    }
}
