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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * This object represents an answer of a user in a non-anonymous poll.
 *
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PollAnswer {

    private final String pollId;
    private final User user;
    private final List<Integer> optionIds;

    /**
     *
     * @param pollId Unique poll identifier.
     * @param user The user, who changed the answer to the poll.
     * @param optionIds 0-based identifiers of answer options, chosen by the user. May be empty if the user retracted their vote.
     */
    @JsonCreator
    public PollAnswer(
            @JsonProperty("poll_id") final String pollId,
            @JsonProperty("user") final User user,
            @JsonProperty("option_ids") final List<Integer> optionIds) {
        this.pollId = pollId;
        this.user = user;
        this.optionIds = optionIds;
    }

    @JsonProperty("poll_id")
    public String getPollId() {
        return pollId;
    }

    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    @JsonProperty("option_ids")
    public List<Integer> getOptionIds() {
        return optionIds;
    }
}
