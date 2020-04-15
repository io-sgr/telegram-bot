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

public class ExponentialBackOff implements BackOff {

    /**
     * The default initial interval.
     */
    public static final long DEFAULT_INITIAL_INTERVAL = 1000L;

    /**
     * The default multiplier.
     */
    public static final double DEFAULT_MULTIPLIER = 1.5;

    /**
     * The default maximum back off time.
     */
    public static final long DEFAULT_MAX_INTERVAL = 64000L;

    /**
     * The default maximum elapsed time.
     */
    public static final long DEFAULT_MAX_ELAPSED_TIME = Long.MAX_VALUE;

    private static final ExponentialBackOff DEFAULT = new ExponentialBackOff();

    private final long initialInterval;
    private final double multiplier;
    private final long maxInterval;
    private final long maxElapsedTime;

    private ExponentialBackOff() {
        this(DEFAULT_INITIAL_INTERVAL, DEFAULT_MULTIPLIER, DEFAULT_MAX_INTERVAL, DEFAULT_MAX_ELAPSED_TIME);
    }

    public ExponentialBackOff(
            @Nullable final Long initialInterval,
            @Nullable final Double multiplier,
            @Nullable final Long maxInterval,
            @Nullable final Long maxElapsedTime) {
        this.initialInterval = Optional.ofNullable(initialInterval).filter(internal -> internal > 0).orElse(DEFAULT_INITIAL_INTERVAL);
        this.multiplier = Optional.ofNullable(multiplier).filter(m -> m > 1).orElse(DEFAULT_MULTIPLIER);
        this.maxInterval = Optional.ofNullable(maxInterval).filter(internal -> internal > 0).orElse(DEFAULT_MAX_INTERVAL);
        this.maxElapsedTime = Optional.ofNullable(maxElapsedTime).filter(elapsed -> elapsed > 0).orElse(DEFAULT_MAX_ELAPSED_TIME);
    }

    public static ExponentialBackOff getDefault() {
        return DEFAULT;
    }

    @Override
    public BackOffHandler start() {
        return new ExponentialBackOffHandler();
    }

    public long getInitialInterval() {
        return initialInterval;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public long getMaxInterval() {
        return maxInterval;
    }

    @Override
    public long getMaxElapsedTime() {
        return maxElapsedTime;
    }

    private class ExponentialBackOffHandler implements BackOffHandler {

        private long currentInterval = -1;
        private long currentElapsedTime = 0;

        private ExponentialBackOffHandler() {
        }

        @Override
        public long nextBackOff() {
            if (this.currentElapsedTime >= getMaxElapsedTime()) {
                return STOP;
            }
            final long nextInterval = calculateNextInterval();
            currentElapsedTime += nextInterval;
            return nextInterval;
        }

        private long calculateNextInterval() {
            final long maxInterval = getMaxInterval();
            if (this.currentInterval >= maxInterval) {
                return maxInterval;
            }
            if (this.currentInterval < 0) {
                this.currentInterval = getInitialInterval();
            } else {
                long i = this.currentInterval;
                i *= getMultiplier();
                this.currentInterval = Math.min(i, maxInterval);
            }
            return this.currentInterval;
        }

    }

}