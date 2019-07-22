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

package io.sgr.telegram.bot.api.models.markups;

import static io.sgr.telegram.bot.api.utils.Preconditions.isEmptyString;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.sgr.telegram.bot.api.utils.JsonUtil;
import io.sgr.telegram.bot.api.utils.Preconditions;

/**
 * This object represents one button of an inline keyboard.
 *
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InlineKeyboardButton {

    private final String text;
    private final String url;
    private final String callbackData;
    private final String switchInlineQuery;
    private final String switchInlineQueryCurrentChat;

    /**
     * @param text         Label text on the button.
     * @param callbackData Optional. Data to be sent in a callback query to the bot when button is pressed, 1-64 bytes.
     */
    public InlineKeyboardButton(String text, String callbackData) {
        this(text, null, callbackData, null, null);
    }

    /**
     * @param text                         Label text on the button.
     * @param url                          Optional. HTTP url to be opened when button is pressed.
     * @param callbackData                 Optional. Data to be sent in a callback query to the bot when button is
     *                                     pressed, 1-64 bytes.
     * @param switchInlineQuery            Optional. If set, pressing the button will prompt the user to select one of
     *                                     their chats, open that chat and insert the bot‘s username and the specified
     *                                     inline query in the input field. Can be empty, in which case just the bot’s
     *                                     username will be inserted.
     * @param switchInlineQueryCurrentChat Optional. If set, pressing the button will insert the bot‘s username and the
     *                                     specified inline query in the current chat's input field. Can be empty, in
     *                                     which case only the bot’s username will be inserted. This offers a quick way
     *                                     for the user to open your bot in inline mode in the same chat – good for
     *                                     selecting something from multiple options.
     */
    @JsonCreator
    public InlineKeyboardButton(
            @JsonProperty("text") String text,
            @JsonProperty("url") String url,
            @JsonProperty("callback_data") String callbackData,
            @JsonProperty("switch_inline_query") String switchInlineQuery,
            @JsonProperty("switch_inline_query_current_chat") String switchInlineQueryCurrentChat) {
        Preconditions.notEmptyString(text, "Text should be provided.");
        this.text = text;
        int optionalFieldCnt = 0;
        if (!isEmptyString(url)) {
            optionalFieldCnt++;
        }
        if (!isEmptyString(callbackData)) {
            optionalFieldCnt++;
        }
        if (!isEmptyString(switchInlineQuery)) {
            optionalFieldCnt++;
        }
        if (!isEmptyString(switchInlineQueryCurrentChat)) {
            optionalFieldCnt++;
        }
        if (optionalFieldCnt != 1) {
            throw new IllegalArgumentException("You must use exactly one of the optional fields.");
        }
        this.url = url;
        if (!isEmptyString(callbackData) && callbackData.length() > 64) {
            throw new IllegalArgumentException("The length of callback data should between 1-64");
        }
        this.callbackData = callbackData;
        this.switchInlineQuery = switchInlineQuery;
        this.switchInlineQueryCurrentChat = switchInlineQueryCurrentChat;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("callback_data")
    public String getCallbackData() {
        return callbackData;
    }

    @JsonProperty("switch_inline_query")
    public String getSwitchInlineQuery() {
        return switchInlineQuery;
    }

    @JsonProperty("switch_inline_query_current_chat")
    public String getSwitchInlineQueryCurrentChat() {
        return switchInlineQueryCurrentChat;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJson();
    }

}
