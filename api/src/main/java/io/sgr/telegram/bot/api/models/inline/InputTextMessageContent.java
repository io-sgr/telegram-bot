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

package io.sgr.telegram.bot.api.models.inline;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;

import io.sgr.telegram.bot.api.models.ParseMode;
import io.sgr.telegram.bot.api.utils.JsonUtil;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the content of a text message to be sent as the result of an inline query.
 *
 * @author SgrAlpha
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InputTextMessageContent implements InputMessageContent {

    private final String text;
    private final ParseMode parseMode;
    private final Boolean disableWebPagePreview;

    /**
     * @param text Text of the message to be sent, 1-4096 characters
     */
    public InputTextMessageContent(String text) {
        this(text, null, null);
    }

    /**
     * @param text                  Text of the message to be sent, 1-4096 characters
     * @param parseMode             Optional. Send Markdown or HTML, if you want Telegram apps to show bold, italic,
     *                              fixed-width text or inline URLs in your bot's message.
     * @param disableWebPagePreview Optional. Disables link previews for links in the sent message
     */
    public InputTextMessageContent(String text, ParseMode parseMode, Boolean disableWebPagePreview) {
        checkArgument(!isNullOrEmpty(text), "Text of message should be provided");
        this.text = text.length() > 4096 ? text.substring(0, 4096) : text;
        this.parseMode = parseMode;
        this.disableWebPagePreview = disableWebPagePreview;
    }

    @JsonProperty("message_text")
    public String getText() {
        return text;
    }

    @JsonProperty("parse_mode")
    public ParseMode getParseMode() {
        return parseMode;
    }

    @JsonProperty("disable_web_page_preview")
    public Boolean getDisableWebPagePreview() {
        return disableWebPagePreview;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJson();
    }

}
