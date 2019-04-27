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

package io.sgr.telegram.bot.examples.webhook.spring;

import io.sgr.telegram.bot.api.models.Update;
import io.sgr.telegram.bot.engine.BotUpdateProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateController implements BotUpdateProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateController.class);

    private final String botAndApiToken;

    public UpdateController(@Value("${bot.apiToken}") final String botAndApiToken) {
        this.botAndApiToken = botAndApiToken;
    }

    @PostMapping(path = "/update/{botAndApiToken}")
    @ResponseBody
    public ResponseEntity receiveUpdate(@PathVariable("botAndApiToken") final String botAndApiToken, @RequestBody final Update update) {
        if (!this.botAndApiToken.equalsIgnoreCase(botAndApiToken)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (handleUpdate(update)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @Override public boolean handleUpdate(final Update update) {
        LOGGER.info(update.toJSON());
        return true;
    }
}
