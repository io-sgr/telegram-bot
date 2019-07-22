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

import static io.sgr.telegram.bot.engine.utils.CommandUtil.parseCommandFromText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import io.sgr.telegram.bot.engine.Command;
import org.junit.Test;

public class CommandUtilTest {

    @Test
    public void testParseCommand() {
        Command cmd;

        cmd = parseCommandFromText(null);
        assertNull(cmd);

        cmd = parseCommandFromText("\n");
        assertNull(cmd);

        cmd = parseCommandFromText("//");
        assertNull(cmd);

        cmd = parseCommandFromText("/ ");
        assertNull(cmd);

        cmd = parseCommandFromText("/ /aaa");
        assertNull(cmd);

        cmd = parseCommandFromText("/@ /aaa");
        assertNull(cmd);

        cmd = parseCommandFromText("/@bot /aaa");
        assertNull(cmd);

        cmd = parseCommandFromText("/aaa@bbb ccc");
        assertNull(cmd);

        cmd = parseCommandFromText("/hello");
        assertNotNull(cmd);
        assertEquals("hello", cmd.type);
        assertNull(cmd.toBot);
        assertNull(cmd.argument);

        cmd = parseCommandFromText("/hello ");
        assertNotNull(cmd);
        assertEquals("hello", cmd.type);
        assertNull(cmd.toBot);
        assertEquals("", cmd.argument);

        cmd = parseCommandFromText("/hello Jarvis");
        assertNotNull(cmd);
        assertEquals("hello", cmd.type);
        assertNull(cmd.toBot);
        assertEquals(cmd.argument, "Jarvis");

        cmd = parseCommandFromText("/hello@enl_jarvis_bot");
        assertNotNull(cmd);
        assertEquals("hello", cmd.type);
        assertEquals(cmd.toBot, "enl_jarvis_bot");
        assertNull(cmd.argument);

        cmd = parseCommandFromText("/hello@enl_jarvis_bot ");
        assertNotNull(cmd);
        assertEquals("hello", cmd.type);
        assertEquals(cmd.toBot, "enl_jarvis_bot");
        assertEquals("", cmd.argument);

        cmd = parseCommandFromText("/hello@enl_jarvis_bot Jarvis");
        assertNotNull(cmd);
        assertEquals("hello", cmd.type);
        assertEquals(cmd.toBot, "enl_jarvis_bot");
        assertEquals(cmd.argument, "Jarvis");

    }

}
