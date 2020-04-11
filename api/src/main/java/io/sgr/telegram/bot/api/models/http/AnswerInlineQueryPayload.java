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

package io.sgr.telegram.bot.api.models.http;

import static io.sgr.telegram.bot.api.utils.Preconditions.notEmptyString;
import static io.sgr.telegram.bot.api.utils.Preconditions.notNull;

import io.sgr.telegram.bot.api.models.inline.InlineQueryResult;
import io.sgr.telegram.bot.api.utils.JsonUtil;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Collection;

/**
 * @author SgrAlpha
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnswerInlineQueryPayload {

    private final String queryId;
    private final Collection<InlineQueryResult> results;
    private final Integer cacheTime;
    private final Boolean personal;
    private final String nextOffset;
    private final String switchPmText;
    private final String switchPmParameter;

    /**
     * @param queryId Unique identifier for the answered query
     * @param results An array of results for the inline query. No more than 50 results per query are allowed.
     * @param cacheTime Optional. The maximum amount of time in seconds that the result of the inline query may be cached on the server. Defaults to 300.
     * @param personal Optional. Pass True, if results may be cached on the server side only for the user that sent the query. By default, results may be
     * returned to any user who sends the same query
     * @param nextOffset Optional. Pass the offset that a client should send in the next query with the same text to receive more results. Pass an empty string
     * if there are no more results or if you don‘t support
     * @param switchPmText Optional. If passed, clients will display a button with specified text that switches the user to a private chat with the bot and
     * sends the bot a start message with the parameter switch_pm_parameter.
     * @param switchPmParameter Optional. Deep-linking parameter for the /start message sent to the bot when user presses the switch button. 1-64 characters,
     * only A-Z, a-z, 0-9, _ and - are allowed.
     */
    public AnswerInlineQueryPayload(
            String queryId, Collection<InlineQueryResult> results, Integer cacheTime, Boolean personal, String nextOffset,
            String switchPmText, String switchPmParameter) {
        notEmptyString(queryId, "Answered query ID should be provided.");
        this.queryId = queryId;
        notNull(results, "Results should be provided.");
        if (results.size() > 50) {
            throw new IllegalArgumentException("No more than 50 results per query are allowed.");
        }
        this.results = results;
        this.cacheTime = cacheTime;
        this.personal = personal;
        this.nextOffset = nextOffset;
        this.switchPmText = switchPmText;
        this.switchPmParameter = switchPmParameter;
    }

    /**
     * @return the queryId
     */
    @JsonProperty("inline_query_id")
    public String getQueryId() {
        return this.queryId;
    }

    @JsonProperty("results")
    public String getResultsStr() {
        try {
            return JsonUtil.getObjectMapper().writeValueAsString(this.getResults());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "[]";
        }
    }

    /**
     * @return the results
     */
    @JsonIgnore
    public Collection<InlineQueryResult> getResults() {
        return this.results;
    }

    /**
     * @return the cacheTime
     */
    @JsonProperty("cache_time")
    public Integer getCacheTime() {
        return this.cacheTime;
    }

    /**
     * @return the personal
     */
    @JsonProperty("is_personal")
    public Boolean getPersonal() {
        return this.personal;
    }

    /**
     * @return the nextOffset
     */
    @JsonProperty("next_offset")
    public String getNextOffset() {
        return this.nextOffset;
    }

    /**
     * @return the switchPmText
     */
    @JsonProperty("switch_pm_text")
    public String getSwitchPmText() {
        return switchPmText;
    }

    /**
     * @return the switchPmParameter
     */
    @JsonProperty("switch_pm_parameter")
    public String getSwitchPmParameter() {
        return switchPmParameter;
    }
}
