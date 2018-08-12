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

import java.util.regex.Pattern;

/**
 * @author SgrAlpha
 */
public class TelegramUtils {

    private static final Pattern TOKEN_PATTERN = Pattern.compile("^\\d+:\\w+$");

    /**
     * Parse bot ID from a full token string
     *
     * @param token The token to parse
     * @return Bot ID
     */
    public static String parseBotIDFromToken(final String token) {
        if (isEmptyString(token)) {
            throw new IllegalArgumentException("Cannot parse valid bot ID from null or empty token!");
        }
        if (!TOKEN_PATTERN.matcher(token).matches()) {
            throw new IllegalArgumentException("Invalid token: " + token);
        }
        return token.split(":", 2)[0];
    }

}
