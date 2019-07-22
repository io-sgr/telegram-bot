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

package io.sgr.telegram.bot.api.models;

import static io.sgr.telegram.bot.api.utils.Preconditions.notEmptyString;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Locale;

/**
 * @author SgrAlpha
 */
public enum ChatType {

    PRIVATE,
    GROUP,
    SUPERGROUP,
    CHANNEL,
    ;

    /**
     * Parse ChatType from string
     *
     * @param str The string to parse from
     * @return An instance of ChatType
     */
    @JsonCreator
    public static ChatType fromString(final String str) {
        notEmptyString(str, "Cannot parse ChatType from null or empty string!");
        return ChatType.valueOf(str.toUpperCase(Locale.ENGLISH));
    }

}
