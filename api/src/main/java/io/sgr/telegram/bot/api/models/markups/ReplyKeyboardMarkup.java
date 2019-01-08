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
 */
package io.sgr.telegram.bot.api.models.markups;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.sgr.telegram.bot.api.utils.JsonUtil;
import io.sgr.telegram.bot.api.utils.Preconditions;

import java.util.Collections;
import java.util.List;

/**
 * This object represents a custom keyboard with reply options.
 *
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReplyKeyboardMarkup implements ReplyMarkup {

    private final List<KeyboardButton[]> keyboard;
    private final Boolean resizeKeyboard;
    private final Boolean oneTimeKeyboard;
    private final Boolean selective;

    /**
     * @param keyboard        Array of button rows, each represented by an Array of KeyboardButton objects.
     * @param resizeKeyboard  Optional. Requests clients to resize the keyboard vertically for optimal fit (e.g., make
     *                        the keyboard smaller if there are just two rows of buttons). Defaults to false, in which
     *                        case the custom keyboard is always of the same height as the app's standard keyboard.
     * @param oneTimeKeyboard Optional. Requests clients to hide the keyboard as soon as it's been used. The keyboard
     *                        will still be available, but clients will automatically display the usual letter-keyboard
     *                        in the chat – the user can press a special button in the input field to see the custom
     *                        keyboard again. Defaults to false.
     * @param selective       Optional. Use this parameter if you want to force reply from specific users only. Targets:
     *                        1) users that are @mentioned in the text of the Message object; 2) if the bot's message is
     *                        a reply (has reply_to_message_id), sender of the original message.
     */
    public ReplyKeyboardMarkup(
            @JsonProperty("keyboard") List<KeyboardButton[]> keyboard,
            @JsonProperty("resize_keyboard") Boolean resizeKeyboard,
            @JsonProperty("one_time_Keyboard") Boolean oneTimeKeyboard,
            @JsonProperty("selective") Boolean selective) {
        Preconditions.notNull(keyboard, "Keyboard should be provided.");
        if (keyboard.isEmpty()) {
            throw new IllegalArgumentException("Keyboard should have at least one KeyButton.");
        }
        this.keyboard = keyboard;
        this.resizeKeyboard = resizeKeyboard;
        this.oneTimeKeyboard = oneTimeKeyboard;
        this.selective = selective;
    }

    @JsonProperty("keyboard")
    public List<KeyboardButton[]> getKeyboard() {
        return this.keyboard == null ? null : Collections.unmodifiableList(this.keyboard);
    }

    @JsonProperty("resize_keyboard")
    public Boolean getResizeKeyboard() {
        return this.resizeKeyboard;
    }

    @JsonProperty("one_time_Keyboard")
    public Boolean getOneTimeKeyboard() {
        return this.oneTimeKeyboard;
    }

    @JsonProperty("selective")
    public Boolean getSelective() {
        return this.selective;
    }

    public String toJSON() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJSON();
    }

}
