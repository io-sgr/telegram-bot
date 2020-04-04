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

package io.sgr.telegram.bot.api.models.sticker;

import static io.sgr.telegram.bot.api.utils.Preconditions.notEmptyString;

import io.sgr.telegram.bot.api.models.PhotoSize;
import io.sgr.telegram.bot.api.utils.JsonUtil;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This object represents a sticker.
 *
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Sticker {

    private final String fileId;
    private final String fileUniqueId;
    private final int width;
    private final int height;
    private final boolean animated;
    private final PhotoSize thumb;
    private final String emoji;
    private final String setName;
    private final MaskPosition maskPosition;
    private final long fileSize;

    /**
     * @param fileId Unique identifier for this file.
     * @param fileUniqueId Unique identifier for this file, which is supposed to be the same over time and for different bots. Can't be used to download or
     * reuse the file.
     * @param width Sticker width.
     * @param height Sticker height.
     * @param animated True, if the sticker is animated.
     * @param thumb Optional. Sticker thumbnail in the .webp or .jpg format.
     * @param emoji Optional. Emoji associated with the sticker.
     * @param setName Optional. Name of the sticker set to which the sticker belongs.
     * @param maskPosition Optional. For mask stickers, the position where the mask should be placed.
     * @param fileSize Optional. File size.
     */
    @JsonCreator
    public Sticker(
            @JsonProperty("file_id") final String fileId,
            @JsonProperty("file_unique_id") final String fileUniqueId,
            @JsonProperty("width") final int width,
            @JsonProperty("height") final int height,
            @JsonProperty("is_animated") final boolean animated,
            @JsonProperty("thumb") final PhotoSize thumb,
            @JsonProperty("emoji") final String emoji,
            @JsonProperty("set_name") final String setName,
            @JsonProperty("mask_position") final MaskPosition maskPosition,
            @JsonProperty("file_size") final long fileSize) {
        this.fileUniqueId = fileUniqueId;
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
        this.animated = animated;
        this.thumb = thumb;
        this.emoji = emoji;
        this.setName = setName;
        this.maskPosition = maskPosition;
        this.fileSize = fileSize;
    }

    @JsonProperty("file_id")
    public String getFileId() {
        return fileId;
    }

    @JsonProperty("file_unique_id")
    public String getFileUniqueId() {
        return fileUniqueId;
    }

    @JsonProperty("width")
    public int getWidth() {
        return width;
    }

    @JsonProperty("height")
    public int getHeight() {
        return height;
    }

    @JsonProperty("is_animated")
    public boolean isAnimated() {
        return animated;
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
    public String getSetName() {
        return setName;
    }

    @JsonProperty("mask_position")
    public MaskPosition getMaskPosition() {
        return maskPosition;
    }

    @JsonProperty("file_size")
    public long getFileSize() {
        return fileSize;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    @Override
    public String toString() {
        return this.toJson();
    }

}
