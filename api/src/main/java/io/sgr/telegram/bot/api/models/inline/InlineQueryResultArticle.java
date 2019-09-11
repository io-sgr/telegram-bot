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
import static io.sgr.telegram.bot.api.utils.Preconditions.notNull;

import io.sgr.telegram.bot.api.models.markups.InlineKeyboardMarkup;
import io.sgr.telegram.bot.api.utils.JsonUtil;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a link to an article or web page.
 *
 * @author SgrAlpha
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InlineQueryResultArticle implements InlineQueryResult, ItemWithThumb {

    private static final String TYPE = "article";

    private final String title;
    private final String url;
    private final Boolean hideUrl;
    private final String description;

    private final String id;
    private final String thumbUrl;
    private final Integer thumbWidth;
    private final Integer thumbHeight;
    private final InputMessageContent inputMessageContent;
    private final InlineKeyboardMarkup replyMarkup;

    /**
     * @param id                  Unique identifier for this result, 1-64 Bytes.
     * @param title               Title of the result.
     * @param inputMessageContent Content of the message to be sent.
     */
    public InlineQueryResultArticle(String id, String title, InputMessageContent inputMessageContent) {
        this(id, title, inputMessageContent, null, true, null);
    }

    /**
     * @param id                  Unique identifier for this result, 1-64 Bytes.
     * @param title               Title of the result.
     * @param inputMessageContent Content of the message to be sent.
     * @param url                 Optional. URL of the result
     * @param hideUrl             Optional. Pass True, if you don't want the URL to be shown in the message.
     * @param description         Optional. Short description of the result.
     */
    public InlineQueryResultArticle(String id, String title, InputMessageContent inputMessageContent, String url, boolean hideUrl, String description) {
        this(id, title, inputMessageContent, null, url, hideUrl, description, null, null, null);
    }

    /**
     * @param id                  Unique identifier for this result, 1-64 Bytes.
     * @param title               Title of the result.
     * @param inputMessageContent Content of the message to be sent.
     * @param replyMarkup         Optional. Inline keyboard attached to the message.
     * @param url                 Optional. URL of the result.
     * @param hideUrl             Optional. Pass True, if you don't want the URL to be shown in the message.
     * @param description         Optional. Short description of the result.
     * @param thumbUrl            Optional. Url of the thumbnail for the result.
     * @param thumbWidth          Optional. Thumbnail width.
     * @param thumbHeight         Optional. Thumbnail height.
     */
    @JsonCreator
    public InlineQueryResultArticle(
            @JsonProperty("id") String id,
            @JsonProperty("title") String title,
            @JsonProperty("input_message_content") InputMessageContent inputMessageContent,
            @JsonProperty("reply_markup") InlineKeyboardMarkup replyMarkup,
            @JsonProperty("url") String url,
            @JsonProperty("hide_url") Boolean hideUrl,
            @JsonProperty("description") String description,
            @JsonProperty("thumb_url") String thumbUrl,
            @JsonProperty("thumb_url") Integer thumbWidth,
            @JsonProperty("thumb_height") Integer thumbHeight) {
        notEmptyString(id, "Article ID should be provided.");
        this.id = id;
        notEmptyString(title, "Article title should be provided.");
        this.title = title;
        notNull(inputMessageContent, "Message content should be provided.");
        this.inputMessageContent = inputMessageContent;
        this.replyMarkup = replyMarkup;
        this.url = url;
        this.hideUrl = hideUrl;
        this.description = description;
        this.thumbUrl = thumbUrl;
        this.thumbWidth = thumbWidth;
        this.thumbHeight = thumbHeight;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("hide_url")
    public Boolean getHideUrl() {
        return hideUrl;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
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

    @JsonProperty("reply_markup")
    @Override public InlineKeyboardMarkup getReplyMarkup() {
        return this.replyMarkup;
    }

    @JsonProperty("input_message_content")
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
