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
package io.sgr.telegram.bot.ext.db;

import io.sgr.telegram.bot.api.models.User;

/**
 * @author SgrAlpha
 */
public interface UserRegistry {

    /**
     * Query user from users repository by user ID.
     *
     * @param userId The user ID
     * @return The bot user who match the given user ID.
     */
    User getUser(long userId);

    /**
     * Query user from users repository by username.
     *
     * @param username The username
     * @return The bot user who match the given user ID.
     */
    User getUser(String username);

    /**
     * Query active user from users repository by user ID.
     *
     * @param userId The user ID
     * @return The bot user who match the given user ID.
     */
    User getActiveUser(long userId);

    /**
     * Query active user from users repository by username.
     *
     * @param username The username
     * @return The bot user who match the given user ID.
     */
    User getActiveUser(String username);

    /**
     * Add one user into users repository, if the user already exist, will update user's data like first name, last name
     * and user name.
     *
     * @param user The telegram user
     * @return Whether or not the specified user has been added to the users repository.
     */
    boolean addUser(User user);

    /**
     * Mark one user as deactivated in users repository, will user's data will be kept.
     *
     * @param userId The user ID
     * @return Whether or not the specified user has been deactivated.
     */
    boolean deactivateUser(long userId);

    /**
     * Actually delete one user from the users repository.
     *
     * @param userId The user ID
     * @return Whether or not the specified user has been deleted.
     */
    boolean deleteUser(long userId);

}
