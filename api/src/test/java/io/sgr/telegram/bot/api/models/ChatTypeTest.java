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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SgrAlpha
 */
public class ChatTypeTest {

    private static final ObjectMapper MAPPER = JsonUtil.getObjectMapper();

    @Test
    public void testParse() {
        try {
            List<ChatType> types = MAPPER.readValue("[ \"private\", \"group\", \"supergroup\", \"channel\" ]", new TypeReference<ArrayList<ChatType>>() {
            });
            Assert.assertEquals(4, types.size());
            Assert.assertEquals(ChatType.PRIVATE, types.get(0));
            Assert.assertEquals(ChatType.GROUP, types.get(1));
            Assert.assertEquals(ChatType.SUPERGROUP, types.get(2));
            Assert.assertEquals(ChatType.CHANNEL, types.get(3));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            MAPPER.readValue("null", ChatType.class);
        } catch (IOException e) {
            Assert.assertTrue(e instanceof JsonMappingException);
        }

        String badString = "asdf";
        try {
            MAPPER.readValue(String.format("\"%s\"", badString), ChatType.class);
        } catch (IOException e) {
            Assert.assertTrue(e instanceof JsonMappingException);
        }

        try {
            MAPPER.readValue("\"\"", ChatType.class);
        } catch (IOException e) {
            Assert.assertTrue(e instanceof JsonMappingException);
        }

    }

}
