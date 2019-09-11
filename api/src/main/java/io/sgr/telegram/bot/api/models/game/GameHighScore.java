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

package io.sgr.telegram.bot.api.models.game;

import static io.sgr.telegram.bot.api.utils.Preconditions.notNull;

import io.sgr.telegram.bot.api.models.User;
import io.sgr.telegram.bot.api.utils.JsonUtil;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This object represents one row of the high scores table for a game.
 *
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GameHighScore {

    private final int position;
    private final User user;
    private final long score;

    /**
     * @param position Position in high score table for the game.
     * @param user     User.
     * @param score    Score.
     */
    @JsonCreator
    public GameHighScore(
            @JsonProperty("position") int position,
            @JsonProperty("user") User user,
            @JsonProperty("score") long score) {
        if (position <= 0) {
            throw new IllegalArgumentException(String.format("Position should be greater than zero, but got %d", position));
        }
        this.position = position;
        notNull(user, "User should be provided");
        this.user = user;
        if (score < 0) {
            throw new IllegalArgumentException(String.format("Score should be greater than or equal to zero, but got %d", score));
        }
        this.score = score;
    }

    @JsonProperty("position")
    public int getPosition() {
        return position;
    }

    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    @JsonProperty("score")
    public long getScore() {
        return score;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJson();
    }

}
