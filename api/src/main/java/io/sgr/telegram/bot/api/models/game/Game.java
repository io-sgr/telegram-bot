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
package io.sgr.telegram.bot.api.models.game;

import static io.sgr.telegram.bot.api.utils.Preconditions.notEmptyString;
import static io.sgr.telegram.bot.api.utils.Preconditions.notNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.sgr.telegram.bot.api.models.MessageEntity;
import io.sgr.telegram.bot.api.models.PhotoSize;
import io.sgr.telegram.bot.api.utils.JsonUtil;

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
    private final PhotoSize[] photo;
    private final String text;
    private final MessageEntity[] textEntities;
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
            @JsonProperty("photo") PhotoSize[] photo,
            @JsonProperty("text") String text,
            @JsonProperty("text_entities") MessageEntity[] textEntities,
            @JsonProperty("animation") Animation animation) {
        notEmptyString(title, "Title should be provided");
        this.title = title;
        notEmptyString(description, "Description should be provided");
        this.description = description;
        notNull(photo, "Photo should be provided");
        this.photo = photo;
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
    public PhotoSize[] getPhoto() {
        return photo;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text_entities")
    public MessageEntity[] getTextEntities() {
        return textEntities;
    }

    @JsonProperty("animation")
    public Animation getAnimation() {
        return animation;
    }

    public String toJSON() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJSON();
    }

}
