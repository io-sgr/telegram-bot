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

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;

import io.sgr.telegram.bot.api.models.ParseMode;
import io.sgr.telegram.bot.api.models.markups.InlineKeyboardMarkup;
import io.sgr.telegram.bot.api.utils.JsonUtil;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a link to a page containing an embedded video player or a video file. By default, this video file will be
 * sent by the user with an optional caption. Alternatively, you can use input_message_content to send a message with
 * the specified content instead of the video.
 * <p>
 * If an InlineQueryResultVideo message contains an embedded video (e.g., YouTube), you must replace its content using
 * input_message_content.
 *
 * @author SgrAlpha
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InlineQueryResultVideo implements InlineQueryResult, ItemWithCaption {

    private static final String TYPE = "video";

    private final String videoUrl;
    private final String mimeType;
    private final String thumbUrl;
    private final String title;
    private final Integer videoWidth;
    private final Integer videoHeight;
    private final Integer videoDuration;
    private final String description;

    private final String id;
    private final String caption;
    private final ParseMode parseMode;
    private final InputMessageContent inputMessageContent;
    private final InlineKeyboardMarkup replyMarkup;

    /**
     * @param id                  Unique identifier for this result, 1-64 bytes.
     * @param videoUrl            A valid URL for the embedded video player or video file.
     * @param mimeType            Mime type of the content of video url, “text/html” or “video/mp4”.
     * @param thumbUrl            URL of the static thumbnail (jpeg or gif) for the result.
     * @param title               Optional. Title for the result.
     * @param caption             Optional. Caption of the video to be sent, 0-200 characters.
     * @param parseMode           Optional. Send Markdown or HTML, if you want Telegram apps to show bold, italic,
     *                            fixed-width text or inline URLs in the media caption.
     * @param videoWidth          Optional. Video width.
     * @param videoHeight         Optional. Video height.
     * @param videoDuration       Optional. Video duration.
     * @param description         Optional. Short description of the result.
     * @param replyMarkup         Optional. Inline keyboard attached to the message.
     * @param inputMessageContent Optional. Content of the message to be sent instead of the video. This field is
     *                            required if InlineQueryResultVideo is used to send an HTML-page as a result (e.g., a
     *                            YouTube video).
     */
    public InlineQueryResultVideo(
            final String id, final String videoUrl, final String mimeType, final String thumbUrl,
            final String title, final String caption, final ParseMode parseMode,
            final Integer videoWidth, final Integer videoHeight, final Integer videoDuration,
            final String description, final InlineKeyboardMarkup replyMarkup,
            final InputMessageContent inputMessageContent) {
        checkArgument(!isNullOrEmpty(id), "Missing ID");
        this.id = id;
        checkArgument(!isNullOrEmpty(videoUrl), "Missing video URL");
        this.videoUrl = videoUrl;
        checkArgument(!isNullOrEmpty(mimeType), "Missing video mime type");
        this.mimeType = mimeType;
        checkArgument(!isNullOrEmpty(thumbUrl), "Missing thumb URL");
        this.thumbUrl = thumbUrl;
        this.title = title;
        checkArgument(isNullOrEmpty(caption) || caption.length() <= 200,
                String.format("Caption should be shorter than 200 characters, but it's %d", caption.length()));
        this.caption = caption;
        this.parseMode = parseMode;
        this.videoWidth = videoWidth;
        this.videoHeight = videoHeight;
        this.videoDuration = videoDuration;
        this.description = description;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
    }

    @JsonProperty("video_url")
    public String getVideoUrl() {
        return videoUrl;
    }

    @JsonProperty("mime_type")
    public String getMimeType() {
        return mimeType;
    }

    @JsonProperty("thumb_url")
    public String getThumbUrl() {
        return thumbUrl;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("video_width")
    public Integer getVideoWidth() {
        return videoWidth;
    }

    @JsonProperty("video_height")
    public Integer getVideoHeight() {
        return videoHeight;
    }

    @JsonProperty("video_duration")
    public Integer getVideoDuration() {
        return videoDuration;
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
