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

package io.sgr.telegram.bot.api.http;

import static io.sgr.telegram.bot.api.utils.Preconditions.notNull;

import io.sgr.telegram.bot.api.models.http.ApiResponse;

import org.slf4j.Logger;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.CompletableFuture;

import javax.annotation.Nonnull;

public class DefaultCallAdapterFactory extends CallAdapter.Factory {

    private final boolean retry;
    private final Logger logger;

    public DefaultCallAdapterFactory(final boolean retry, @Nonnull final Logger logger) {
        this.retry = retry;
        notNull(logger, "Missing logger!");
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
        return new CompletableFutureBasedCallAdapter<>(new ApiResponseType(innerType), retry, logger);
    }

    static final class ApiResponseType implements ParameterizedType {

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

}
