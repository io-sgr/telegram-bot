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

import static io.sgr.telegram.bot.api.utils.Preconditions.isEmptyString;
import static io.sgr.telegram.bot.api.utils.Preconditions.notEmptyString;

import io.sgr.telegram.bot.api.models.ParseMode;
import io.sgr.telegram.bot.api.models.markups.InlineKeyboardMarkup;
import io.sgr.telegram.bot.api.utils.JsonUtil;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a link to a file. By default, this file will be sent by the user with an optional caption. Alternatively,
 * you can use input_message_content to send a message with the specified content instead of the file. Currently, only
 * .PDF and .ZIP files can be sent using this method.
 *
 * @author SgrAlpha
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InlineQueryResultDocument implements InlineQueryResult, ItemWithCaption, ItemWithThumb {

    private static final String TYPE = "document";

    private final String title;
    private final String documentUrl;
    private final String mimeType;
    private final String description;
    private final String id;
    private final String caption;
    private final ParseMode parseMode;
    private final String thumbUrl;
    private final Integer thumbWidth;
    private final Integer thumbHeight;
    private final InputMessageContent inputMessageContent;
    private final InlineKeyboardMarkup replyMarkup;

    /**
     * @param id                  Unique identifier for this result, 1-64 bytes.
     * @param title               Title for the result.
     * @param caption             Optional. Caption of the document to be sent, 0-200 characters.
     * @param parseMode           Optional. Send Markdown or HTML, if you want Telegram apps to show bold, italic,
     *                            fixed-width text or inline URLs in the media caption.
     * @param documentUrl         A valid URL for the file.
     * @param mimeType            Mime type of the content of the file, either “application/pdf” or “application/zip”.
     * @param description         Optional. Short description of the result.
     * @param replyMarkup         Optional. Inline keyboard attached to the message.
     * @param inputMessageContent Optional. Content of the message to be sent instead of the file.
     * @param thumbUrl            Optional. URL of the thumbnail (jpeg only) for the file.
     * @param thumbWidth          Optional. Thumbnail width.
     * @param thumbHeight         Optional. Thumbnail height.
     */
    public InlineQueryResultDocument(
            final String id,
            final String title, final String caption, final ParseMode parseMode,
            final String documentUrl, final String mimeType, final String description,
            final InlineKeyboardMarkup replyMarkup, final InputMessageContent inputMessageContent,
            final String thumbUrl, final Integer thumbWidth, final Integer thumbHeight) {
        notEmptyString(id, "Missing ID");
        this.id = id;
        this.title = title;
        if (!isEmptyString(caption) && caption.length() > 200) {
            throw new IllegalArgumentException(String.format("Caption should be shorter than 200 characters, but it's %d", caption.length()));
        }
        this.caption = caption;
        this.parseMode = parseMode;
        notEmptyString(documentUrl, "Missing document URL");
        this.documentUrl = documentUrl;
        notEmptyString(mimeType, "Missing document mime type");
        this.mimeType = mimeType;
        this.description = description;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
        this.thumbUrl = thumbUrl;
        this.thumbWidth = thumbWidth;
        this.thumbHeight = thumbHeight;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("document_url")
    public String getDocumentUrl() {
        return documentUrl;
    }

    @JsonProperty("mime_type")
    public String getMimeType() {
        return mimeType;
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

    @JsonProperty("caption")
    @Override public String getCaption() {
        return this.caption;
    }

    @JsonProperty("parse_mode")
    @Override public ParseMode getParseMode() {
        return this.parseMode;
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
