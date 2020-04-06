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

import static io.sgr.telegram.bot.api.utils.Preconditions.notNull;

import io.sgr.telegram.bot.api.utils.JsonUtil;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This object contains information about one member of the chat.
 *
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatMember {

    private final User user;
    private final ChatMemberStatus status;
    private final String customTitle;
    private final Long untilDate;
    private final Boolean canBeEdited;
    private final Boolean canPostMessage;
    private final Boolean canEditMessage;
    private final Boolean canDeleteMessage;
    private final Boolean canRestrictMembers;
    private final Boolean canPromoteMembers;
    private final Boolean canChangeInfo;
    private final Boolean canInviteUsers;
    private final Boolean canPinMessage;
    private final Boolean isMember;
    private final Boolean canSendMessage;
    private final Boolean canSendMediaMessage;
    private final Boolean canSendPolls;
    private final Boolean canSendOtherMessage;
    private final Boolean canAddWebPagePreviews;

    /**
     * @param user Information about the user
     * @param status The isMember's status in the chat. Can be 'creator', 'administrator', 'isMember', 'left' or 'kicked'
     * @param customTitle Optional. Owner and administrators only. Custom title for this user.
     * @param untilDate Optional. Restricted and kicked only. Date when restrictions will be lifted for this user, unix time.
     * @param canBeEdited Optional. Administrators only. True, if the bot is allowed to edit administrator privileges of that user.
     * @param canPostMessage Optional. Administrators only. True, if the administrator can post in the channel, channels only.
     * @param canEditMessage Optional. Administrators only. True, if the administrator can edit messages of other users, channels only.
     * @param canDeleteMessage Optional. Administrators only. True, if the administrator can delete messages of other users.
     * @param canRestrictMembers Optional. Administrators only. True, if the administrator can restrict, ban or unban chat members.
     * @param canPromoteMembers Optional. Administrators only. True, if the administrator can add new administrators with a subset of his own privileges or
     * demote administrators that he has promoted, directly or indirectly (promoted by administrators that were appointed by the user)
     * @param canChangeInfo Optional. Administrators only. True, if the administrator can change the chat title, photo and other settings.
     * @param canInviteUsers Optional. Administrators only. True, if the administrator can invite new users to the chat.
     * @param canPinMessage Optional. Administrators only. True, if the administrator can pin messages, supergroups only.
     * @param isMember Optional. Restricted only. True, if the user is a isMember of the chat at the moment of the request.
     * @param canSendMessage Optional. Restricted only. True, if the user can send text messages, contacts, locations and venues.
     * @param canSendMediaMessage Optional. Restricted only. True, if the user can send audios, documents, photos, videos, video notes and voice notes, implies
     * can_send_messages.
     * @param canSendPolls Optional. Restricted only. True, if the user is allowed to send polls.
     * @param canSendOtherMessage Optional. Restricted only. True, if the user can send animations, games, stickers and use inline bots, implies
     * can_send_media_messages.
     * @param canAddWebPagePreviews Optional. Restricted only. True, if user may add web page previews to his messages,
     */
    @JsonCreator
    public ChatMember(
            @JsonProperty("user") final User user,
            @JsonProperty("status") final ChatMemberStatus status,
            @JsonProperty("custom_title") final String customTitle,
            @JsonProperty("until_date") final Long untilDate,
            @JsonProperty("can_be_edited") final Boolean canBeEdited,
            @JsonProperty("can_post_messages") final Boolean canPostMessage,
            @JsonProperty("can_edit_messages") final Boolean canEditMessage,
            @JsonProperty("can_delete_messages") final Boolean canDeleteMessage,
            @JsonProperty("can_restrict_members") final Boolean canRestrictMembers,
            @JsonProperty("can_promote_members") final Boolean canPromoteMembers,
            @JsonProperty("can_change_info") final Boolean canChangeInfo,
            @JsonProperty("can_invite_users") final Boolean canInviteUsers,
            @JsonProperty("can_pin_messages") final Boolean canPinMessage,
            @JsonProperty("is_member") final Boolean isMember,
            @JsonProperty("can_send_messages") final Boolean canSendMessage,
            @JsonProperty("can_send_media_messages") final Boolean canSendMediaMessage,
            @JsonProperty("can_send_polls") final Boolean canSendPolls,
            @JsonProperty("can_send_other_messages") final Boolean canSendOtherMessage,
            @JsonProperty("can_add_web_page_previews") final Boolean canAddWebPagePreviews) {
        notNull(user, "User should be provided.");
        this.user = user;
        notNull(status, "Chat isMember status should be provided.");
        this.status = status;
        this.customTitle = customTitle;
        this.untilDate = untilDate;
        this.canBeEdited = canBeEdited;
        this.canPostMessage = canPostMessage;
        this.canEditMessage = canEditMessage;
        this.canDeleteMessage = canDeleteMessage;
        this.canRestrictMembers = canRestrictMembers;
        this.canPromoteMembers = canPromoteMembers;
        this.canChangeInfo = canChangeInfo;
        this.canInviteUsers = canInviteUsers;
        this.canPinMessage = canPinMessage;
        this.isMember = isMember;
        this.canSendMessage = canSendMessage;
        this.canSendMediaMessage = canSendMediaMessage;
        this.canSendPolls = canSendPolls;
        this.canSendOtherMessage = canSendOtherMessage;
        this.canAddWebPagePreviews = canAddWebPagePreviews;
    }

    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    @JsonProperty("status")
    public ChatMemberStatus getStatus() {
        return status;
    }

    @JsonProperty("until_date")
    public Long getUntilDate() {
        return untilDate;
    }

    @JsonProperty("custom_title")
    public String getCustomTitle() {
        return customTitle;
    }

    @JsonProperty("can_be_edited")
    public Boolean canBeEdited() {
        return canBeEdited;
    }

    @JsonProperty("can_post_messages")
    public Boolean canPostMessage() {
        return canPostMessage;
    }

    @JsonProperty("can_edit_messages")
    public Boolean canEditMessage() {
        return canEditMessage;
    }

    @JsonProperty("can_delete_messages")
    public Boolean canDeleteMessage() {
        return canDeleteMessage;
    }

    @JsonProperty("can_restrict_members")
    public Boolean canRestrictMembers() {
        return canRestrictMembers;
    }

    @JsonProperty("can_promote_members")
    public Boolean canPromoteMembers() {
        return canPromoteMembers;
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

    @JsonProperty("is_members")
    public Boolean getMember() {
        return isMember;
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

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    @Override
    public String toString() {
        return this.toJson();
    }

}
