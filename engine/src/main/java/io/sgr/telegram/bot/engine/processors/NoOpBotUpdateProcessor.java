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

package io.sgr.telegram.bot.engine.processors;

import io.sgr.telegram.bot.api.models.Update;
import io.sgr.telegram.bot.engine.BotUpdateProcessor;

import javax.annotation.Nonnull;

/**
 * An update handler which exactly does nothing.
 *
 * @author SgrAlpha
 */
public class NoOpBotUpdateProcessor implements BotUpdateProcessor {

    private static final NoOpBotUpdateProcessor INST = new NoOpBotUpdateProcessor();

    public static NoOpBotUpdateProcessor getDefault() {
        return INST;
    }

    @Override public boolean handleUpdate(@Nonnull final Update update) {
        return true;
    }

}
