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

package io.sgr.telegram.bot.api.models;

import static io.sgr.telegram.bot.api.utils.Preconditions.notNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.sgr.telegram.bot.api.utils.JsonUtil;

/**
 * This object represents a Telegram user or bot.
 *
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    private final long id;
    private final boolean isBot;
    private final String firstName;
    private final String lastName;
    private final String username;
    private final String languageCode;

    /**
     * @param id           Unique identifier for this user or bot.
     * @param isBot        True, if this user is a bot.
     * @param firstName    User's or bot's first name.
     * @param lastName     Optional. User's or bot's last name.
     * @param username     Optional. User's or bot's username.
     * @param languageCode Optional. IETF language tag of the user's language.
     */
    @JsonCreator
    public User(
            @JsonProperty("id") Long id,
            @JsonProperty("is_bot") boolean isBot,
            @JsonProperty("first_name") String firstName,
            @JsonProperty("last_name") String lastName,
            @JsonProperty("username") String username,
            @JsonProperty("language_code") String languageCode) {
        notNull(id, "User's or bot's ID should be provided.");
        this.id = id;
        this.isBot = isBot;
        notNull(firstName, "User's or bot's first name should be provided.");
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.languageCode = languageCode;
    }

    @JsonProperty("id")
    public long getId() {
        return id;
    }

    @JsonProperty("is_bot")
    public boolean isBot() {
        return isBot;
    }

    @JsonProperty("first_name")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("language_code")
    public String getLanguageCode() {
        return languageCode;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJson();
    }

}
