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

import static io.sgr.telegram.bot.api.utils.Preconditions.isEmptyString;

import io.sgr.telegram.bot.engine.Command;

import java.util.regex.Pattern;

public class CommandUtil {

    private static final String CMD_PREFIX = "/";
    private static final Pattern CMD_PATTERN = Pattern.compile("^\\w+(@\\w+(bot))?$");

    /**
     * @param text The text to parse command from
     * @return An instance of command
     */
    public static Command parseCommandFromText(final String text) {
        if (isEmptyString(text) || !text.startsWith(CMD_PREFIX)) {
            return null;
        }
        String[] tmp = text.split(" ", 2);
        String argument = tmp.length > 1 ? tmp[1] : null;
        String cmdToBot = tmp[0].replaceFirst(CMD_PREFIX, "");
        if (!CMD_PATTERN.matcher(cmdToBot).matches()) {
            return null;
        }
        tmp = cmdToBot.split("@", 2);
        final String type = tmp[0];
        if (isEmptyString(type)) {
            return null;
        }
        final String toBot = tmp.length > 1 ? tmp[1] : null;
        return new Command(type, toBot, argument);
    }

}
