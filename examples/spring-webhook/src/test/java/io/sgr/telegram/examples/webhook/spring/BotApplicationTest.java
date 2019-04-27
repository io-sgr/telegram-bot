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

package io.sgr.telegram.examples.webhook.spring;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.sgr.telegram.bot.api.models.Chat;
import io.sgr.telegram.bot.api.models.ChatType;
import io.sgr.telegram.bot.api.models.Message;
import io.sgr.telegram.bot.api.models.Update;
import io.sgr.telegram.bot.api.utils.JsonUtil;
import io.sgr.telegram.bot.examples.webhook.spring.BotApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BotApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.yaml")
public class BotApplicationTest {

    @Value("${bot.apiToken}")
    private String botApiToken;

    @Autowired
    private MockMvc mvc;

    @Test
    public void testReceiveGoodUpdate() throws Exception {
        final Chat chat = new Chat(2L, ChatType.PRIVATE, null, null, null, null,
                null, null, null, null,
                null, null, null);
        final Message message = new Message(1L, null, System.currentTimeMillis(), chat,
                null, null, null, null,
                null, null, null, null, null,
                null, null, null, null,
                null, null, null, null,
                null, null, null, null,
                null, null, null, null,
                null, null, null, null, null,
                null, null, null, null,
                null, null, null, null);
        final Update update = new Update(0L, message, null, null, null, null, null, null, null);
        mvc.perform(post("/update/" + botApiToken)
                .content(JsonUtil.toJson(update))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(post("/update/something_else")  // Good update with unsupported bot
                .content(JsonUtil.toJson(update))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testReceiveInvalidUpdate() throws Exception {
        mvc.perform(post("/update/" + botApiToken)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
