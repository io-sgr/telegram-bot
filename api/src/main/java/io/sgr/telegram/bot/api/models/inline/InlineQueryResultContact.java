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

package io.sgr.telegram.bot.api.models.inline;

import static io.sgr.telegram.bot.api.utils.Preconditions.notEmptyString;

import io.sgr.telegram.bot.api.models.markups.InlineKeyboardMarkup;
import io.sgr.telegram.bot.api.utils.JsonUtil;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a contact with a phone number. By default, this contact will be sent by the user. Alternatively, you can
 * use input_message_content to send a message with the specified content instead of the contact.
 *
 * @author SgrAlpha
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InlineQueryResultContact implements InlineQueryResult, ItemWithThumb {

    private static final String TYPE = "contact";

    private final String phoneNumber;
    private final String firstName;
    private final String lastName;
    private final String id;
    private final String thumbUrl;
    private final Integer thumbWidth;
    private final Integer thumbHeight;
    private final InputMessageContent inputMessageContent;
    private final InlineKeyboardMarkup replyMarkup;

    /**
     * @param id                  Unique identifier for this result, 1-64 bytes.
     * @param phoneNumber         Contact's phone number.
     * @param firstName           Contact's first name.
     * @param lastName            Optional. Contact's last name.
     * @param replyMarkup         Optional. Inline keyboard attached to the message.
     * @param inputMessageContent Optional. Content of the message to be sent instead of the location.
     * @param thumbUrl            Optional. Url of the thumbnail for the result.
     * @param thumbWidth          Optional. Thumbnail width.
     * @param thumbHeight         Optional. Thumbnail height.
     */
    public InlineQueryResultContact(
            final String id,
            final String phoneNumber, final String firstName, final String lastName,
            final InlineKeyboardMarkup replyMarkup, final InputMessageContent inputMessageContent,
            final String thumbUrl, final Integer thumbWidth, final Integer thumbHeight) {
        notEmptyString(id, "Missing ID");
        this.id = id;
        notEmptyString(phoneNumber, "Missing phone number");
        this.phoneNumber = phoneNumber;
        notEmptyString(firstName, "Missing first name");
        this.firstName = firstName;
        this.lastName = lastName;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
        this.thumbUrl = thumbUrl;
        this.thumbWidth = thumbWidth;
        this.thumbHeight = thumbHeight;
    }

    @JsonProperty("phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @JsonProperty("first_name")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
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
