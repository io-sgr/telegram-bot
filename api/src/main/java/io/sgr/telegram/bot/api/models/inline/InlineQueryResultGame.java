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
package io.sgr.telegram.bot.api.models.inline;

import static io.sgr.telegram.bot.api.utils.Preconditions.notEmptyString;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.sgr.telegram.bot.api.models.markups.InlineKeyboardMarkup;
import io.sgr.telegram.bot.api.utils.JsonUtil;

/**
 * Represents a Game.
 *
 * @author SgrAlpha
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InlineQueryResultGame implements InlineQueryResult {

    private static final String TYPE = "game";

    private final String gameShortName;
    private final String id;
    private final InlineKeyboardMarkup replyMarkup;

    /**
     * @param id            Unique identifier for this result, 1-64 bytes.
     * @param gameShortName Contact's phone number.
     * @param replyMarkup   Optional. Inline keyboard attached to the message.
     */
    public InlineQueryResultGame(final String id, final String gameShortName, final InlineKeyboardMarkup replyMarkup) {
        notEmptyString(id, "Missing ID");
        this.id = id;
        notEmptyString(gameShortName, "Missing game short name");
        this.gameShortName = gameShortName;
        this.replyMarkup = replyMarkup;
    }

    @JsonProperty("game_short_name")
    public String getGameShortName() {
        return gameShortName;
    }

    @JsonProperty("type")
    @Override public String getType() {
        return TYPE;
    }

    @JsonProperty("id")
    @Override public String getId() {
        return this.id;
    }

    @JsonProperty("input_message_content")
    @Override public InlineKeyboardMarkup getReplyMarkup() {
        return this.replyMarkup;
    }

    @JsonProperty("reply_markup")
    @Override public InputMessageContent getInputMessageContent() {
        return null;
    }

    public String toJSON() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJSON();
    }

}
