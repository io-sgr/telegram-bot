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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This object contains information about one answer option in a poll.
 *
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PollOption {

    private final String text;
    private final int voterCount;

    /**
     * @param text       Option text, 1-100 characters.
     * @param voterCount Number of users that voted for this option.
     */
    @JsonCreator
    public PollOption(
            @JsonProperty("text") final String text,
            @JsonProperty("voter_count") final int voterCount) {
        this.text = text;
        this.voterCount = voterCount;
    }

    @JsonProperty("test")
    public String getText() {
        return text;
    }

    @JsonProperty("voter_count")
    public int getVoterCount() {
        return voterCount;
    }

}
