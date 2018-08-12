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
package io.sgr.telegram.bot.api.models;

import static io.sgr.telegram.bot.api.utils.Preconditions.notEmptyString;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.sgr.telegram.bot.api.utils.Preconditions;

/**
 * @author SgrAlpha
 */
public enum ChatMemberStatus {

    CREATOR,
    ADMINISTRATOR,
    MEMBER,
    RESTRICTED,
    LEFT,
    KICKED,
    ;

    /**
     * Parse ChatMemberStatus from string
     *
     * @param str The string to parse from
     * @return An instance of ChatMemberStatus
     */
    @JsonCreator
    public static ChatMemberStatus fromString(String str) {
        notEmptyString(str, "Cannot parse ChatMemberStatus from null or empty string!");
        return ChatMemberStatus.valueOf(str.toUpperCase());
    }

}
