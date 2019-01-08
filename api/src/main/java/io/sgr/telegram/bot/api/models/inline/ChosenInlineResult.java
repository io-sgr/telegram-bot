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
package io.sgr.telegram.bot.api.models.inline;

import static io.sgr.telegram.bot.api.utils.Preconditions.notNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.sgr.telegram.bot.api.models.Location;
import io.sgr.telegram.bot.api.models.User;
import io.sgr.telegram.bot.api.utils.JsonUtil;

/**
 * Represents a result of an inline query that was chosen by the user and sent to their chat partner.
 *
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChosenInlineResult {

    private final String resultId;
    private final User from;
    private final Location location;
    private final String inlineMessageId;
    private final String query;

    /**
     * @param resultId        The unique identifier for the result that was chosen.
     * @param from            The user that chose the result.
     * @param location        Optional. Sender location, only for bots that require user location.
     * @param inlineMessageId Optional. Identifier of the sent inline message. Available only if there is an inline
     *                        keyboard attached to the message. Will be also received in callback queries and can be
     *                        used to edit the message.
     * @param query           The query that was used to obtain the result.
     */
    @JsonCreator
    public ChosenInlineResult(
            @JsonProperty("result_id") String resultId,
            @JsonProperty("from") User from,
            @JsonProperty("location") Location location,
            @JsonProperty("inline_message_id") String inlineMessageId,
            @JsonProperty("query") String query) {
        this.resultId = resultId;
        notNull(from, "The user that chose the result cannot be null!");
        this.from = from;
        this.location = location;
        this.inlineMessageId = inlineMessageId;
        notNull(query, "The query that was used to obtain the result cannot be null!");
        this.query = query;
    }

    @JsonProperty("result_id")
    public String getResultId() {
        return resultId;
    }

    @JsonProperty("from")
    public User getFrom() {
        return from;
    }

    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

    @JsonProperty("inline_message_id")
    public String getInlineMessageId() {
        return inlineMessageId;
    }

    @JsonProperty("query")
    public String getQuery() {
        return query;
    }

    public String toJSON() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJSON();
    }

}
