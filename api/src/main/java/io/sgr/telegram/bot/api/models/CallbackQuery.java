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
import static io.sgr.telegram.bot.api.utils.Preconditions.notNull;

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
public class CallbackQuery {

    private final String id;
    private final User from;
    private final Message message;
    private final String inlineMessageId;
    private final String chatInstance;
    private final String data;
    private final String gameShortName;

    /**
     * @param id              Unique identifier for this query.
     * @param from            Sender.
     * @param message         Optional. Message with the callback button that originated the query. Note that message
     *                        content and message date will not be available if the message is too old.
     * @param inlineMessageId Optional. Identifier of the message sent via the bot in inline mode, that originated the
     *                        query.
     * @param chatInstance    Global identifier, uniquely corresponding to the chat to which the message with the
     *                        callback button was sent. Useful for high scores in games.
     * @param data            Optional. Data associated with the callback button. Be aware that a bad client can send
     *                        arbitrary data in this field.
     * @param gameShortName   Optional. Short name of a Game to be returned, serves as the unique identifier for the
     *                        game.
     */
    @JsonCreator
    public CallbackQuery(
            @JsonProperty("id") String id,
            @JsonProperty("from") User from,
            @JsonProperty("message") Message message,
            @JsonProperty("inline_message_id") String inlineMessageId,
            @JsonProperty("chat_instance") String chatInstance,
            @JsonProperty("data") String data,
            @JsonProperty("game_short_name") String gameShortName) {
        notEmptyString(id, "ID should be provided");
        this.id = id;
        notNull(from, "Sender should be provided");
        this.from = from;
        this.message = message;
        this.inlineMessageId = inlineMessageId;
        notEmptyString(chatInstance, "Chat instance should be provided");
        this.chatInstance = chatInstance;
        this.data = data;
        this.gameShortName = gameShortName;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("from")
    public User getFrom() {
        return from;
    }

    @JsonProperty("message")
    public Message getMessage() {
        return message;
    }

    @JsonProperty("inline_message_id")
    public String getInlineMessageId() {
        return inlineMessageId;
    }

    @JsonProperty("chat_instance")
    public String getChatInstance() {
        return chatInstance;
    }

    @JsonProperty("data")
    public String getData() {
        return data;
    }

    @JsonProperty("game_short_name")
    public String getGameShortName() {
        return gameShortName;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJson();
    }

}
