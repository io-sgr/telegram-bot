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

package io.sgr.telegram.bot.api.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

/**
 * This object contains information about a poll.
 *
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Poll {

    private final String id;
    private final String question;
    private final List<PollOption> options;
    private final boolean closed;

    @JsonCreator
    public Poll(
            @JsonProperty("id") final String id,
            @JsonProperty("question") final String question,
            @JsonProperty("options") final List<PollOption> options,
            @JsonProperty("is_closed") final boolean closed) {
        this.id = id;
        this.question = question;
        this.options = options;
        this.closed = closed;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("question")
    public String getQuestion() {
        return question;
    }

    @JsonProperty("options")
    public List<PollOption> getOptions() {
        return Collections.unmodifiableList(options);
    }

    @JsonProperty("is_closed")
    public boolean isClosed() {
        return closed;
    }

}
