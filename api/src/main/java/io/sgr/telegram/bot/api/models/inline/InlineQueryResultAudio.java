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
 * Represents a link to an mp3 audio file. By default, this audio file will be sent by the user. Alternatively, you can use input_message_content to send a
 * message with the specified content instead of the audio.
 *
 * @author SgrAlpha
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InlineQueryResultAudio implements InlineQueryResult, ItemWithCaption {

    private static final String TYPE = "audio";

    private final String audioUrl;
    private final String title;
    private final String performer;
    private final Integer audioDuration;
    private final String id;
    private final String caption;
    private final ParseMode parseMode;
    private final InputMessageContent inputMessageContent;
    private final InlineKeyboardMarkup replyMarkup;

    /**
     * @param id Unique identifier for this result, 1-64 bytes.
     * @param audioUrl A valid URL for the audio file.
     * @param title Optional. Title for the result.
     * @param caption Optional. Caption of the audio to be sent, 0-200 characters.
     * @param parseMode Optional. Send Markdown or HTML, if you want Telegram apps to show bold, italic, fixed-width text or inline URLs in the media caption.
     * @param performer Optional. Performer.
     * @param audioDuration Optional. Audio duration in seconds.
     * @param replyMarkup Optional. Inline keyboard attached to the message.
     * @param inputMessageContent Optional. Content of the message to be sent instead of the audio.
     */
    public InlineQueryResultAudio(
            final String id, final String audioUrl,
            final String title, final String caption, final ParseMode parseMode,
            final Integer audioDuration, final String performer,
            final InlineKeyboardMarkup replyMarkup, final InputMessageContent inputMessageContent) {
        checkArgument(!isNullOrEmpty(id), "Missing ID");
        this.id = id;
        checkArgument(!isNullOrEmpty(audioUrl), "Missing audio URL");
        this.audioUrl = audioUrl;
        this.title = title;
        checkArgument(isNullOrEmpty(caption) || caption.length() <= 200,
                String.format("Caption should be shorter than 200 characters, but it's %d", caption.length()));
        this.caption = caption;
        this.parseMode = parseMode;
        this.performer = performer;
        this.audioDuration = audioDuration;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
    }

    @JsonProperty("audio_url")
    public String getAudioUrl() {
        return audioUrl;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("performer")
    public String getPerformer() {
        return performer;
    }

    @JsonProperty("audio_duration")
    public Integer getAudioDuration() {
        return audioDuration;
    }

    @JsonProperty("type")
    @Override
    public String getType() {
        return TYPE;
    }

    @JsonProperty("id")
    @Override
    public String getId() {
        return this.id;
    }

    @JsonProperty("caption")
    @Override
    public String getCaption() {
        return this.caption;
    }

    @JsonProperty("parse_mode")
    @Override
    public ParseMode getParseMode() {
        return this.parseMode;
    }

    @JsonProperty("input_message_content")
    @Override
    public InlineKeyboardMarkup getReplyMarkup() {
        return this.replyMarkup;
    }

    @JsonProperty("reply_markup")
    @Override
    public InputMessageContent getInputMessageContent() {
        return this.inputMessageContent;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    @Override
    public String toString() {
        return this.toJson();
    }

}
