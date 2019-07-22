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

package io.sgr.telegram.bot.api.models.http;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

/**
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiErrorResponse {

    private final boolean ok;
    private final String description;
    private final ResponseParameters parameters;

    /**
     * @param ok          The request was successful or not.
     * @param description Optional. A human-readable explanation of the error.
     * @param parameters  Optional. Information about why a request was unsuccessful, which can help to automatically
     *                    handle the error.
     */
    @JsonCreator
    public ApiErrorResponse(
            @JsonProperty("ok") boolean ok,
            @JsonProperty("description") String description,
            @JsonProperty("parameters") ResponseParameters parameters) {
        this.ok = ok;
        this.description = description;
        this.parameters = parameters;
    }

    /**
     * @return The request was successful or not
     */
    public boolean isOk() {
        return this.ok;
    }

    /**
     * @return A human-readable explanation of the error.
     */
    public Optional<String> getDescription() {
        return Optional.ofNullable(this.description);
    }

    /**
     * @return Information about why a request was unsuccessful, which can help to automatically handle the error.
     */
    public Optional<ResponseParameters> getParameters() {
        return Optional.ofNullable(this.parameters);
    }

}
