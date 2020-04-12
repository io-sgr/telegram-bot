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

import io.sgr.telegram.bot.api.utils.JsonUtil;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TelegramError {

    private final boolean ok;
    private final int errorCode;
    private final String description;

    /**
     * @param ok          The status of the result.
     * @param errorCode   The error code.
     * @param description The description of the error.
     */
    @JsonCreator
    public TelegramError(
            @JsonProperty("ok") boolean ok,
            @JsonProperty("error_code") int errorCode,
            @JsonProperty("description") String description) {
        this.ok = ok;
        this.errorCode = errorCode;
        this.description = description;
    }

    /**
     * @return the ok
     */
    @JsonProperty("ok")
    public boolean isOk() {
        return this.ok;
    }

    /**
     * @return the errorCode
     */
    @JsonProperty("error_code")
    public int getErrorCode() {
        return this.errorCode;
    }

    /**
     * @return the description
     */
    @JsonProperty("description")
    public String getDescription() {
        return this.description;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJson();
    }

}
