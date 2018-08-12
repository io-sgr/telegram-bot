/*
 * Copyright 2017-2018 SgrAlpha
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
import io.sgr.telegram.bot.api.models.markups.ReplyMarkup;

/**
 * @author SgrAlpha
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SendLocationPayload {

    private final String chatId;
    private final float latitude;
    private final float longitude;
    private final Integer livePeriod;
    private final Boolean disableNotification;
    private final Long replyTo;
    private final ReplyMarkup replyMarkup;

    /**
     * @param chatId              Unique identifier for the target chat or username of the target channel (in the
     *                            format
     * @param latitude            Latitude of the location
     * @param longitude           Longitude of the location
     * @param livePeriod          Optional. Period in seconds for which the location will be updated (see Live
     *                            Locations, should be between 60 and 86400.
     * @param disableNotification Optional. Sends the message silently. Users will receive a notification with no
     *                            sound.
     * @param replyTo             Optional. If the message is a reply, ID of the original message.
     * @param replyMarkup         Optional. Additional interface options. A JSON-serialized object for an inline
     *                            keyboard, custom reply keyboard, instructions to remove reply keyboard or to force a
     *                            reply from the user.
     */
    public SendLocationPayload(
            String chatId, float latitude, float longitude, Integer livePeriod,
            Boolean disableNotification, Long replyTo, ReplyMarkup replyMarkup) {
        notEmptyString(chatId, "Chat ID should be provided");
        this.chatId = chatId;
        if (Math.abs(latitude) > 90) {
            throw new IllegalArgumentException(String.format("Invalid latitude: %f", latitude));
        }
        this.latitude = latitude;
        if (Math.abs(longitude) > 180) {
            throw new IllegalArgumentException(String.format("Invalid longitude: %f", longitude));
        }
        this.longitude = longitude;
        if (livePeriod != null && (livePeriod < 60 || livePeriod > 86400)) {
            throw new IllegalArgumentException(String.format("Invalid live in period: %d, should be between 60 and 86400", livePeriod));
        }
        this.livePeriod = livePeriod;
        this.disableNotification = disableNotification;
        this.replyTo = replyTo;
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
     * @return the chatId
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
     * @return the livePeriod
     */
    @JsonProperty("live_period")
    public Integer getLivePeriod() {
        return livePeriod;
    }

    /**
     * @return the disableNotification
     */
    @JsonProperty("disable_notification")
    public Boolean getDisableNotification() {
        return disableNotification;
    }

    /**
     * @return the replyTo
     */
    @JsonProperty("reply_to_message_id")
    public Long getReplyTo() {
        return replyTo;
    }

    /**
     * @return the replyMarkup
     */
    @JsonProperty("reply_markup")
    public ReplyMarkup getReplyMarkup() {
        return replyMarkup;
    }
}
