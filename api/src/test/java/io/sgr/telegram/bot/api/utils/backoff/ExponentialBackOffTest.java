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

import static io.sgr.telegram.bot.api.utils.backoff.BackOffHandler.STOP;
import static io.sgr.telegram.bot.api.utils.backoff.ExponentialBackOff.DEFAULT_INITIAL_INTERVAL;
import static io.sgr.telegram.bot.api.utils.backoff.ExponentialBackOff.DEFAULT_MAX_INTERVAL;
import static io.sgr.telegram.bot.api.utils.backoff.ExponentialBackOff.DEFAULT_MULTIPLIER;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ExponentialBackOffTest {

    @Test
    public void testDefault() {
        long interval = DEFAULT_INITIAL_INTERVAL;
        BackOffHandler handler = ExponentialBackOff.getDefault().start();
        for (int i = 1; i <= 12; i++) {
            if (i <= 11) {
                assertEquals(interval, handler.nextBackOff());
                interval *= DEFAULT_MULTIPLIER;
            } else {
                assertEquals(DEFAULT_MAX_INTERVAL, handler.nextBackOff());
            }
        }
    }

    @Test
    public void testCustomization() {
        long interval = 1;
        final double multiplier = 2.0;
        final long maxInterval = 32;
        BackOffHandler handler = new ExponentialBackOff(1L, multiplier, maxInterval, 128L).start();
        for (int i = 1; i <= 12; i++) {
            if (i <= 6) {
                assertEquals(interval, handler.nextBackOff());
                interval *= multiplier;
            } else if (i <= 9) {
                assertEquals(maxInterval, handler.nextBackOff());
            } else {
                assertEquals(STOP, handler.nextBackOff());
            }
        }
    }

}