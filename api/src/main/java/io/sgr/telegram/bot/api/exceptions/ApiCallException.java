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

package io.sgr.telegram.bot.api.exceptions;

import io.sgr.telegram.bot.api.models.http.ApiErrorResponse;

import java.util.Optional;

public class ApiCallException extends Exception {

    private final ApiErrorResponse errorResponse;

    public ApiCallException(final ApiErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public ApiCallException(final Throwable t) {
        super(t);
        this.errorResponse = null;
    }

    @Override public String getMessage() {
        return getErrorResponse().flatMap(ApiErrorResponse::getDescription).orElse(super.getMessage());
    }

    public Optional<ApiErrorResponse> getErrorResponse() {
        return Optional.ofNullable(errorResponse);
    }

}
