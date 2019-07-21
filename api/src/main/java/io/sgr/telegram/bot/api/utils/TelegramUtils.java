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

package io.sgr.telegram.bot.api.utils;

import static io.sgr.telegram.bot.api.utils.Preconditions.notEmptyString;

import java.util.regex.Pattern;

/**
 * @author SgrAlpha
 */
public class TelegramUtils {

    private static final Pattern TOKEN_PATTERN = Pattern.compile("^\\d+:[\\w-]+$");

    /**
     * Verify if the given token is valid.
     *
     * @param token
     *         The token to verify.
     */
    public static void verifyToken(final String token) {
        notEmptyString(token, "Token can not be null or empty string!");
        if (!TOKEN_PATTERN.matcher(token).matches()) {
            throw new IllegalArgumentException("Invalid token: " + token);
        }
    }

    /**
     * Parse bot ID from a full token string.
     *
     * @param token
     *         The token to parse bot ID from.
     * @return Bot ID
     */
    public static String parseBotIdFromToken(final String token) {
        verifyToken(token);
        return token.split(":", 2)[0];
    }

}
