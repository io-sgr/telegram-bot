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

package io.sgr.telegram.bot.api.models.game;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;

import io.sgr.telegram.bot.api.models.PhotoSize;
import io.sgr.telegram.bot.api.utils.JsonUtil;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * You can provide an animation for your game so that it looks stylish in chats (check out Lumberjack for an example).
 * This object represents an animation file to be displayed in the message containing a game.
 *
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Animation {

    private final String fileId;
    private final String fileUniqueId;
    private final int width;
    private final int height;
    private final long duration;
    private final PhotoSize thumb;
    private final String fileName;
    private final String mimeType;
    private final Long fileSize;

    /**
     * @param fileId Unique identifier for this file.
     * @param fileUniqueId Unique identifier for this file, which is supposed to be the same over time and for different bots. Can't be used to download or
     * reuse the file.
     * @param width Video width as defined by sender.
     * @param height Video height as defined by sender.
     * @param duration Duration of the video in seconds as defined by sender.
     * @param thumb Optional. Animation thumbnail as defined by sender.
     * @param fileName Optional. Original animation filename as defined by sender.
     * @param mimeType Optional. MIME type of the file as defined by sender.
     * @param fileSize Optional. File size.
     */
    @JsonCreator
    public Animation(
            @JsonProperty("file_id") final String fileId,
            @JsonProperty("file_unique_id") final String fileUniqueId,
            @JsonProperty("width") final int width,
            @JsonProperty("height") final int height,
            @JsonProperty("duration") final long duration,
            @JsonProperty("thumb") final PhotoSize thumb,
            @JsonProperty("file_name") final String fileName,
            @JsonProperty("mime_type") final String mimeType,
            @JsonProperty("file_size") final Long fileSize) {
        checkArgument(!isNullOrEmpty(fileId), "File ID should be provided");
        this.fileId = fileId;
        checkArgument(!isNullOrEmpty(fileUniqueId), "File unique ID should be provided");
        this.fileUniqueId = fileUniqueId;
        checkArgument(width >= 0, "Width should be greater than or equal to zero");
        this.width = width;
        checkArgument(height >= 0, "Height should be greater than or equal to zero");
        this.height = height;
        checkArgument(duration >= 0, "Duration should be greater than or equal to zero");
        this.duration = duration;
        this.thumb = thumb;
        this.fileName = fileName;
        this.mimeType = mimeType;
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

    @JsonProperty("duration")
    public long getDuration() {
        return duration;
    }

    @JsonProperty("thumb")
    public PhotoSize getThumb() {
        return thumb;
    }

    @JsonProperty("file_name")
    public String getFileName() {
        return fileName;
    }

    @JsonProperty("mime_type")
    public String getMimeType() {
        return mimeType;
    }

    @JsonProperty("file_size")
    public Long getFileSize() {
        return fileSize;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJson();
    }

}
