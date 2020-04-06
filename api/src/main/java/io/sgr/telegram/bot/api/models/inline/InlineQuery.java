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

import static io.sgr.telegram.bot.api.utils.Preconditions.notEmptyString;
import static io.sgr.telegram.bot.api.utils.Preconditions.notNull;

import io.sgr.telegram.bot.api.models.Location;
import io.sgr.telegram.bot.api.models.User;
import io.sgr.telegram.bot.api.utils.JsonUtil;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This object represents an incoming inline query. When the user sends an empty query, your bot could return some
 * default or trending results.
 *
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InlineQuery {

    private final String id;
    private final User from;
    private final Location location;
    private final String query;
    private final String offset;

    /**
     * @param id       Unique identifier for this query
     * @param from     Sender
     * @param location Optional. Sender location, only for bots that request user location
     * @param query    Text of the query
     * @param offset   Optional. Offset of the results to be returned, can be controlled by the bot
     */
    @JsonCreator
    public InlineQuery(
            @JsonProperty("id") String id,
            @JsonProperty("from") User from,
            @JsonProperty("location") Location location,
            @JsonProperty("query") String query,
            @JsonProperty("offset") String offset) {
        notEmptyString(id, "Inline query ID should be provided.");
        this.id = id;
        notNull(from, "Sender should be provided.");
        this.from = from;
        this.location = location;
        notNull(query, "Text of the query should be provided.");
        this.query = query;
        this.offset = offset;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("from")
    public User getFrom() {
        return from;
    }

    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

    @JsonProperty("query")
    public String getQuery() {
        return query;
    }

    @JsonProperty("offset")
    public String getOffset() {
        return offset;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJson();
    }

}
