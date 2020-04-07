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
import io.sgr.telegram.bot.api.utils.Preconditions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class File {

    private final String fileId;
    private final String fileUniqueId;
    private final Long fileSize;
    private final String filePath;

    /**
     * @param fileId Unique identifier for this file.
     * @param fileUniqueId Unique identifier for this file, which is supposed to be the same over time and for different bots. Can't be used to download or
     * reuse the file.
     * @param fileSize Optional. File size, if known.
     * @param filePath Optional. File path. Use https://api.telegram.org/file/bot&lt;token&gt;/&lt;file_path&gt; to get the file.
     */
    public File(
            @JsonProperty("file_id") final String fileId,
            @JsonProperty("file_unique_id") final String fileUniqueId,
            @JsonProperty("file_size") final Long fileSize,
            @JsonProperty("file_path") final String filePath) {
        notEmptyString(fileId, "File ID should be provided");
        this.fileId = fileId;
        this.fileUniqueId = fileUniqueId;
        this.fileSize = fileSize;
        this.filePath = filePath;
    }

    @JsonProperty("file_id")
    public String getFileId() {
        return fileId;
    }

    @JsonProperty("file_unique_id")
    public String getFileUniqueId() {
        return fileUniqueId;
    }

    @JsonProperty("file_size")
    public Long getFileSize() {
        return fileSize;
    }

    @JsonProperty("file_path")
    public String getFilePath() {
        return filePath;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    @Override
    public String toString() {
        return this.toJson();
    }

}
