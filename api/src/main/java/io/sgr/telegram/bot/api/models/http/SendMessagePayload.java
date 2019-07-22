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

package io.sgr.telegram.bot.api.models.http;

import static io.sgr.telegram.bot.api.utils.Preconditions.notEmptyString;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.sgr.telegram.bot.api.models.ParseMode;
import io.sgr.telegram.bot.api.models.markups.ReplyMarkup;
import io.sgr.telegram.bot.api.utils.JsonUtil;

/**
 * @author SgrAlpha
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SendMessagePayload {

    private final String chatId;
    private final String text;
    private final ParseMode parseMode;
    private final Boolean disablePreview;
    private final Boolean disableNotification;
    private final Long replyTo;
    private final ReplyMarkup replyMarkup;

    /**
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     *               format @channelusername)
     * @param text   Text of the message to be sent.
     */
    public SendMessagePayload(long chatId, String text) {
        this(String.valueOf(chatId), text, null, null, null, null, null);
    }

    /**
     * @param chatId  Unique identifier for the target chat or username of the target channel (in the
     *                format @channelusername)
     * @param text    Text of the message to be sent.
     * @param replyTo ID of the original message.
     */
    public SendMessagePayload(long chatId, String text, long replyTo) {
        this(String.valueOf(chatId), text, null, null, null, replyTo, null);
    }

    /**
     * @param chatId              Unique identifier for the target chat or username of the target channel (in the
     *                            format @channelusername)
     * @param text                Text of the message to be sent.
     * @param parseMode           Optional. Send Markdown or HTML, if you want Telegram apps to show bold, italic,
     *                            fixed-width text or inline URLs in your bot's message.
     * @param disablePreview      Optional. Disables link previews for links in this message.
     * @param disableNotification Optional. Sends the message silently. iOS users will not receive a notification,
     *                            Android users will receive a notification with no sound.
     * @param replyTo             Optional. If the message is a reply, ID of the original message.
     * @param replyMarkup         Optional. Additional interface options. A JSON-serialized object for an inline
     *                            keyboard, custom reply keyboard, instructions to remove reply keyboard or to force a
     *                            reply from the user.
     */
    public SendMessagePayload(long chatId, String text, ParseMode parseMode,
                              Boolean disablePreview, Boolean disableNotification,
                              Long replyTo, ReplyMarkup replyMarkup) {
        this(String.valueOf(chatId), text, parseMode, disablePreview, disableNotification, replyTo, replyMarkup);
    }

    /**
     * @param chatId              Unique identifier for the target chat or username of the target channel (in the
     *                            format @channelusername)
     * @param text                Text of the message to be sent.
     * @param parseMode           Optional. Send Markdown or HTML, if you want Telegram apps to show bold, italic,
     *                            fixed-width text or inline URLs in your bot's message.
     * @param disablePreview      Optional. Disables link previews for links in this message.
     * @param disableNotification Optional. Sends the message silently. iOS users will not receive a notification,
     *                            Android users will receive a notification with no sound.
     * @param replyTo             Optional. If the message is a reply, ID of the original message.
     * @param replyMarkup         Additional interface options. A JSON-serialized object for an inline keyboard, custom
     *                            reply keyboard, instructions to remove reply keyboard or to force a reply from the
     *                            user.
     */
    public SendMessagePayload(String chatId, String text, ParseMode parseMode,
                              Boolean disablePreview, Boolean disableNotification,
                              Long replyTo, ReplyMarkup replyMarkup) {
        notEmptyString(chatId, "Chat ID should be provided");
        this.chatId = chatId;
        notEmptyString(text, "Content of the message should be provided");
        this.text = text;
        this.parseMode = parseMode;
        this.disablePreview = disablePreview;
        this.disableNotification = disableNotification;
        this.replyTo = replyTo;
        this.replyMarkup = replyMarkup;
    }

    /**
     * @return the chatId
     */
    @JsonProperty("chat_id")
    public String getChatId() {
        return this.chatId;
    }

    /**
     * @return the text
     */
    @JsonProperty("text")
    public String getText() {
        return this.text;
    }

    /**
     * @return the parseMode
     */
    @JsonProperty("parse_mode")
    public ParseMode getParseMode() {
        return this.parseMode;
    }

    /**
     * @return the disablePreview
     */
    @JsonProperty("disable_web_page_preview")
    public Boolean getDisablePreview() {
        return this.disablePreview;
    }

    /**
     * @return the disableNotification
     */
    @JsonProperty("disable_notification")
    public Boolean getDisableNotification() {
        return this.disableNotification;
    }

    /**
     * @return the replyTo
     */
    @JsonProperty("reply_to_message_id")
    public Long getReplyTo() {
        return this.replyTo;
    }

    @JsonProperty("reply_markup")
    public String getReplyMarkupJson() throws JsonProcessingException {
        if (this.replyMarkup == null) {
            return null;
        }
        return JsonUtil.getObjectMapper().writeValueAsString(this.replyMarkup);
    }

    /**
     * @return the replyMarkup
     */
    @JsonIgnore
    public ReplyMarkup getReplyMarkup() {
        return this.replyMarkup;
    }

}
