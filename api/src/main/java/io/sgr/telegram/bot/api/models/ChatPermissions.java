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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Describes actions that a non-administrator user is allowed to take in a chat.
 *
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatPermissions {

    private final Boolean canSendMessage;
    private final Boolean canSendMediaMessage;
    private final Boolean canSendPolls;
    private final Boolean canSendOtherMessage;
    private final Boolean canAddWebPagePreviews;
    private final Boolean canChangeInfo;
    private final Boolean canInviteUsers;
    private final Boolean canPinMessage;

    /**
     * @param canSendMessage Optional. Restricted only. True, if the user can send text messages, contacts, locations and venues.
     * @param canSendMediaMessage Optional. Restricted only. True, if the user can send audios, documents, photos, videos, video notes and voice notes, implies
     * can_send_messages.
     * @param canSendPolls Optional. Restricted only. True, if the user is allowed to send polls.
     * @param canSendOtherMessage Optional. Restricted only. True, if the user can send animations, games, stickers and use inline bots, implies
     * can_send_media_messages.
     * @param canAddWebPagePreviews Optional. Restricted only. True, if user may add web page previews to his messages,
     * @param canChangeInfo Optional. Administrators only. True, if the administrator can change the chat title, photo and other settings.
     * @param canInviteUsers Optional. Administrators only. True, if the administrator can invite new users to the chat.
     * @param canPinMessage Optional. Administrators only. True, if the administrator can pin messages, supergroups only.
     */
    @JsonCreator
    public ChatPermissions(
            @JsonProperty("can_send_messages") final Boolean canSendMessage,
            @JsonProperty("can_send_media_messages") final Boolean canSendMediaMessage,
            @JsonProperty("can_send_polls") final Boolean canSendPolls,
            @JsonProperty("can_send_other_messages") final Boolean canSendOtherMessage,
            @JsonProperty("can_add_web_page_previews") final Boolean canAddWebPagePreviews,
            @JsonProperty("can_change_info") final Boolean canChangeInfo,
            @JsonProperty("can_invite_users") final Boolean canInviteUsers,
            @JsonProperty("can_pin_messages") final Boolean canPinMessage
    ) {
        this.canSendMessage = canSendMessage;
        this.canSendMediaMessage = canSendMediaMessage;
        this.canSendPolls = canSendPolls;
        this.canSendOtherMessage = canSendOtherMessage;
        this.canAddWebPagePreviews = canAddWebPagePreviews;
        this.canChangeInfo = canChangeInfo;
        this.canInviteUsers = canInviteUsers;
        this.canPinMessage = canPinMessage;
    }

    @JsonProperty("can_send_messages")
    public Boolean canSendMessage() {
        return canSendMessage;
    }

    @JsonProperty("can_send_media_messages")
    public Boolean canSendMediaMessage() {
        return canSendMediaMessage;
    }

    @JsonProperty("can_send_pools")
    public Boolean canSendPolls() {
        return canSendPolls;
    }

    @JsonProperty("can_send_other_messages")
    public Boolean canSendOtherMessage() {
        return canSendOtherMessage;
    }

    @JsonProperty("can_add_web_page_previews")
    public Boolean canAddWebPagePreviews() {
        return canAddWebPagePreviews;
    }

    @JsonProperty("can_change_info")
    public Boolean canChangeInfo() {
        return canChangeInfo;
    }

    @JsonProperty("can_invite_users")
    public Boolean canInviteUsers() {
        return canInviteUsers;
    }

    @JsonProperty("can_pin_messages")
    public Boolean canPinMessage() {
        return canPinMessage;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    @Override
    public String toString() {
        return this.toJson();
    }

}
