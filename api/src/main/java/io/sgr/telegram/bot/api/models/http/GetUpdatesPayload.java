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

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

/**
 * @author SgrAlpha
 */
public class GetUpdatesPayload {

    private final Long offset;
    private final Integer limit;
    private final Integer timeout;
    private final List<String> allowedUpdates;

    /**
     * @param offset         Optional. Identifier of the first update to be returned. Must be greater by one than the
     *                       highest among the identifiers of previously received updates. By default, updates starting
     *                       with the earliest unconfirmed update are returned. The negative offset can be specified to
     *                       retrieve updates starting from -offset update from the end of the updates queue. All
     *                       previous updates will forgotten.
     * @param limit          Optional. Limits the number of updates to be retrieved. Values between 1—100 are accepted.
     *                       Defaults to 100.
     * @param timeout        Optional. Timeout in seconds for long polling. Defaults to 0, i.e. usual short polling.
     *                       Should be positive, short polling should be used for testing purposes only.
     * @param allowedUpdates Optional. List the types of updates you want your bot to receive. For example, specify
     *                       [“message”, “edited_channel_post”, “callback_query”] to only receive updates of these
     *                       types. See Update for a complete list of available update types. Specify an empty list to
     *                       receive all updates regardless of type (default). If not specified, the previous setting
     *                       will be used.
     */
    public GetUpdatesPayload(Long offset, Integer limit, Integer timeout, List<String> allowedUpdates) {
        this.offset = offset;
        if (limit != null) {
            if (limit < 1 || limit > 100) {
                throw new IllegalArgumentException(String.format("limit should between 1 to 100 or a negative value, but got %s", limit));
            }
        }
        this.limit = limit;
        if (timeout != null) {
            if (timeout < 0) {
                throw new IllegalArgumentException("A negative timeout value does not make any sense.");
            }
        }
        this.timeout = timeout;
        this.allowedUpdates = allowedUpdates;
    }

    /**
     * @return the offset
     */
    @JsonProperty("offset")
    public Long getOffset() {
        return this.offset;
    }

    /**
     * @return the limit
     */
    @JsonProperty("limit")
    public Integer getLimit() {
        return this.limit;
    }

    /**
     * @return the timeout
     */
    @JsonProperty("timeout")
    public Integer getTimeout() {
        return this.timeout;
    }

    /**
     * @return the allowedUpdates
     */
    @JsonProperty("allowed_updates")
    public List<String> getAllowedUpdates() {
        return Collections.unmodifiableList(this.allowedUpdates);
    }

}
