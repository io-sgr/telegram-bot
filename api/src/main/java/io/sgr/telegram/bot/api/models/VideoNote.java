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
package io.sgr.telegram.bot.api.models;

import static io.sgr.telegram.bot.api.utils.Preconditions.notEmptyString;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.sgr.telegram.bot.api.utils.JsonUtil;
import io.sgr.telegram.bot.api.utils.Preconditions;

/**
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoNote {

    private final String fileId;
    private final int length;
    private final long duration;
    private final PhotoSize thumb;
    private final long fileSize;

    /**
     * @param fileId   Unique identifier for this file.
     * @param length   Video width and height as defined by sender.
     * @param duration Duration of the video in seconds as defined by sender.
     * @param thumb    Optional. Video thumbnail.
     * @param fileSize Optional. File size
     */
    @JsonCreator
    public VideoNote(
            @JsonProperty("file_id") String fileId,
            @JsonProperty("length") int length,
            @JsonProperty("duration") long duration,
            @JsonProperty("thumb") PhotoSize thumb,
            @JsonProperty("file_size") long fileSize) {
        notEmptyString(fileId, "File ID should be provided");
        this.fileId = fileId;
        if (length <= 0) {
            throw new IllegalArgumentException("Length should be greater than zero");
        }
        this.length = length;
        if (duration < 0) {
            throw new IllegalArgumentException("Duration should be greater than or equal to zero");
        }
        this.duration = duration;
        this.thumb = thumb;
        this.fileSize = fileSize;
    }

    @JsonProperty("file_id")
    public String getFileId() {
        return fileId;
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

    public String toJSON() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJSON();
    }

}
