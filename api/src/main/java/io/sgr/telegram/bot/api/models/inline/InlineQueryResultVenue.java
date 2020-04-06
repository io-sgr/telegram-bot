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

package io.sgr.telegram.bot.api.models.inline;

import static io.sgr.telegram.bot.api.utils.Preconditions.notEmptyString;

import io.sgr.telegram.bot.api.models.markups.InlineKeyboardMarkup;
import io.sgr.telegram.bot.api.utils.JsonUtil;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a venue. By default, the venue will be sent by the user. Alternatively, you can use input_message_content
 * to send a message with the specified content instead of the venue.
 *
 * @author SgrAlpha
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InlineQueryResultVenue implements InlineQueryResult, ItemWithThumb {

    private static final String TYPE = "venue";

    private final float latitude;
    private final float longitude;
    private final String title;
    private final String address;
    private final String foursquareId;
    private final String foursquareType;
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
     * @param title               Title of the venue.
     * @param address             Address of the venue.
     * @param foursquareId        Optional. Foursquare identifier of the venue if known.
     * @param foursquareType      Optional. Foursquare type of the venue, if known. (For example,
     *                            “arts_entertainment/default”, “arts_entertainment/aquarium” or “food/icecream”.)
     * @param replyMarkup         Optional. Inline keyboard attached to the message.
     * @param inputMessageContent Optional. Content of the message to be sent instead of the location.
     * @param thumbUrl            Optional. Url of the thumbnail for the result.
     * @param thumbWidth          Optional. Thumbnail width.
     * @param thumbHeight         Optional. Thumbnail height.
     */
    public InlineQueryResultVenue(
            final String id, final float latitude, final float longitude,
            final String title, final String address, final String foursquareId,
            final String foursquareType, final InlineKeyboardMarkup replyMarkup, final InputMessageContent inputMessageContent,
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
        this.address = address;
        this.foursquareId = foursquareId;
        this.foursquareType = foursquareType;
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

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("foursquare_id")
    public String getFoursquareId() {
        return foursquareId;
    }

    @JsonProperty("foursquare_type")
    public String getFoursquareType() {
        return foursquareType;
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
