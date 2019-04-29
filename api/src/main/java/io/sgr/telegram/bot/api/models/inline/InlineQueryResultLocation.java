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

package io.sgr.telegram.bot.api.models.inline;

import static io.sgr.telegram.bot.api.utils.Preconditions.notEmptyString;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.sgr.telegram.bot.api.models.markups.InlineKeyboardMarkup;
import io.sgr.telegram.bot.api.utils.JsonUtil;

/**
 * Represents a location on a map. By default, the location will be sent by the user. Alternatively, you can use
 * input_message_content to send a message with the specified content instead of the location.
 *
 * @author SgrAlpha
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InlineQueryResultLocation implements InlineQueryResult, ItemWithThumb {

    private static final String TYPE = "location";

    private final float latitude;
    private final float longitude;
    private final String title;
    private final Integer livePeriod;
    private final String id;
    private final String thumbUrl;
    private final Integer thumbWidth;
    private final Integer thumbHeight;
    private final InputMessageContent inputMessageContent;
    private final InlineKeyboardMarkup replyMarkup;

    /**
     * @param id                  Unique identifier for this result, 1-64 bytes.
     * @param latitude            Location latitude in degrees.
     * @param longitude           Location longitude in degrees.
     * @param title               Location title.
     * @param livePeriod          Optional. Period in seconds for which the location can be updated, should be between
     *                            60 and 86400.
     * @param replyMarkup         Optional. Inline keyboard attached to the message.
     * @param inputMessageContent Optional. Content of the message to be sent instead of the location.
     * @param thumbUrl            Optional. Url of the thumbnail for the result.
     * @param thumbWidth          Optional. Thumbnail width.
     * @param thumbHeight         Optional. Thumbnail height.
     */
    public InlineQueryResultLocation(
            final String id, final float latitude, final float longitude,
            final String title, final Integer livePeriod,
            final InlineKeyboardMarkup replyMarkup, final InputMessageContent inputMessageContent,
            final String thumbUrl, final Integer thumbWidth, final Integer thumbHeight) {
        notEmptyString(id, "Missing ID");
        this.id = id;
        if (Math.abs(latitude) > 90) {
            throw new IllegalArgumentException(String.format("Invalid latitude %f", latitude));
        }
        this.latitude = latitude;
        if (Math.abs(longitude) > 180) {
            throw new IllegalArgumentException(String.format("Invalid longitude %f", longitude));
        }
        this.longitude = longitude;
        this.title = title;
        if (livePeriod != null && (livePeriod < 60 || livePeriod > 86400)) {
            throw new IllegalArgumentException(String.format("Live period should between 60 and 86400, but it's %d", livePeriod));
        }
        this.livePeriod = livePeriod;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
        this.thumbUrl = thumbUrl;
        this.thumbWidth = thumbWidth;
        this.thumbHeight = thumbHeight;
    }

    @JsonProperty("latitude")
    public float getLatitude() {
        return latitude;
    }

    @JsonProperty("longitude")
    public float getLongitude() {
        return longitude;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("live_period")
    public Integer getLivePeriod() {
        return livePeriod;
    }

    @JsonProperty("type")
    @Override public String getType() {
        return TYPE;
    }

    @JsonProperty("id")
    @Override public String getId() {
        return this.id;
    }

    @JsonProperty("thumb_url")
    @Override public String getThumbUrl() {
        return this.thumbUrl;
    }

    @JsonProperty("thumb_width")
    @Override public Integer getThumbWidth() {
        return this.thumbWidth;
    }

    @JsonProperty("thumb_height")
    @Override public Integer getThumbHeight() {
        return this.thumbHeight;
    }

    @JsonProperty("input_message_content")
    @Override public InlineKeyboardMarkup getReplyMarkup() {
        return this.replyMarkup;
    }

    @JsonProperty("reply_markup")
    @Override public InputMessageContent getInputMessageContent() {
        return this.inputMessageContent;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJson();
    }

}
