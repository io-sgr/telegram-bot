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

package io.sgr.telegram.bot.api.utils.backoff;

import java.util.Optional;

import javax.annotation.Nullable;

public class FixedBackOff implements BackOff {

    /**
     * The default initial interval.
     */
    public static final long DEFAULT_INTERVAL = 1000L;

    /**
     * The default maximum elapsed time.
     */
    public static final long DEFAULT_MAX_ELAPSED_TIME = Long.MAX_VALUE;

    private static final FixedBackOff DEFAULT = new FixedBackOff();

    private final long internal;
    private final long maxElapsedTime;

    private FixedBackOff() {
        this(DEFAULT_INTERVAL, DEFAULT_MAX_ELAPSED_TIME);
    }

    public FixedBackOff(@Nullable final Long internal, @Nullable final Long maxElapsedTime) {
        this.internal = Optional.ofNullable(internal).filter(i -> i > 0).orElse(DEFAULT_INTERVAL);
        this.maxElapsedTime = Optional.ofNullable(maxElapsedTime).filter(elapsed -> elapsed > 0).orElse(DEFAULT_MAX_ELAPSED_TIME);
    }

    public static FixedBackOff getDefault() {
        return DEFAULT;
    }

    @Override
    public BackOffHandler start() {
        return new FixedBackOffHandler();
    }

    public long getInterval() {
        return internal;
    }

    @Override
    public long getMaxElapsedTime() {
        return maxElapsedTime;
    }

    private class FixedBackOffHandler implements BackOffHandler {

        private long currentElapsedTime = 0;

        private FixedBackOffHandler() {
        }

        @Override
        public long nextBackOff() {
            if (this.currentElapsedTime >= getMaxElapsedTime()) {
                return STOP;
            }
            final long nextInterval = getInterval();
            currentElapsedTime += nextInterval;
            return nextInterval;
        }

    }

}