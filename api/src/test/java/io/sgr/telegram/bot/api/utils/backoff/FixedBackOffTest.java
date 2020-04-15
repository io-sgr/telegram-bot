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
import static io.sgr.telegram.bot.api.utils.backoff.FixedBackOff.DEFAULT_INTERVAL;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FixedBackOffTest {

    @Test
    public void testDefault() {
        BackOffHandler handler = FixedBackOff.getDefault().start();
        for (int i = 1; i <= 10; i++) {
            assertEquals(DEFAULT_INTERVAL, handler.nextBackOff());
        }
    }

    @Test
    public void testCustomization() {
        final long interval = 10;
        BackOffHandler handler = new FixedBackOff(interval, 100L).start();
        for (int i = 1; i <= 11; i++) {
            if (i <= 10) {
                assertEquals(interval, handler.nextBackOff());
            } else {
                assertEquals(STOP, handler.nextBackOff());
            }
        }
    }

}