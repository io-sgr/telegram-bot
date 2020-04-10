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

import static io.sgr.telegram.bot.api.utils.Preconditions.notNull;

import io.sgr.telegram.bot.api.models.inline.ChosenInlineResult;
import io.sgr.telegram.bot.api.models.inline.InlineQuery;
import io.sgr.telegram.bot.api.utils.JsonUtil;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This object represents an incoming update.
 *
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Update {

    private final long id;
    private final Message message;
    private final Message editedMessage;
    private final Message channelPost;
    private final Message editedChannelPost;
    private final InlineQuery inlineQuery;
    private final ChosenInlineResult chosenInlineResult;
    private final CallbackQuery callbackQuery;
    private final Poll poll;
    private final PollAnswer pollAnswer;

    /**
     * @param id The update's unique identifier. Update identifiers start from a certain positive number and increase sequentially.
     * @param message Optional. New incoming message of any kind — text, photo, sticker, etc.
     * @param editedMessage Optional. New version of a message that is known to the bot and was edited
     * @param channelPost Optional. New incoming channel post of any kind — text, photo, sticker, etc.
     * @param editedChannelPost Optional. New version of a channel post that is known to the bot and was edited.
     * @param inlineQuery Optional. New incoming inline query.
     * @param chosenInlineResult Optional. The result of a inline query that was chosen by a user and sent to their chat partner.
     * @param callbackQuery Optional. New incoming callback query.
     * @param poll Optional. New poll state. Bots receive only updates about stopped polls and polls,
     * @param pollAnswer Optional. A user changed their answer in a non-anonymous poll. Bots receive new votes only in polls that were sent by the bot itself.
     */
    @JsonCreator
    public Update(
            @JsonProperty("update_id") final long id,
            @JsonProperty("message") final Message message,
            @JsonProperty("edited_message") final Message editedMessage,
            @JsonProperty("channel_post") final Message channelPost,
            @JsonProperty("edited_channel_post") final Message editedChannelPost,
            @JsonProperty("inline_query") final InlineQuery inlineQuery,
            @JsonProperty("chosen_inline_result") final ChosenInlineResult chosenInlineResult,
            @JsonProperty("callback_query") final CallbackQuery callbackQuery,
            @JsonProperty("poll") final Poll poll,
            @JsonProperty("poll_answer") final PollAnswer pollAnswer) {
        notNull(id, "Update ID should be provided.");
        this.id = id;
        this.message = message;
        this.editedMessage = editedMessage;
        this.channelPost = channelPost;
        this.editedChannelPost = editedChannelPost;
        this.inlineQuery = inlineQuery;
        this.chosenInlineResult = chosenInlineResult;
        this.callbackQuery = callbackQuery;
        this.poll = poll;
        this.pollAnswer = pollAnswer;
    }

    @JsonProperty("update_id")
    public final long getId() {
        return id;
    }

    @JsonProperty("message")
    public final Message getMessage() {
        return message;
    }

    @JsonProperty("edited_message")
    public final Message getEditedMessage() {
        return editedMessage;
    }

    @JsonProperty("channel_post")
    public final Message getChannelPost() {
        return channelPost;
    }

    @JsonProperty("edited_channel_post")
    public final Message getEditedChannelPost() {
        return editedChannelPost;
    }

    @JsonProperty("inline_query")
    public final InlineQuery getInlineQuery() {
        return inlineQuery;
    }

    @JsonProperty("chosen_inline_result")
    public final ChosenInlineResult getChosenInlineResult() {
        return chosenInlineResult;
    }

    @JsonProperty("callback_query")
    public final CallbackQuery getCallbackQuery() {
        return callbackQuery;
    }

    @JsonProperty("poll")
    public Poll getPoll() {
        return poll;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    @Override
    public String toString() {
        return this.toJson();
    }

}
