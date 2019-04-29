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

package io.sgr.telegram.bot.api.models.http;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseParameters {

    private final Long migrateToChatId;
    private final Integer retryAfter;

    /**
     * @param migrateToChatId Optional. The group has been migrated to a supergroup with the specified identifier.
     * @param retryAfter      Optional. In case of exceeding flood control, the number of seconds left to wait before
     *                        the request can be repeated
     */
    @JsonCreator
    public ResponseParameters(
            @JsonProperty("migrate_to_chat_id") Long migrateToChatId,
            @JsonProperty("retry_after") Integer retryAfter) {
        if (migrateToChatId == null && retryAfter == null) {
            throw new IllegalArgumentException("A ResponseParameters without both migrateToChatId and retryAfter does not make any sense");
        }
        this.migrateToChatId = migrateToChatId;
        if (retryAfter <= 0) {
            throw new IllegalArgumentException("The retryAfter should be greater than 0");
        }
        this.retryAfter = retryAfter;
    }

    /**
     * @return the migrateToChatId
     */
    public Long getMigrateToChatId() {
        return this.migrateToChatId;
    }

    /**
     * @return the retryAfter
     */
    public Integer getRetryAfter() {
        return this.retryAfter;
    }

}
