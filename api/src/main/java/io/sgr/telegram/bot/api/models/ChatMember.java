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

import static io.sgr.telegram.bot.api.utils.Preconditions.notNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.sgr.telegram.bot.api.utils.JsonUtil;

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
    private final Long untilDate;
    private final Boolean canBeEdited;
    private final Boolean canChangeInfo;
    private final Boolean canPostMessage;
    private final Boolean canEditMessage;
    private final Boolean canDeleteMessage;
    private final Boolean canInviteUsers;
    private final Boolean canRestrictMembers;
    private final Boolean canPinMessage;
    private final Boolean canPromoteMembers;
    private final Boolean canSendMessage;
    private final Boolean canSendMediaMessage;
    private final Boolean canSendOtherMessage;
    private final Boolean canAddWebPagePreviews;

    /**
     * @param user                  Information about the user
     * @param status                The member's status in the chat. Can be 'creator', 'administrator', 'member', 'left'
     *                              or 'kicked'
     * @param untilDate             Optional. Restricted and kicked only. Date when restrictions will be lifted for this
     *                              user, unix time.
     * @param canBeEdited           Optional. Administrators only. True, if the bot is allowed to edit administrator
     *                              privileges of that user.
     * @param canChangeInfo         Optional. Administrators only. True, if the administrator can change the chat title,
     *                              photo and other settings.
     * @param canPostMessage        Optional. Administrators only. True, if the administrator can post in the channel,
     *                              channels only.
     * @param canEditMessage        Optional. Administrators only. True, if the administrator can edit messages of other
     *                              users, channels only.
     * @param canDeleteMessage      Optional. Administrators only. True, if the administrator can delete messages of
     *                              other users.
     * @param canInviteUsers        Optional. Administrators only. True, if the administrator can invite new users to
     *                              the chat.
     * @param canRestrictMembers    Optional. Administrators only. True, if the administrator can restrict, ban or unban
     *                              chat members.
     * @param canPinMessage         Optional. Administrators only. True, if the administrator can pin messages,
     *                              supergroups only.
     * @param canPromoteMembers     Optional. Administrators only. True, if the administrator can add new administrators
     *                              with a subset of his own privileges or demote administrators that he has promoted,
     *                              directly or indirectly (promoted by administrators that were appointed by the user)
     * @param canSendMessage        Optional. Restricted only. True, if the user can send text messages, contacts,
     *                              locations and venues.
     * @param canSendMediaMessage   Optional. Restricted only. True, if the user can send audios, documents, photos,
     *                              videos, video notes and voice notes, implies can_send_messages.
     * @param canSendOtherMessage   Optional. Restricted only. True, if the user can send animations, games, stickers
     *                              and use inline bots, implies can_send_media_messages.
     * @param canAddWebPagePreviews Optional. Restricted only. True, if user may add web page previews to his messages,
     *                              implies can_send_media_messages.
     */
    @JsonCreator
    public ChatMember(
            @JsonProperty("user") User user,
            @JsonProperty("status") ChatMemberStatus status,
            @JsonProperty("until_date") Long untilDate,
            @JsonProperty("can_be_edited") Boolean canBeEdited,
            @JsonProperty("can_change_info") Boolean canChangeInfo,
            @JsonProperty("can_post_messages") Boolean canPostMessage,
            @JsonProperty("can_edit_messages") Boolean canEditMessage,
            @JsonProperty("can_delete_messages") Boolean canDeleteMessage,
            @JsonProperty("can_invite_users") Boolean canInviteUsers,
            @JsonProperty("can_restrict_members") Boolean canRestrictMembers,
            @JsonProperty("can_pin_messages") Boolean canPinMessage,
            @JsonProperty("can_promote_members") Boolean canPromoteMembers,
            @JsonProperty("can_send_messages") Boolean canSendMessage,
            @JsonProperty("can_send_media_messages") Boolean canSendMediaMessage,
            @JsonProperty("can_send_other_messages") Boolean canSendOtherMessage,
            @JsonProperty("can_add_web_page_previews") Boolean canAddWebPagePreviews) {
        notNull(user, "User should be provided.");
        this.user = user;
        notNull(status, "Chat member status should be provided.");
        this.status = status;
        this.untilDate = untilDate;
        this.canBeEdited = canBeEdited;
        this.canChangeInfo = canChangeInfo;
        this.canPostMessage = canPostMessage;
        this.canEditMessage = canEditMessage;
        this.canDeleteMessage = canDeleteMessage;
        this.canInviteUsers = canInviteUsers;
        this.canRestrictMembers = canRestrictMembers;
        this.canPinMessage = canPinMessage;
        this.canPromoteMembers = canPromoteMembers;
        this.canSendMessage = canSendMessage;
        this.canSendMediaMessage = canSendMediaMessage;
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

    @JsonProperty("can_be_edited")
    public Boolean canBeEdited() {
        return canBeEdited;
    }

    @JsonProperty("can_change_info")
    public Boolean canChangeInfo() {
        return canChangeInfo;
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

    @JsonProperty("can_invite_users")
    public Boolean canInviteUsers() {
        return canInviteUsers;
    }

    @JsonProperty("can_restrict_members")
    public Boolean canRestrictMembers() {
        return canRestrictMembers;
    }

    @JsonProperty("can_pin_messages")
    public Boolean canPinMessage() {
        return canPinMessage;
    }

    @JsonProperty("can_promote_members")
    public Boolean canPromoteMembers() {
        return canPromoteMembers;
    }

    @JsonProperty("can_send_messages")
    public Boolean canSendMessage() {
        return canSendMessage;
    }

    @JsonProperty("can_send_media_messages")
    public Boolean canSendMediaMessage() {
        return canSendMediaMessage;
    }

    @JsonProperty("can_send_other_messages")
    public Boolean canSendOtherMessage() {
        return canSendOtherMessage;
    }

    @JsonProperty("can_add_web_page_previews")
    public Boolean canAddWebPagePreviews() {
        return canAddWebPagePreviews;
    }

    public String toJSON() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJSON();
    }

}
