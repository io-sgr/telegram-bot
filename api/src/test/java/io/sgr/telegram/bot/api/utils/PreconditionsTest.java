/*
 * Copyright 2017-2018 SgrAlpha
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

package io.sgr.telegram.bot.api.utils;

import static io.sgr.telegram.bot.api.utils.Preconditions.isEmptyString;
import static io.sgr.telegram.bot.api.utils.Preconditions.notEmptyString;
import static io.sgr.telegram.bot.api.utils.Preconditions.notNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class PreconditionsTest {

    @Test
    public void testBlankErrorMessage() {
        try {
            notNull(null, "");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(Preconditions.DEFAULT_ERROR_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void testCheckNull() {
        try {
            notNull(null, "err msg");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("err msg", e.getMessage());
        }
        notNull("", "err?");
        notNull("\n", "err?");
        notNull("\n\n\n", "err?");
        notNull("abc", "err?");
    }

    @Test
    public void testCheckString() {
        assertTrue(isEmptyString(null));
        assertTrue(isEmptyString(""));
        assertTrue(isEmptyString("\n"));
        assertTrue(isEmptyString("\n\n\n"));
        assertTrue(isEmptyString(" "));
        assertTrue(isEmptyString(" \n"));
        assertFalse(isEmptyString("abd"));
        try {
            notEmptyString(null, "err msg");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("err msg", e.getMessage());
        }
        try {
            notEmptyString("", "err msg");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("err msg", e.getMessage());
        }
        try {
            notEmptyString("\n", "err msg");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("err msg", e.getMessage());
        }
        try {
            notEmptyString("\n\n\n", "err msg");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("err msg", e.getMessage());
        }
        notEmptyString("abd", "err?");
    }

}
