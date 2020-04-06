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

package io.sgr.telegram.bot.api.models.sticker;

import static io.sgr.telegram.bot.api.utils.Preconditions.notEmptyString;
import static io.sgr.telegram.bot.api.utils.Preconditions.notNull;

import io.sgr.telegram.bot.api.models.PhotoSize;
import io.sgr.telegram.bot.api.utils.JsonUtil;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    private final boolean animated;
    private final boolean containsMasks;
    private final List<Sticker> stickers;
    private final PhotoSize thumb;

    /**
     * @param name Sticker set name.
     * @param title Sticker set title.
     * @param animated True, if the sticker set contains animated stickers.
     * @param containsMasks True, if the sticker set contains masks.
     * @param stickers List of all set stickers.
     * @param thumb Optional. Sticker set thumbnail in the .WEBP or .TGS format.
     */
    @JsonCreator
    public StickerSet(
            @JsonProperty("name") final String name,
            @JsonProperty("title") final String title,
            @JsonProperty("is_animated") final boolean animated,
            @JsonProperty("contains_masks") final boolean containsMasks,
            @JsonProperty("stickers") final List<Sticker> stickers,
            @JsonProperty("thumb") final PhotoSize thumb) {
        notEmptyString(name, "Name should be provided");
        this.name = name;
        notEmptyString(title, "Title should be provided");
        this.title = title;
        this.animated = animated;
        this.containsMasks = containsMasks;
        notNull(stickers, "Stickers should be provided");
        if (stickers.isEmpty()) {
            throw new IllegalArgumentException("Stickers should not be empty");
        }
        this.stickers = stickers;
        this.thumb = thumb;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("is_animated")
    public boolean isAnimated() {
        return animated;
    }

    @JsonProperty("contains_masks")
    public boolean isContainsMasks() {
        return containsMasks;
    }

    @JsonProperty("stickers")
    public List<Sticker> getStickers() {
        return stickers == null ? null : Collections.unmodifiableList(stickers);
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
