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
package io.sgr.telegram.bot.api.utils;

import static io.sgr.telegram.bot.api.utils.Preconditions.notNull;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

/**
 * @author SgrAlpha
 */
public class JsonUtil {

    private static final JsonFactory JSON_FACTORY = new JsonFactory();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper(JSON_FACTORY);
    static {
        OBJECT_MAPPER.registerModule(new Jdk8Module());
    }

    /**
     * Get the JsonFactory
     *
     * @return An instance of JsonFactory
     */
    public static JsonFactory getJsonFactory() {
        return JSON_FACTORY;
    }

    /**
     * Get the ObjectMapper
     *
     * @return An instance of ObjectMapper
     */
    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }

    /**
     * Convert a object to JSON string
     *
     * @param object The object to convert.
     * @param <T>    The type of object
     * @return The JSON string
     * @throws IllegalArgumentException if the object is null
     */
    public static <T> String toJson(T object) {
        notNull(object, "Cannot convert NULL object!");
        try {
            return getObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "{}";
    }

}
