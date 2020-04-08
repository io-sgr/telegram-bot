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

import io.sgr.telegram.bot.api.utils.JsonUtil;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    private final Boolean canJoinGroups;
    private final Boolean canReadAllGroupMessages;
    private final Boolean supportInlineQueries;

    /**
     * @param id Unique identifier for this user or bot.
     * @param isBot True, if this user is a bot.
     * @param firstName User's or bot's first name.
     * @param lastName Optional. User's or bot's last name.
     * @param username Optional. User's or bot's username.
     * @param languageCode Optional. IETF language tag of the user's language.
     */
    public User(long id, boolean isBot, String firstName, String lastName, String username, String languageCode) {
        this(id, isBot, firstName, lastName, username, languageCode, null, null, null);
    }

    /**
     * @param id Unique identifier for this user or bot.
     * @param isBot True, if this user is a bot.
     * @param firstName User's or bot's first name.
     * @param lastName Optional. User's or bot's last name.
     * @param username Optional. User's or bot's username.
     * @param languageCode Optional. IETF language tag of the user's language.
     * @param canJoinGroups Optional. True, if the bot can be invited to groups. Returned only in getMe.
     * @param canReadAllGroupMessages Optional. True, if privacy mode is disabled for the bot. Returned only in getMe.
     * @param supportInlineQueries Optional. True, if the bot supports inline queries. Returned only in getMe.
     */
    @JsonCreator
    public User(
            @JsonProperty("id") final long id,
            @JsonProperty("is_bot") final boolean isBot,
            @JsonProperty("first_name") final String firstName,
            @JsonProperty("last_name") final String lastName,
            @JsonProperty("username") final String username,
            @JsonProperty("language_code") final String languageCode,
            @JsonProperty("can_join_groups") final Boolean canJoinGroups,
            @JsonProperty("can_read_all_group_messages") final Boolean canReadAllGroupMessages,
            @JsonProperty("supports_inline_queries") final Boolean supportInlineQueries) {
        this.id = id;
        this.isBot = isBot;
        notNull(firstName, "User's or bot's first name should be provided.");
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.languageCode = languageCode;
        this.canJoinGroups = canJoinGroups;
        this.canReadAllGroupMessages = canReadAllGroupMessages;
        this.supportInlineQueries = supportInlineQueries;
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

    @JsonProperty("can_join_groups")
    public Boolean canJoinGroups() {
        return canJoinGroups;
    }

    @JsonProperty("can_read_all_group_messages")
    public Boolean canReadAllGroupMessages() {
        return canReadAllGroupMessages;
    }

    @JsonProperty("supports_inline_queries")
    public Boolean supportInlineQueries() {
        return supportInlineQueries;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    @Override
    public String toString() {
        return this.toJson();
    }

}
