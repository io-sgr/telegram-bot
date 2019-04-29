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

package io.sgr.telegram.bot.api;

import static io.sgr.telegram.bot.api.utils.Preconditions.notNull;
import static io.sgr.telegram.bot.api.utils.TelegramUtils.verifyToken;

import io.sgr.telegram.bot.api.exceptions.ApiCallException;
import io.sgr.telegram.bot.api.models.http.ApiErrorResponse;
import io.sgr.telegram.bot.api.models.http.ApiResponse;
import io.sgr.telegram.bot.api.utils.JsonUtil;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;

public class BotApiBuilder {

    private static final String BASE_URL_FORMAT = "https://api.telegram.org/bot%s";

    private final String botApiToken;
    private boolean retry = false;
    private Logger logger;

    public BotApiBuilder(final String botApiToken) {
        verifyToken(botApiToken);
        this.botApiToken = botApiToken;
    }

    /**
     * Enable retry
     *
     * @return the builder
     */
    public BotApiBuilder enableRetry() {
        this.retry = true;
        return this;
    }

    /**
     * @param logger the logger
     *
     * @return the builder
     */
    public BotApiBuilder setLogger(final Logger logger) {
        this.logger = logger;
        return this;
    }

    /**
     * @return the bot API client
     */
    public BotApi build() {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(String.format(Locale.ENGLISH, BASE_URL_FORMAT, botApiToken))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(JsonUtil.getObjectMapper()))
                .addCallAdapterFactory(new DefaultCallAdapterFactory(retry, logger));
        OkHttpClient.Builder clientBuilder = new OkHttpClient().newBuilder()
                .retryOnConnectionFailure(true)
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES);
        retrofitBuilder.client(clientBuilder.build());
        return retrofitBuilder.build().create(BotApi.class);
    }

    private static final class DefaultCallAdapterFactory extends CallAdapter.Factory {

        private final boolean retry;
        private final Logger logger;

        private DefaultCallAdapterFactory(final boolean retry, final Logger logger) {
            this.retry = retry;
            this.logger = logger;
        }

        @Override
        public CallAdapter<?, ?> get(@Nonnull final Type returnType, @Nonnull final Annotation[] annotations, @Nonnull final Retrofit retrofit) {
            if (getRawType(returnType) != CompletableFuture.class) {
                return null;
            }
            if (!(returnType instanceof ParameterizedType)) {
                throw new IllegalStateException("CompletableFuture return type must be parameterized as"
                        + " CompletableFuture<Foo> or CompletableFuture<? extends Foo>");
            }
            final Type innerType = getParameterUpperBound(0, (ParameterizedType) returnType);
            return new DefaultCallAdapter<>(new ApiResponseType(innerType), retry, logger);
        }

    }

    private static final class ApiResponseType implements ParameterizedType {

        private final Type innerType;

        private ApiResponseType(final Type innerType) {
            this.innerType = innerType;
        }

        @Override public Type[] getActualTypeArguments() {
            return new Type[]{innerType};
        }

        @Override public Type getRawType() {
            return ApiResponse.class;
        }

        @Override public Type getOwnerType() {
            return Object.class;
        }

    }

    private static final class DefaultCallAdapter<T> implements CallAdapter<ApiResponse<T>, CompletableFuture<T>> {

        private final Type responseType;
        private final boolean retry;
        private final Logger logger;

        private DefaultCallAdapter(final Type responseType, final boolean retry, final Logger logger) {
            notNull(responseType, "Missing response type!");
            this.responseType = responseType;
            this.retry = retry;
            this.logger = logger;
        }

        @Override public Type responseType() {
            return responseType;
        }

        @Override public CompletableFuture<T> adapt(final Call<ApiResponse<T>> call) {
            final CompletableFuture<T> fut = new CompletableFuture<T>() {
                @Override public boolean cancel(final boolean mayInterruptIfRunning) {
                    if (mayInterruptIfRunning) {
                        call.cancel();
                    }
                    return super.cancel(mayInterruptIfRunning);
                }
            };
            call.enqueue(new Callback<ApiResponse<T>>() {
                @Override
                public void onResponse(@Nonnull final Call<ApiResponse<T>> call, @Nonnull final Response<ApiResponse<T>> response) {
                    if (response.isSuccessful()) {
                        final ApiResponse<T> rawBody = response.body();
                        if (rawBody == null) {
                            fut.completeExceptionally(new IllegalStateException("Missing response body!"));
                            return;
                        }
                        fut.complete(rawBody.getResult());
                        return;
                    }
                    final ResponseBody errorBody = response.errorBody();
                    ApiErrorResponse apiError = null;
                    if (errorBody != null) {
                        try {
                            apiError = JsonUtil.getObjectMapper().readValue(errorBody.bytes(), ApiErrorResponse.class);
                        } catch (IOException e) {
                            getLogger().error("{}: {}", e.getClass().getSimpleName(), e.getMessage());
                        }
                    }
                    final String errDesc = apiError == null ? "NA" : apiError.getDescription().orElse("NA");
                    if (response.code() >= 400 && response.code() < 500) {
                        if (response.code() == 409) {
                            fut.completeExceptionally(new ApiCallException(apiError));
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
                                fut.completeExceptionally(e);
                                return;
                            }
                            call.clone().enqueue(this);
                            return;
                        }
                        getLogger().error("Error '{}:{}' received.", response.code(), errDesc);
                        fut.completeExceptionally(new ApiCallException(apiError));
                        return;
                    }
                    if (retry) {
                        getLogger().error("Error '{}:{}' received.", response.code(), errDesc);
                        fut.completeExceptionally(new ApiCallException(apiError));
                        return;
                    }
                    getLogger().error("Error '{}:{}' received, retrying ...", response.code(), errDesc);
                    call.clone().enqueue(this);
                }

                @Override public void onFailure(@Nonnull final Call<ApiResponse<T>> call, @Nonnull final Throwable t) {
                    if (!retry) {
                        fut.completeExceptionally(t);
                        return;
                    }
                    // Ignore client read timeout
                    if ("timeout".equalsIgnoreCase(t.getMessage())) {
                        fut.completeExceptionally(t);
                        return;
                    }
                    getLogger().error("{} '{}' received when sending message.", t.getClass().getSimpleName(), t.getMessage());
                    call.clone().enqueue(this);
                }
            });
            return fut;
        }

        public Logger getLogger() {
            return Optional.ofNullable(logger).orElse(LoggerFactory.getLogger(BotApi.class));
        }

    }

}
