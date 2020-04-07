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
public class ChatPhoto {

    private final String smallFileId;
    private final String smallFileUniqueId;
    private final String bigFileId;
    private final String bigFileUniqueId;

    /**
     * @param smallFileId File identifier of small (160x160) chat photo. This file_id can be used only for photo download and only for as long as the photo is
     * not changed.
     * @param smallFileUniqueId Unique file identifier of small (160x160) chat photo, which is supposed to be the same over time and for different bots. Can't
     * be used to download or reuse the file.
     * @param bigFileId File identifier of big (640x640) chat photo. This file_id can be used only for photo download and only for as long as the photo is not
     * changed.
     * @param bigFileUniqueId Unique file identifier of big (640x640) chat photo, which is supposed to be the same over time and for different bots. Can't be
     * used to download or reuse the file.
     */
    @JsonCreator
    public ChatPhoto(
            @JsonProperty("small_file_id") final String smallFileId,
            @JsonProperty("small_file_unique_id") final String smallFileUniqueId,
            @JsonProperty("big_file_id") final String bigFileId,
            @JsonProperty("big_file_unique_id") final String bigFileUniqueId) {
        this.smallFileId = smallFileId;
        this.smallFileUniqueId = smallFileUniqueId;
        this.bigFileId = bigFileId;
        this.bigFileUniqueId = bigFileUniqueId;
    }

    @JsonProperty("small_file_id")
    public String getSmallFileId() {
        return smallFileId;
    }

    @JsonProperty("small_file_unique_id")
    public String getSmallFileUniqueId() {
        return smallFileUniqueId;
    }

    @JsonProperty("big_file_id")
    public String getBigFileId() {
        return bigFileId;
    }

    @JsonProperty("big_file_unique_id")
    public String getBigFileUniqueId() {
        return bigFileUniqueId;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    @Override
    public String toString() {
        return this.toJson();
    }

}
