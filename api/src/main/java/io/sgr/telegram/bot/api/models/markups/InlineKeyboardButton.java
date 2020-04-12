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

package io.sgr.telegram.bot.api.models.markups;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;

import io.sgr.telegram.bot.api.models.LoginUrl;
import io.sgr.telegram.bot.api.utils.JsonUtil;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    private final LoginUrl loginUrl;
    private final String callbackData;
    private final String switchInlineQuery;
    private final String switchInlineQueryCurrentChat;

    /**
     * @param text Label text on the button.
     * @param callbackData Optional. Data to be sent in a callback query to the bot when button is pressed, 1-64 bytes.
     */
    public InlineKeyboardButton(String text, String callbackData) {
        this(text, null, null, callbackData, null, null);
    }

    /**
     * @param text Label text on the button.
     * @param url Optional. HTTP or tg:// url to be opened when button is pressed.
     * @param loginUrl Optional. An HTTP URL used to automatically authorize the user. Can be used as a replacement for the Telegram Login Widget.
     * @param callbackData Optional. Data to be sent in a callback query to the bot when button is pressed, 1-64 bytes.
     * @param switchInlineQuery Optional. If set, pressing the button will prompt the user to select one of their chats, open that chat and insert the bot‘s
     * username and the specified inline query in the input field. Can be empty, in which case just the bot’s username will be inserted.
     * @param switchInlineQueryCurrentChat Optional. If set, pressing the button will insert the bot‘s username and the specified inline query in the current
     * chat's input field. Can be empty, in which case only the bot’s username will be inserted. This offers a quick way for the user to open your bot in inline
     * mode in the same chat – good for
     */
    @JsonCreator
    public InlineKeyboardButton(
            @JsonProperty("text") final String text,
            @JsonProperty("url") final String url,
            @JsonProperty("login_url") final LoginUrl loginUrl,
            @JsonProperty("callback_data") final String callbackData,
            @JsonProperty("switch_inline_query") final String switchInlineQuery,
            @JsonProperty("switch_inline_query_current_chat") final String switchInlineQueryCurrentChat) {
        checkArgument(!isNullOrEmpty(text), "Text should be provided.");
        this.text = text;
        int optionalFieldCnt = 0;
        if (!isNullOrEmpty(url)) {
            optionalFieldCnt++;
        }
        if (!isNullOrEmpty(callbackData)) {
            optionalFieldCnt++;
        }
        if (!isNullOrEmpty(switchInlineQuery)) {
            optionalFieldCnt++;
        }
        if (!isNullOrEmpty(switchInlineQueryCurrentChat)) {
            optionalFieldCnt++;
        }
        if (optionalFieldCnt != 1) {
            throw new IllegalArgumentException("You must use exactly one of the optional fields.");
        }
        this.url = url;
        this.loginUrl = loginUrl;
        checkArgument(isNullOrEmpty(callbackData) || callbackData.length() < 64, "The length of callback data should between 1-64");
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

    @JsonProperty("login_url")
    public LoginUrl getLoginUrl() {
        return loginUrl;
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

    @Override
    public String toString() {
        return this.toJson();
    }

}
