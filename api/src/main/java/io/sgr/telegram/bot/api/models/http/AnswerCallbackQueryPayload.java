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
package io.sgr.telegram.bot.api.models.http;

import static io.sgr.telegram.bot.api.utils.Preconditions.notEmptyString;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.sgr.telegram.bot.api.utils.Preconditions;

/**
 * @author SgrAlpha
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnswerCallbackQueryPayload {

    private final String callbackQueryId;
    private final String text;
    private final Boolean showAlert;
    private final String url;
    private final Integer cacheTime;

    /**
     * @param callbackQueryId Unique identifier for the query to be answered.
     */
    public AnswerCallbackQueryPayload(String callbackQueryId) {
        this(callbackQueryId, null, null, null, null);
    }

    /**
     * @param callbackQueryId Unique identifier for the query to be answered.
     * @param text            Optional. Text of the notification. If not specified, nothing will be shown to the user,
     *                        0-200 characters.
     * @param showAlert       Optional. If true, an alert will be shown by the client instead of a notification at the
     *                        top of the chat screen. Defaults to false.
     * @param url             Optional. URL that will be opened by the user's client. If you have created a Game and
     *                        accepted the conditions via @Botfather, specify the URL that opens your game – note that
     *                        this will only work if the query comes from a callback_game button. Otherwise, you may use
     *                        links like t.me/your_bot?start=XXXX that open your bot with a parameter.
     * @param cacheTime       Optional. The maximum amount of time in seconds that the result of the callback query may
     *                        be cached client-side. Telegram apps will support caching starting in version 3.14.
     *                        Defaults to 0.
     */
    public AnswerCallbackQueryPayload(
            String callbackQueryId,
            String text,
            Boolean showAlert,
            String url,
            Integer cacheTime) {
        notEmptyString(callbackQueryId, "Callback query ID should be provided");
        this.callbackQueryId = callbackQueryId;
        if (!Preconditions.isEmptyString(text) && (text.trim().length() > 200)) {
            throw new IllegalArgumentException("The length of text should be between 1-200");
        }
        this.text = text;
        this.showAlert = showAlert;
        this.url = url;
        this.cacheTime = cacheTime;
    }

    @JsonProperty("callback_query_id")
    public String getCallbackQueryId() {
        return this.callbackQueryId;
    }

    @JsonProperty("text")
    public String getText() {
        return this.text;
    }

    @JsonProperty("show_alert")
    public Boolean getShowAlert() {
        return this.showAlert;
    }

    @JsonProperty("url")
    public String getUrl() {
        return this.url;
    }

    @JsonProperty("cache_time")
    public Integer getCacheTime() {
        return this.cacheTime;
    }

}
