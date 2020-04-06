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

import io.sgr.telegram.bot.api.utils.JsonUtil;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

/**
 * This object represents an inline keyboard that appears right next to the message it belongs to.
 *
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InlineKeyboardMarkup implements ReplyMarkup {

    private final List<InlineKeyboardButton[]> inlineKeyboard;

    /**
     * @param inlineKeyboard List of an array of button rows, each represented by an Array of InlineKeyboardButton
     *                       objects.
     */
    public InlineKeyboardMarkup(@JsonProperty("inline_keyboard") List<InlineKeyboardButton[]> inlineKeyboard) {
        this.inlineKeyboard = inlineKeyboard;
    }

    @JsonProperty("inline_keyboard")
    public List<InlineKeyboardButton[]> getInlineKeyboard() {
        return this.inlineKeyboard == null ? null : Collections.unmodifiableList(this.inlineKeyboard);
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJson();
    }

}
