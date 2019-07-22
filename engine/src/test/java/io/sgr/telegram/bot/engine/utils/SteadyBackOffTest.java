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

package io.sgr.telegram.bot.engine.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Random;

public class SteadyBackOffTest {

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidInterval() {
        new SteadyBackOff(-1);
    }

    @Test
    public void testGetInterval() {
        final long interval = new Random().nextInt(1000) + 1;
        SteadyBackOff backOff = new SteadyBackOff(interval);
        assertEquals(interval, backOff.getInterval());
        int round = new Random().nextInt(100) + 1;
        while (round > 0) {
            assertEquals(interval, backOff.getNextBackOffInMilli());
            round--;
        }

    }
}
