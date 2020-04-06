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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This object represents a parameter of the inline keyboard button used to automatically authorize a user. Serves as a great replacement for the Telegram Login
 * Widget when the user is coming from Telegram.
 *
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginUrl {

    private final String url;
    private final String forwardText;
    private final String botUsername;
    private final Boolean requestWriteAccess;

    /**
     * @param url An HTTP URL to be opened with user authorization data added to the query string when the button is pressed. If the user refuses to provide
     * authorization data, the original URL without information about the user will be opened. The data added is the same as described in Receiving
     * authorization data.
     * @param forwardText Optional. New text of the button in forwarded messages.
     * @param botUsername Optional. Username of a bot, which will be used for user authorization. See Setting up a bot for more details. If not specified, the
     * current bot's username will be assumed. The url's domain must be the same as the domain linked with the bot. See Linking your domain to the bot for more
     * details.
     * @param requestWriteAccess Optional. Pass True to request the permission for your bot to send messages to the user.
     */
    @JsonCreator
    public LoginUrl(
            @JsonProperty("url") final String url,
            @JsonProperty("forward_text") final String forwardText,
            @JsonProperty("bot_username") final String botUsername,
            @JsonProperty("request_write_access") final Boolean requestWriteAccess
    ) {
        this.url = url;
        this.forwardText = forwardText;
        this.botUsername = botUsername;
        this.requestWriteAccess = requestWriteAccess;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("forward_text")
    public String getForwardText() {
        return forwardText;
    }

    @JsonProperty("bot_username")
    public String getBotUsername() {
        return botUsername;
    }

    @JsonProperty("request_write_access")
    public Boolean getRequestWriteAccess() {
        return requestWriteAccess;
    }
}
