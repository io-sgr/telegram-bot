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

package io.sgr.telegram.bot.api.http;

import static io.sgr.telegram.bot.api.utils.Preconditions.notNull;

import io.sgr.telegram.bot.api.exceptions.ApiCallException;
import io.sgr.telegram.bot.api.models.http.ApiErrorResponse;
import io.sgr.telegram.bot.api.models.http.ApiResponse;
import io.sgr.telegram.bot.api.utils.JsonUtil;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;

class CompletableFutureBasedCallback<T> implements Callback<ApiResponse<T>> {

    private final CompletableFuture<T> future;
    private final boolean retry;
    private final Logger logger;

    CompletableFutureBasedCallback(@Nonnull final CompletableFuture<T> future, final boolean retry, @Nonnull final Logger logger) {
        notNull(future, "Missing future!");
        this.future = future;
        this.retry = retry;
        notNull(logger, "Missing logger!");
        this.logger = logger;
    }

    @Override public void onResponse(@Nonnull final Call<ApiResponse<T>> call, @Nonnull final Response<ApiResponse<T>> response) {
        if (response.isSuccessful()) {
            final ApiResponse<T> rawBody = response.body();
            if (rawBody == null) {
                future.completeExceptionally(new IllegalStateException("Missing response body!"));
                return;
            }
            future.complete(rawBody.getResult());
            return;
        }
        final ResponseBody errorBody = response.errorBody();
        ApiErrorResponse apiError = null;
        if (errorBody != null) {
            try {
                apiError = JsonUtil.getObjectMapper().readValue(errorBody.bytes(), ApiErrorResponse.class);
            } catch (IOException e) {
                logger.error("{}: {}", e.getClass().getSimpleName(), e.getMessage());
            }
        }
        final String errDesc = apiError == null ? "NA" : apiError.getDescription().orElse("NA");
        if (response.code() >= 400 && response.code() < 500) {
            if (response.code() == 409) {
                future.completeExceptionally(new ApiCallException(apiError));
                return;
            }
            if (response.code() == 429) {
                int index = errDesc.lastIndexOf(' ');
                int time;
                try {
                    time = Integer.parseInt(errDesc.substring(index));
                } catch (Exception e) {
                    time = 3;
                }
                try {
                    TimeUnit.SECONDS.sleep(time);
                } catch (InterruptedException e) {
                    future.completeExceptionally(e);
                    return;
                }
                call.clone().enqueue(this);
                return;
            }
            logger.error("Error '{}:{}' received.", response.code(), errDesc);
            future.completeExceptionally(new ApiCallException(apiError));
            return;
        }
        if (retry) {
            logger.error("Error '{}:{}' received.", response.code(), errDesc);
            future.completeExceptionally(new ApiCallException(apiError));
            return;
        }
        logger.error("Error '{}:{}' received, retrying ...", response.code(), errDesc);
        call.clone().enqueue(this);
    }

    @Override public void onFailure(@Nonnull final Call<ApiResponse<T>> call, @Nonnull final Throwable t) {
        if (!retry) {
            future.completeExceptionally(t);
            return;
        }
        // Ignore client read timeout
        if ("timeout".equalsIgnoreCase(t.getMessage())) {
            future.completeExceptionally(t);
            return;
        }
        logger.error("{} '{}' received when sending message.", t.getClass().getSimpleName(), t.getMessage());
        call.clone().enqueue(this);
    }

}
