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

package io.sgr.telegram.bot.api.models.http;

import io.sgr.telegram.bot.api.models.PollOption;
import io.sgr.telegram.bot.api.models.markups.ReplyMarkup;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Optional;

/**
 * @author SgrAlpha
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SendPollPayload {

    private final String id;
    private final String question;
    private final List<PollOption> options;
    private final boolean anonymous;
    private final String type;
    private final boolean allowsMultipleAnswers;
    private final Integer correctOptionId;
    private final Boolean closed;
    private final Boolean disableNotification;
    private final Long replyToMessageId;
    private final ReplyMarkup replyMarkup;

    /**
     * @param id Unique identifier for the target chat or username of the target channel (in the format @channelusername).
     * @param question Poll question, 1-255 characters.
     * @param options A JSON-serialized list of answer options, 2-10 strings 1-100 characters each.
     */
    public SendPollPayload(final String id, final String question, final List<PollOption> options) {
        this(id, question, options, null, null, null, null, null, null, null, null);
    }

    /**
     * @param id Unique identifier for the target chat or username of the target channel (in the format @channelusername).
     * @param question Poll question, 1-255 characters.
     * @param options A JSON-serialized list of answer options, 2-10 strings 1-100 characters each.
     * @param anonymous Optional. True, if the poll needs to be anonymous, defaults to True.
     * @param type Optional. Poll type, “quiz” or “regular”, defaults to “regular”.
     * @param allowsMultipleAnswers Optional. True, if the poll allows multiple answers, ignored for polls in quiz mode, defaults to False.
     * @param correctOptionId Optional. 0-based identifier of the correct answer option, required for polls in quiz mode.
     * @param closed Optional. Pass True, if the poll needs to be immediately closed. This can be useful for poll preview.
     * @param disableNotification Optional. Sends the message silently. Users will receive a notification with no sound.
     * @param replyToMessageId If the message is a reply, ID of the original message.
     * @param replyMarkup Additional interface options. A JSON-serialized object for an inline keyboard, custom reply keyboard, instructions to remove reply
     * keyboard or to force a reply from the user.
     */
    public SendPollPayload(
            final String id, final String question, final List<PollOption> options,
            final Boolean anonymous, final String type, final Boolean allowsMultipleAnswers,
            final Integer correctOptionId, final Boolean closed, final Boolean disableNotification, final Long replyToMessageId,
            final ReplyMarkup replyMarkup) {
        this.id = id;
        this.question = question;
        this.options = options;
        this.anonymous = Optional.ofNullable(anonymous).orElse(true);
        this.type = type;
        this.allowsMultipleAnswers = Optional.ofNullable(allowsMultipleAnswers).orElse(false);
        this.correctOptionId = correctOptionId;
        this.closed = closed;
        this.disableNotification = disableNotification;
        this.replyToMessageId = replyToMessageId;
        this.replyMarkup = replyMarkup;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("question")
    public String getQuestion() {
        return question;
    }

    @JsonProperty("options")
    public List<PollOption> getOptions() {
        return options;
    }

    @JsonProperty("is_anonymous")
    public boolean isAnonymous() {
        return anonymous;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("allows_multiple_answers")
    public boolean allowsMultipleAnswers() {
        return allowsMultipleAnswers;
    }

    @JsonProperty("correct_option_id")
    public Integer getCorrectOptionId() {
        return correctOptionId;
    }

    @JsonProperty("is_closed")
    public Boolean isClosed() {
        return closed;
    }

    @JsonProperty("disable_notification")
    public Boolean disableNotification() {
        return disableNotification;
    }

    @JsonProperty("reply_to_message_id")
    public Long getReplyToMessageId() {
        return replyToMessageId;
    }

    @JsonProperty("reply_markup")
    public ReplyMarkup getReplyMarkup() {
        return replyMarkup;
    }
}
