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
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.isNullOrEmpty;

import io.sgr.telegram.bot.api.models.MessageEntity;
import io.sgr.telegram.bot.api.models.PhotoSize;
import io.sgr.telegram.bot.api.utils.JsonUtil;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

/**
 * This object represents a game. Use BotFather to create and edit games, their short names will act as unique
 * identifiers.
 *
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Game {

    private final String title;
    private final String description;
    private final List<PhotoSize> photo;
    private final String text;
    private final List<MessageEntity> textEntities;
    private final Animation animation;

    /**
     * @param title        Title of the game.
     * @param description  Description of the game.
     * @param photo        Photo that will be displayed in the game message in chats.
     * @param text         Optional. Brief description of the game or high scores included in the game message. Can be
     *                     automatically edited to include current high scores for the game when the bot calls
     *                     setGameScore, or manually edited using editMessageText. 0-4096 characters.
     * @param textEntities Optional. Special entities that appear in text, such as usernames, URLs, bot commands, etc.
     * @param animation    Optional. Animation that will be displayed in the game message in chats. Upload via
     *                     BotFather.
     */
    @JsonCreator
    public Game(
            @JsonProperty("title") String title,
            @JsonProperty("description") String description,
            @JsonProperty("photo") List<PhotoSize> photo,
            @JsonProperty("text") String text,
            @JsonProperty("text_entities") List<MessageEntity> textEntities,
            @JsonProperty("animation") Animation animation) {
        checkArgument(!isNullOrEmpty(title), "Title should be provided");
        this.title = title;
        checkArgument(!isNullOrEmpty(description), "Description should be provided");
        this.description = description;
        this.photo = checkNotNull(photo, "Photo should be provided");
        this.text = text;
        this.textEntities = textEntities;
        this.animation = animation;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("photo")
    public List<PhotoSize> getPhoto() {
        return Collections.unmodifiableList(photo);
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text_entities")
    public List<MessageEntity> getTextEntities() {
        return Collections.unmodifiableList(textEntities);
    }

    @JsonProperty("animation")
    public Animation getAnimation() {
        return animation;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJson();
    }

}
