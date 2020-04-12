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

import io.sgr.telegram.bot.api.models.markups.InlineKeyboardMarkup;
import io.sgr.telegram.bot.api.utils.JsonUtil;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a link to a sticker stored on the Telegram servers. By default, this sticker will be sent by the user.
 * Alternatively, you can use input_message_content to send a message with the specified content instead of the
 * sticker.
 *
 * @author SgrAlpha
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InlineQueryResultCachedSticker implements InlineQueryResult, CachedItem {

    private static final String TYPE = "sticker";

    private final String id;
    private final String fileId;
    private final InputMessageContent inputMessageContent;
    private final InlineKeyboardMarkup replyMarkup;

    /**
     * @param id                  Unique identifier for this result, 1-64 bytes.
     * @param fileId              A valid file identifier of the photo.
     * @param replyMarkup         Optional. Inline keyboard attached to the message.
     * @param inputMessageContent Optional. Content of the message to be sent instead of the photo.
     */
    public InlineQueryResultCachedSticker(
            final String id, final String fileId,
            final InlineKeyboardMarkup replyMarkup, final InputMessageContent inputMessageContent) {
        checkArgument(!isNullOrEmpty(id), "Missing ID");
        this.id = id;
        checkArgument(!isNullOrEmpty(fileId), "Missing photo URL");
        this.fileId = fileId;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
    }

    @JsonProperty("type")
    @Override public String getType() {
        return TYPE;
    }

    @JsonProperty("id")
    @Override public String getId() {
        return this.id;
    }

    @JsonProperty("sticker_file_id")
    @Override public String getFileId() {
        return fileId;
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
