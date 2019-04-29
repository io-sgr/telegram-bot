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

import static io.sgr.telegram.bot.api.utils.Preconditions.isEmptyString;
import static io.sgr.telegram.bot.api.utils.Preconditions.notEmptyString;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.sgr.telegram.bot.api.models.ParseMode;
import io.sgr.telegram.bot.api.models.markups.InlineKeyboardMarkup;
import io.sgr.telegram.bot.api.utils.JsonUtil;

/**
 * Represents a link to a video animation (H.264/MPEG-4 AVC video without sound). By default, this animated MPEG-4 file
 * will be sent by the user with optional caption. Alternatively, you can use input_message_content to send a message
 * with the specified content instead of the animation.
 *
 * @author SgrAlpha
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InlineQueryResultMpeg4Gif implements InlineQueryResult, ItemWithCaption {

    private static final String TYPE = "mpeg4_gif";

    private final String mpeg4Url;
    private final Integer mpeg4Width;
    private final Integer mpeg4Height;
    private final Integer mpeg4Duration;
    private final String thumbUrl;
    private final String title;
    private final String id;
    private final String caption;
    private final ParseMode parseMode;
    private final InputMessageContent inputMessageContent;
    private final InlineKeyboardMarkup replyMarkup;

    /**
     * @param id                  Unique identifier for this result, 1-64 bytes.
     * @param mpeg4Url            A valid URL for the MP4 file. File size must not exceed 1MB.
     * @param mpeg4Width          Optional. Video width.
     * @param mpeg4Height         Optional. Video height.
     * @param mpeg4Duration       Optional. Video duration.
     * @param thumbUrl            URL of the static thumbnail (jpeg or gif) for the result.
     * @param title               Optional. Title for the result.
     * @param caption             Optional. Caption of the MPEG-4 file to be sent, 0-200 characters.
     * @param parseMode           Optional. Send Markdown or HTML, if you want Telegram apps to show bold, italic,
     *                            fixed-width text or inline URLs in the media caption.
     * @param replyMarkup         Optional. Inline keyboard attached to the message.
     * @param inputMessageContent Optional. Content of the message to be sent instead of the video animation.
     */
    public InlineQueryResultMpeg4Gif(
            final String id, final String mpeg4Url,
            final Integer mpeg4Width, final Integer mpeg4Height, final Integer mpeg4Duration, final String thumbUrl,
            final String title, final String caption,
            final ParseMode parseMode,
            final InlineKeyboardMarkup replyMarkup,
            final InputMessageContent inputMessageContent) {
        notEmptyString(id, "Missing ID");
        this.id = id;
        notEmptyString(mpeg4Url, "Missing video URL");
        this.mpeg4Url = mpeg4Url;
        this.mpeg4Width = mpeg4Width;
        this.mpeg4Height = mpeg4Height;
        this.mpeg4Duration = mpeg4Duration;
        notEmptyString(thumbUrl, "Missing thumb URL");
        this.thumbUrl = thumbUrl;
        this.title = title;
        if (!isEmptyString(caption) && caption.length() > 200) {
            throw new IllegalArgumentException(String.format("Caption should be shorter than 200 characters, but it's %d", caption.length()));
        }
        this.caption = caption;
        this.parseMode = parseMode;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
    }

    @JsonProperty("mpeg4_url")
    public String getMpeg4Url() {
        return mpeg4Url;
    }

    @JsonProperty("mpeg4_width")
    public Integer getMpeg4Width() {
        return mpeg4Width;
    }

    @JsonProperty("mpeg4_height")
    public Integer getMpeg4Height() {
        return mpeg4Height;
    }

    @JsonProperty("mpeg4_duration")
    public Integer getMpeg4Duration() {
        return mpeg4Duration;
    }

    @JsonProperty("thumb_url")
    public String getThumbUrl() {
        return thumbUrl;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
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
