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

package io.sgr.telegram.bot.api.models;

import static io.sgr.telegram.bot.api.utils.Preconditions.notEmptyString;
import static io.sgr.telegram.bot.api.utils.Preconditions.notNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.sgr.telegram.bot.api.utils.JsonUtil;

/**
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageEntity {

    private final String type;
    private final Integer offset;
    private final Integer length;
    private final String url;
    private final User user;

    /**
     * @param type   Type of the entity. Can be mention (@username), hashtag, cashtag, bot_command, url, email,
     *               phone_number, bold (bold text), italic (italic text), code (monowidth string),
     *               pre (monowidth block), text_link (for clickable text URLs),
     *               text_mention (for users without usernames)
     * @param offset Offset in UTF-16 code units to the start of the entity.
     * @param length Length of the entity in UTF-16 code units.
     * @param url    Optional. For “text_link” only, url that will be opened after user taps on the text.
     * @param user   Optional. For “text_mention” only, the mentioned user.
     */
    @JsonCreator
    public MessageEntity(
            @JsonProperty("type") String type,
            @JsonProperty("offset") Integer offset,
            @JsonProperty("length") Integer length,
            @JsonProperty("url") String url,
            @JsonProperty("user") User user) {
        notEmptyString(type, "Message entity type should be provided.");
        this.type = type;
        notNull(offset, "Message entity offset should be provided.");
        this.offset = offset;
        notNull(length, "Message entity length should be provided.");
        this.length = length;
        this.url = url;
        this.user = user;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("offset")
    public Integer getOffset() {
        return offset;
    }

    @JsonProperty("length")
    public Integer getLength() {
        return length;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJson();
    }

}
