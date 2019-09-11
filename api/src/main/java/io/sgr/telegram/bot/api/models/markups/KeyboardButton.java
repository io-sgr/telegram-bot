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

import static io.sgr.telegram.bot.api.utils.Preconditions.notEmptyString;

import io.sgr.telegram.bot.api.utils.JsonUtil;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This object represents one button of the reply keyboard. For simple text buttons String can be used instead of this
 * object to specify text of the button. Optional fields are mutually exclusive.
 *
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KeyboardButton {

    private final String text;
    private final Boolean requestContact;
    private final Boolean requestLocation;

    /**
     * @param text            Text of the button. If none of the optional fields are used, it will be sent to the bot as
     *                        a message when the button is pressed.
     * @param requestContact  Optional. If True, the user's phone number will be sent as a contact when the button is
     *                        pressed. Available in private chats only.
     * @param requestLocation Optional. If True, the user's current location will be sent when the button is pressed.
     *                        Available in private chats only.
     */
    @JsonCreator
    public KeyboardButton(
            @JsonProperty("text") String text,
            @JsonProperty("request_contact") Boolean requestContact,
            @JsonProperty("request_location") Boolean requestLocation) {
        notEmptyString(text, "Text should be provided.");
        this.text = text;
        this.requestContact = requestContact;
        this.requestLocation = requestLocation;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("request_contact")
    public Boolean getRequestContact() {
        return requestContact;
    }

    @JsonProperty("request_location")
    public Boolean getRequestLocation() {
        return requestLocation;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJson();
    }

}
