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

import io.sgr.telegram.bot.api.models.http.ApiResponse;

import org.slf4j.Logger;
import retrofit2.Call;
import retrofit2.CallAdapter;

import java.lang.reflect.Type;
import java.util.concurrent.CompletableFuture;

import javax.annotation.Nonnull;

class CompletableFutureBasedCallAdapter<T> implements CallAdapter<ApiResponse<T>, CompletableFuture<T>> {

    private final Type responseType;
    private final boolean retry;
    private final Logger logger;

    CompletableFutureBasedCallAdapter(@Nonnull final Type responseType, final boolean retry, @Nonnull final Logger logger) {
        notNull(responseType, "Missing response type!");
        this.responseType = responseType;
        this.retry = retry;
        notNull(logger, "Missing logger!");
        this.logger = logger;
    }

    @Nonnull
    @Override
    public Type responseType() {
        return responseType;
    }

    @Nonnull
    @Override
    public CompletableFuture<T> adapt(@Nonnull final Call<ApiResponse<T>> call) {
        final CompletableFuture<T> fut = new CompletableFuture<T>() {
            @Override public boolean cancel(final boolean mayInterruptIfRunning) {
                if (mayInterruptIfRunning) {
                    call.cancel();
                }
                return super.cancel(mayInterruptIfRunning);
            }
        };
        call.enqueue(new CompletableFutureBasedCallback<>(fut, retry, logger));
        return fut;
    }

}
