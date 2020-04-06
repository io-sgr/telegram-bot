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

package io.sgr.telegram.bot.engine.utils;

import static io.sgr.telegram.bot.engine.utils.ExponentialBackOff.DEFAULT_INITIAL_INTERVAL_IN_MILLI;
import static io.sgr.telegram.bot.engine.utils.ExponentialBackOff.DEFAULT_MAX_INTERVAL_IN_MILLI;
import static io.sgr.telegram.bot.engine.utils.ExponentialBackOff.DEFAULT_MULTIPLIER;
import static io.sgr.telegram.bot.engine.utils.ExponentialBackOff.DEFAULT_RANDOMIZATION_FACTOR;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.Random;

public class ExponentialBackOffTest {

    @Test
    public void testGetInterval() {
        final ExponentialBackOff backOff = ExponentialBackOff.newInstance();
        assertEquals(DEFAULT_INITIAL_INTERVAL_IN_MILLI, backOff.getInitialIntervalInMilli());
        assertEquals(DEFAULT_RANDOMIZATION_FACTOR, backOff.getRandomizationFactor(), 0);
        assertEquals(DEFAULT_MULTIPLIER, backOff.getMultiplier(), 0);
        assertEquals(DEFAULT_MAX_INTERVAL_IN_MILLI, backOff.getMaxIntervalInMilli());

        long currentInterval = backOff.getCurrentInterval();
        long nextBackOff = backOff.getNextBackOffInMilli();
        assertEquals(currentInterval, nextBackOff, currentInterval * backOff.getRandomizationFactor());
        long lastInterval = currentInterval;

        int round = new Random().nextInt(100) + 100;  // At least 100 rounds.
        while (round > 0) {
            currentInterval = backOff.getCurrentInterval();
            nextBackOff = backOff.getNextBackOffInMilli();
            if (lastInterval != backOff.getMaxIntervalInMilli()) {
                assertTrue(String.format("Round %d, last interval is %d but next back off is %d", round, lastInterval, nextBackOff),
                        nextBackOff > lastInterval);
            }
            double delta = currentInterval * backOff.getRandomizationFactor();
            assertTrue(String.format("Next backoff %d is smaller than expected %d minus delta %f", nextBackOff, currentInterval, delta),
                    nextBackOff >= currentInterval - delta + 1);
            assertTrue(String.format("Next backoff %d is larger than expected %d plus delta %f", nextBackOff, currentInterval, delta),
                    nextBackOff <= currentInterval + delta + 1);
            lastInterval = currentInterval;
            round--;
        }
    }

}
