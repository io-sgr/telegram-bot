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
package io.sgr.telegram.bot.api.models.sticker;

import static io.sgr.telegram.bot.api.utils.Preconditions.notEmptyString;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.sgr.telegram.bot.api.models.PhotoSize;
import io.sgr.telegram.bot.api.utils.JsonUtil;

/**
 * This object represents a sticker.
 *
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Sticker {

    private final String fileId;
    private final int width;
    private final int height;
    private final PhotoSize thumb;
    private final String emoji;
    private final String name;
    private final MaskPosition maskPosition;
    private final long fileSize;

    /**
     * @param fileId       Unique identifier for this file.
     * @param width        Sticker width.
     * @param height       Sticker height.
     * @param thumb        Optional. Sticker thumbnail in the .webp or .jpg format.
     * @param emoji        Optional. Emoji associated with the sticker.
     * @param name         Optional. Name of the sticker set to which the sticker belongs.
     * @param maskPosition Optional. For mask stickers, the position where the mask should be placed.
     * @param fileSize     Optional. File size.
     */
    @JsonCreator
    public Sticker(
            @JsonProperty("file_id") String fileId,
            @JsonProperty("width") int width,
            @JsonProperty("height") int height,
            @JsonProperty("thumb") PhotoSize thumb,
            @JsonProperty("emoji") String emoji,
            @JsonProperty("set_name") String name,
            @JsonProperty("mask_position") MaskPosition maskPosition,
            @JsonProperty("file_size") long fileSize) {
        notEmptyString(fileId, "File ID should be provided");
        this.fileId = fileId;
        if (width <= 0) {
            throw new IllegalArgumentException("Width should be greater than zero");
        }
        this.width = width;
        if (height <= 0) {
            throw new IllegalArgumentException("Height should be greater than zero");
        }
        this.height = height;
        this.thumb = thumb;
        this.emoji = emoji;
        this.name = name;
        this.maskPosition = maskPosition;
        this.fileSize = fileSize;
    }

    @JsonProperty("file_id")
    public String getFileId() {
        return fileId;
    }

    @JsonProperty("width")
    public int getWidth() {
        return width;
    }

    @JsonProperty("height")
    public int getHeight() {
        return height;
    }

    @JsonProperty("thumb")
    public PhotoSize getThumb() {
        return thumb;
    }

    @JsonProperty("emoji")
    public String getEmoji() {
        return emoji;
    }

    @JsonProperty("set_name")
    public String getName() {
        return name;
    }

    @JsonProperty("mask_position")
    public MaskPosition getMaskPosition() {
        return maskPosition;
    }

    @JsonProperty("file_size")
    public long getFileSize() {
        return fileSize;
    }

    public String toJSON() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJSON();
    }

}
