/*
 * Copyright 2017-2020 SgrAlpha
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package io.sgr.telegram.bot.api.models;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.isNullOrEmpty;

import io.sgr.telegram.bot.api.utils.JsonUtil;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    private final String language;

    /**
     * @param type Type of the entity. Can be “mention” (@username), “hashtag” (#hashtag), “cashtag” ($USD), “bot_command” (/start@jobs_bot), “url”
     * (https://telegram.org), “email” (do-not-reply@telegram.org), “phone_number” (+1-212-555-0123), “bold” (bold text), “italic” (italic text), “underline”
     * (underlined text), “strikethrough” (strikethrough text), “code” (monowidth string), “pre” (monowidth block), “text_link” (for clickable text URLs),
     * “text_mention” (for users without usernames).
     * @param offset Offset in UTF-16 code units to the start of the entity.
     * @param length Length of the entity in UTF-16 code units.
     * @param url Optional. For “text_link” only, url that will be opened after user taps on the text.
     * @param user Optional. For “text_mention” only, the mentioned user.
     * @param language Optional. For “pre” only, the programming language of the entity text.
     */
    @JsonCreator
    public MessageEntity(
            @JsonProperty("type") final String type,
            @JsonProperty("offset") final Integer offset,
            @JsonProperty("length") final Integer length,
            @JsonProperty("url") final String url,
            @JsonProperty("user") final User user,
            @JsonProperty("language") final String language) {
        checkArgument(!isNullOrEmpty(type), "Message entity type should be provided.");
        this.type = type;
        this.offset = checkNotNull(offset, "Message entity offset should be provided.");
        this.length = checkNotNull(length, "Message entity length should be provided.");
        this.url = url;
        this.user = user;
        this.language = language;
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

    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    @Override
    public String toString() {
        return this.toJson();
    }

}
