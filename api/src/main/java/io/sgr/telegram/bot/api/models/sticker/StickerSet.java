/*
 * Copyright 2017-2018 SgrAlpha
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
import static io.sgr.telegram.bot.api.utils.Preconditions.notNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.sgr.telegram.bot.api.utils.JsonUtil;

import java.util.Collections;
import java.util.List;

/**
 * This object represents a sticker set.
 *
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StickerSet {

    private final String name;
    private final String title;
    private final boolean containsMasks;
    private final List<Sticker> stickers;

    /**
     * @param name          Sticker set name.
     * @param title         Sticker set title.
     * @param containsMasks True, if the sticker set contains masks.
     * @param stickers      List of all set stickers.
     */
    @JsonCreator
    public StickerSet(
            @JsonProperty("name") String name,
            @JsonProperty("title") String title,
            @JsonProperty("contains_masks") boolean containsMasks,
            @JsonProperty("stickers") List<Sticker> stickers) {
        notEmptyString(name, "Name should be provided");
        this.name = name;
        notEmptyString(title, "Title should be provided");
        this.title = title;
        this.containsMasks = containsMasks;
        notNull(stickers, "Stickers should be provided");
        if (stickers.isEmpty()) {
            throw new IllegalArgumentException("Stickers should not be empty");
        }
        this.stickers = stickers;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("contains_masks")
    public boolean isContainsMasks() {
        return containsMasks;
    }

    @JsonProperty("stickers")
    public List<Sticker> getStickers() {
        return stickers == null ? null : Collections.unmodifiableList(stickers);
    }

    public String toJSON() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJSON();
    }

}
