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
package io.sgr.telegram.bot.engine;

import io.sgr.telegram.bot.api.utils.Preconditions;

/**
 * @author SgrAlpha
 */
public class Command {

    public final String type;
    public final String toBot;
    public final String argument;

    /**
     * @param type     The type of command
     * @param toBot    The robot which send command to
     * @param argument argument for command
     */
    public Command(String type, String toBot, String argument) {
        if (Preconditions.isEmptyString(type)) {
            throw new IllegalArgumentException("Command type cannot be null or empty string!");
        }
        this.type = type.toLowerCase();
        this.toBot = toBot;
        this.argument = argument;
    }

}
