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

import static io.sgr.telegram.bot.api.utils.Preconditions.notEmptyString;

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
public class Audio {

    private final String fileId;
    private final String fileUniqueId;
    private final long duration;
    private final String performer;
    private final String title;
    private final String mimeType;
    private final long fileSize;
    private final PhotoSize thumb;

    /**
     * @param fileId Unique identifier for this file.
     * @param fileUniqueId Unique identifier for this file, which is supposed to be the same over time and for different bots. Can't be used to download or
     * reuse the file.
     * @param duration Duration of the audio in seconds as defined by sender.
     * @param performer Optional. Performer of the audio as defined by sender or by audio tags.
     * @param title Optional. Title of the audio as defined by sender or by audio tags.
     * @param mimeType Optional. MIME type of the file as defined by sender.
     * @param fileSize Optional. File size.
     * @param thumb Optional. Thumbnail of the album cover to which the music file belongs.
     */
    @JsonCreator
    public Audio(
            @JsonProperty("file_id") final String fileId,
            @JsonProperty("file_unique_id") final String fileUniqueId,
            @JsonProperty("duration") final long duration,
            @JsonProperty("performer") final String performer,
            @JsonProperty("title") final String title,
            @JsonProperty("mime_type") final String mimeType,
            @JsonProperty("file_size") final long fileSize,
            @JsonProperty("thumb") final PhotoSize thumb) {
        notEmptyString(fileId, "File ID should be provided");
        this.fileId = fileId;
        this.fileUniqueId = fileUniqueId;
        if (duration < 0) {
            throw new IllegalArgumentException("Duration should be greater than or equal to zero");
        }
        this.duration = duration;
        this.performer = performer;
        this.title = title;
        this.mimeType = mimeType;
        this.fileSize = fileSize;
        this.thumb = thumb;
    }

    @JsonProperty("file_id")
    public String getFileId() {
        return fileId;
    }

    @JsonProperty("file_unique_id")
    public String getFileUniqueId() {
        return fileUniqueId;
    }

    @JsonProperty("duration")
    public long getDuration() {
        return duration;
    }

    @JsonProperty("performer")
    public String getPerformer() {
        return performer;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("mime_type")
    public String getMimeType() {
        return mimeType;
    }

    @JsonProperty("file_size")
    public long getFileSize() {
        return fileSize;
    }

    @JsonProperty("thumb")
    public PhotoSize getThumb() {
        return thumb;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    @Override
    public String toString() {
        return this.toJson();
    }

}
