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

package io.sgr.telegram.bot.engine.processors;

import static io.sgr.telegram.bot.api.utils.Preconditions.notNull;

import io.sgr.telegram.bot.api.models.Update;
import io.sgr.telegram.bot.api.utils.JsonUtil;
import io.sgr.telegram.bot.api.utils.Preconditions;
import io.sgr.telegram.bot.engine.BotUpdateProcessor;
import org.slf4j.Logger;

/**
 * An update handler which only write update to log.
 *
 * @author SgrAlpha
 */
public class LogOnlyBotUpdateProcessor implements BotUpdateProcessor {

    private final Logger logger;

    public LogOnlyBotUpdateProcessor(final Logger logger) {
        notNull(logger, "Missing logger.");
        this.logger = logger;
    }

    @Override public boolean handleUpdate(final Update update) {
        logger.info(update == null ? "NULL" : JsonUtil.toJson(update));
        return true;
    }
}
