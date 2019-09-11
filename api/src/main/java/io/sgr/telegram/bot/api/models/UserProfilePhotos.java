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

package io.sgr.telegram.bot.api.models;

import static io.sgr.telegram.bot.api.utils.Preconditions.notNull;

import io.sgr.telegram.bot.api.utils.JsonUtil;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author SgrAlpha
 * <p>
 * TODO Make it immutable
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProfilePhotos {

    private final int totalCount;
    private final List<List<PhotoSize>> photos;

    /**
     * @param totalCount Total number of profile pictures the target user has.
     * @param photos     Requested profile pictures (in up to 4 sizes each).
     */
    @JsonCreator
    public UserProfilePhotos(
            @JsonProperty("total_count") int totalCount,
            @JsonProperty("photos") List<List<PhotoSize>> photos) {
        if (totalCount < 0) {
            throw new IllegalArgumentException("Total count should be greater or equal to zero");
        }
        this.totalCount = totalCount;
        notNull(photos, "Photos should be provided");
        this.photos = photos;
    }

    @JsonProperty("total_count")
    public int getTotalCount() {
        return totalCount;
    }

    @JsonProperty("photos")
    public List<List<PhotoSize>> getPhotos() {
        return photos == null ? null : Collections.unmodifiableList(photos.stream()
                .map(items -> items == null ? null : Collections.unmodifiableList(items))
                .collect(Collectors.toList()));
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJson();
    }

}
