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
    private final int totalVoterCount;
    private final boolean closed;
    private final boolean anonymous;
    private final String type;
    private final boolean allowsMultipleAnswers;
    private final int correctOptionId;

    /**
     * @param id Unique poll identifier.
     * @param question Poll question, 1-255 characters.
     * @param options List of poll options.
     * @param totalVoterCount Total number of users that voted in the poll.
     * @param closed True, if the poll is closed.
     * @param anonymous True, if the poll is anonymous.
     * @param type Poll type, currently can be “regular” or “quiz”.
     * @param allowsMultipleAnswers True, if the poll allows multiple answers.
     * @param correctOptionId Optional. 0-based identifier of the correct answer option. Available only for polls in the quiz mode, which are closed, or was
     * sent (not forwarded) by the bot or to the private chat with the bot.
     */
    @JsonCreator
    public Poll(
            @JsonProperty("id") final String id,
            @JsonProperty("question") final String question,
            @JsonProperty("options") final List<PollOption> options,
            @JsonProperty("total_voter_count") final int totalVoterCount,
            @JsonProperty("is_closed") final boolean closed,
            @JsonProperty("is_anonymous") final boolean anonymous,
            @JsonProperty("type") final String type,
            @JsonProperty("allows_multiple_answers") final boolean allowsMultipleAnswers,
            @JsonProperty("correct_option_id") final int correctOptionId) {
        this.id = id;
        this.question = question;
        this.options = options;
        this.totalVoterCount = totalVoterCount;
        this.closed = closed;
        this.anonymous = anonymous;
        this.type = type;
        this.allowsMultipleAnswers = allowsMultipleAnswers;
        this.correctOptionId = correctOptionId;
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

    @JsonProperty("total_voter_count")
    public int getTotalVoterCount() {
        return totalVoterCount;
    }

    @JsonProperty("is_anonymous")
    public boolean isAnonymous() {
        return anonymous;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("allows_multiple_answers")
    public boolean allowsMultipleAnswers() {
        return allowsMultipleAnswers;
    }

    @JsonProperty("correct_option_id")
    public int getCorrectOptionId() {
        return correctOptionId;
    }
}
