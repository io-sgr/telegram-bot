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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.sgr.telegram.bot.api.utils.JsonUtil;

/**
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatPhoto {

    private final String smallFileId;
    private final String bigFileId;

    /**
     * @param smallFileId Unique file identifier of small (160x160) chat photo. This file_id can be used only for photo
     *                    download.
     * @param bigFileId   Unique file identifier of big (640x640) chat photo. This file_id can be used only for photo
     *                    download.
     */
    @JsonCreator
    public ChatPhoto(@JsonProperty("small_file_id") String smallFileId, @JsonProperty("big_file_id") String bigFileId) {
        this.smallFileId = smallFileId;
        this.bigFileId = bigFileId;
    }

    @JsonProperty("small_file_id")
    public String getSmallFileId() {
        return smallFileId;
    }

    @JsonProperty("big_file_id")
    public String getBigFileId() {
        return bigFileId;
    }

    public String toJSON() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJSON();
    }

}
