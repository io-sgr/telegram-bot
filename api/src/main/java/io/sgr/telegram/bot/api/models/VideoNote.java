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

package io.sgr.telegram.bot.api.models;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;

import io.sgr.telegram.bot.api.utils.JsonUtil;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoNote {

    private final String fileId;
    private final String fileUniqueId;
    private final int length;
    private final long duration;
    private final PhotoSize thumb;
    private final long fileSize;

    /**
     * @param fileId Unique identifier for this file.
     * @param fileUniqueId Unique identifier for this file, which is supposed to be the same over time and for different bots. Can't be used to download or
     * reuse the file.
     * @param length Video width and height as defined by sender.
     * @param duration Duration of the video in seconds as defined by sender.
     * @param thumb Optional. Video thumbnail.
     * @param fileSize Optional. File size
     */
    @JsonCreator
    public VideoNote(
            @JsonProperty("file_id") final String fileId,
            @JsonProperty("file_unique_id") final String fileUniqueId,
            @JsonProperty("length") final int length,
            @JsonProperty("duration") final long duration,
            @JsonProperty("thumb") final PhotoSize thumb,
            @JsonProperty("file_size") final long fileSize) {
        checkArgument(!isNullOrEmpty(fileId), "File ID should be provided");
        this.fileId = fileId;
        checkArgument(!isNullOrEmpty(fileUniqueId), "File unique ID should be provided");
        this.fileUniqueId = fileUniqueId;
        checkArgument(length >= 0, "Height should be greater than or equal to zero");
        this.length = length;
        checkArgument(duration >= 0, "Duration should be greater than or equal to zero");
        this.duration = duration;
        this.thumb = thumb;
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

    @JsonProperty("length")
    public int getLength() {
        return length;
    }

    @JsonProperty("duration")
    public long getDuration() {
        return duration;
    }

    @JsonProperty("thumb")
    public PhotoSize getThumb() {
        return thumb;
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
