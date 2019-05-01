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

package io.sgr.telegram.bot.ext.db;

import java.util.Map;

/**
 * @author SgrAlpha
 */
public interface UserSettingsWithMultiBotsSupport {

    /**
     * @param botId   The bot ID
     * @param userId  The user ID
     * @param propKey The property key
     * @return The property value
     */
    String getRobotSettings(long botId, long userId, String propKey);

    /**
     * @param botId  The bot ID
     * @param userId The user ID
     * @return A hash map of all properties
     */
    Map<String, String> loadRobotSettings(long botId, long userId);

    /**
     * @param botId     The bot ID
     * @param userId    The user ID
     * @param propKey   The property key
     * @param propValue The property value
     */
    void saveRobotSettings(long botId, long userId, String propKey, String propValue);

    /**
     * @param botId  The bot ID
     * @param userId The user ID
     */
    void deleteRobotSettings(long botId, long userId);

    /**
     * @param botId   The bot ID
     * @param userId  The user ID
     * @param propKey The property key
     */
    void deleteRobotSettings(long botId, long userId, String propKey);

}
