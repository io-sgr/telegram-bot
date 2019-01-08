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
public class SteadyBackOff implements BackOff {

    public static final long DEFAULT_INTERVAL_IN_MILLI = 100;

    private final long interval;

    public SteadyBackOff(final long interval) {
        if (interval <= 0) {
            throw new IllegalArgumentException(String.format("Interval should be greater than 0, but got %d", interval));
        }
        this.interval = interval;
    }

    public static SteadyBackOff getDefault() {
        return new SteadyBackOff(DEFAULT_INTERVAL_IN_MILLI);
    }

    @Override public long getNextBackOffInMilli() {
        return this.interval;
    }

    @Override public void reset() {
    }

    public final long getInterval() {
        return interval;
    }

}
