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
package io.sgr.telegram.bot.api.models;

import io.sgr.telegram.bot.api.utils.JsonUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

/**
 * @author SgrAlpha
 */
public class UpdateTest {

    private static final ObjectMapper MAPPER = JsonUtil.getObjectMapper();

    @Test
    public void testParse() {
        try {
            Update update = MAPPER.readValue("{\"update_id\": 40737835,"
                    + " \"inline_query\": {"
                    + "\"id\": \"480167263633693677\","
                    + " \"from\": {\"id\": 111797653, \"first_name\": \"Super\", \"last_name\": \"@AS16-NOV-08\", \"username\": \"StarVoyager\"},"
                    + " \"location\": {\"latitude\": 39.94991,\"longitude\": 116.307506},"
                    + " \"query\": \"\","
                    + " \"offset\": \"\"}}", Update.class);
            System.out.println(update);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
