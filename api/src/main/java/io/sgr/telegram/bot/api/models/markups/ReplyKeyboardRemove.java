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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.sgr.telegram.bot.api.utils.JsonUtil;

/**
 * Upon receiving a message with this object, Telegram clients will hide the current custom keyboard and display the
 * default letter-keyboard. By default, custom keyboards are displayed until a new keyboard is sent by a bot. An
 * exception is made for one-time keyboards that are hidden immediately after the user presses a button
 *
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReplyKeyboardRemove implements ReplyMarkup {

    private final Boolean selective;

    /**
     * @param selective Optional. Use this parameter if you want to force reply from specific users only. Targets: 1)
     *                  users that are @mentioned in the text of the Message object; 2) if the bot's message is a reply
     *                  (has reply_to_message_id), sender of the original message.
     */
    @JsonCreator
    public ReplyKeyboardRemove(@JsonProperty("selective") Boolean selective) {
        this.selective = selective;
    }

    @JsonProperty("remove_keyboard")
    public boolean isRemoveKeyboard() {
        return true;
    }

    @JsonProperty("selective")
    public Boolean getSelective() {
        return selective;
    }

    public String toJSON() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJSON();
    }

}
