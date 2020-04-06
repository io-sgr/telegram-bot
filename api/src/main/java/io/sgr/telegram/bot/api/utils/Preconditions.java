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

package io.sgr.telegram.bot.api.utils;

/**
 * @author SgrAlpha
 */
public final class Preconditions {

    public static final String DEFAULT_ERROR_MESSAGE = "Received an invalid parameter";

    /**
     * Make sure the given object is not null, otherwise an IllegalArgumentException will be thrown
     *
     * @param object   any object
     * @param errorMsg error message
     * @throws IllegalArgumentException if the object is null
     */
    public static void notNull(Object object, String errorMsg) {
        matchRequirement(object != null, errorMsg);
    }

    /**
     * Make sure the given string is not null or a blank string, otherwise an IllegalArgumentException will be thrown
     *
     * @param string   any string
     * @param errorMsg error message
     * @throws IllegalArgumentException if the string is null or a blank string
     */
    public static void notEmptyString(String string, String errorMsg) {
        matchRequirement(!isEmptyString(string), errorMsg);
    }

    /**
     * Check if the specified string is null or blank string
     *
     * @param string any string
     * @return Whether or not the given string is an empty string
     */
    public static boolean isEmptyString(String string) {
        return string == null || string.trim().isEmpty();
    }

    private static void matchRequirement(boolean requirements, String errorMsg) {
        String message = (isEmptyString(errorMsg)) ? DEFAULT_ERROR_MESSAGE : errorMsg;
        if (!requirements) {
            throw new IllegalArgumentException(message);
        }
    }

}
