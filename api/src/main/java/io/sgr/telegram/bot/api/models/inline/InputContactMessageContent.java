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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.sgr.telegram.bot.api.utils.JsonUtil;
import io.sgr.telegram.bot.api.utils.Preconditions;

/**
 * Represents the content of a contact message to be sent as the result of an inline query.
 *
 * @author SgrAlpha
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InputContactMessageContent implements InputMessageContent {

    private final String phoneNumber;
    private final String firstName;
    private final String lastName;

    /**
     * @param phoneNumber Contact's phone number
     * @param firstName   Contact's first name
     * @param lastName    Optional. Contact's last name
     */
    public InputContactMessageContent(String phoneNumber, String firstName, String lastName) {
        Preconditions.notEmptyString(phoneNumber, "Contact's phone number should be provided.");
        this.phoneNumber = phoneNumber;
        Preconditions.notEmptyString(firstName, "Contact's first name should be provided.");
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @JsonProperty("phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @JsonProperty("first_name")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }

    public String toJSON() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJSON();
    }

}
