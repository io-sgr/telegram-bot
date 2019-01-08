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

package io.sgr.telegram.bot.engine.utils;

/**
 * @author SgrAlpha
 */
public class ExponentialBackOff implements BackOff {

    public static final long DEFAULT_INITIAL_INTERVAL_IN_MILLI = 100;
    public static final double DEFAULT_MULTIPLIER = 1.5;
    public static final double DEFAULT_RANDOMIZATION_FACTOR = 0.2;
    public static final long DEFAULT_MAX_INTERVAL_IN_MILLI = 60000;

    private final long initialIntervalInMilli;
    private final double multiplier;
    private final double randomizationFactor;
    private final long maxIntervalInMilli;

    private long currentInterval;

    public ExponentialBackOff(final long initialIntervalInMilli, final double multiplier, final double randomizationFactor, final long maxIntervalInMilli) {
        if (initialIntervalInMilli <= 0) {
            throw new IllegalArgumentException(String.format("Initial interval should be greater than 0, but got %d", initialIntervalInMilli));
        }
        this.initialIntervalInMilli = initialIntervalInMilli;
        if (multiplier <= 0) {
            throw new IllegalArgumentException(String.format("Multiplier should be greater than 0, but got %f", multiplier));
        }
        this.multiplier = multiplier;
        if (randomizationFactor <= 0) {
            throw new IllegalArgumentException(String.format("Randomization factor should be greater than 0, but got %f", randomizationFactor));
        }
        this.randomizationFactor = randomizationFactor;
        if (maxIntervalInMilli <= 0) {
            throw new IllegalArgumentException(String.format("Maximum interval should be greater than 0, but got %d", maxIntervalInMilli));
        }
        if (maxIntervalInMilli <= initialIntervalInMilli) {
            throw new IllegalArgumentException(String.format("Maximum interval should be greater than initial interval, but got %d", maxIntervalInMilli));
        }
        this.maxIntervalInMilli = maxIntervalInMilli;
        this.currentInterval = initialIntervalInMilli;
    }

    public static ExponentialBackOff getDefault() {
        return new ExponentialBackOff(DEFAULT_INITIAL_INTERVAL_IN_MILLI, DEFAULT_MULTIPLIER, DEFAULT_RANDOMIZATION_FACTOR, DEFAULT_MAX_INTERVAL_IN_MILLI);
    }

    @Override public long getNextBackOffInMilli() {
        double delta = currentInterval * randomizationFactor;
        double minInterval = currentInterval - delta;
        double maxInterval = currentInterval + delta;
        long nextInterval = (long) (minInterval + Math.random() * (maxInterval - minInterval + 1)) + 1;

        if (currentInterval >= maxIntervalInMilli / multiplier) {
            currentInterval = maxIntervalInMilli;
        }
        return nextInterval;
    }

    @Override public void reset() {
        currentInterval = initialIntervalInMilli;
    }

    public final long getInitialIntervalInMilli() {
        return initialIntervalInMilli;
    }

    public final double getMultiplier() {
        return multiplier;
    }

    public final double getRandomizationFactor() {
        return randomizationFactor;
    }

    public final long getMaxIntervalInMilli() {
        return maxIntervalInMilli;
    }

    public final long getCurrentInterval() {
        return currentInterval;
    }
}
