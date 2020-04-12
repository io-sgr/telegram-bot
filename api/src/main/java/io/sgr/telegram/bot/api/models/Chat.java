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

import static com.google.common.base.Preconditions.checkNotNull;

import io.sgr.telegram.bot.api.utils.JsonUtil;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This object represents a chat.
 *
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Chat {

    private final long id;
    private final ChatType type;
    private final String title;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final ChatPhoto photo;
    private final String description;
    private final String inviteLink;
    private final Message pinnedMessage;
    private final ChatPermissions permissions;
    private final Integer slowModeDelay;
    private final String stickerSetName;
    private final Boolean canSetStickerSet;

    /**
     * @param id Unique identifier for this chat should not exceeding 1e13 by absolute value.
     * @param type Type of chat.
     * @param title Optional. Title, for channels and group chats.
     * @param username Optional. Username, for private chats and channels if available.
     * @param firstName Optional. First name of the other party in a private chat.
     * @param lastName Optional. Last name of the other party in a private chat.
     * @param photo Optional. Chat photo. Returned only in getChat.
     * @param description Optional. Description, for supergroups and channel chats. Returned only in getChat.
     * @param inviteLink Optional. Chat invite link, for supergroups and channel chats. Returned only in getChat.
     * @param pinnedMessage Optional. Pinned message, for supergroups. Returned only in getChat.
     * @param permissions Optional. Default chat member permissions, for groups and supergroups. Returned only in getChat.
     * @param slowModeDelay Optional. For supergroups, the minimum allowed delay between consecutive messages sent by each unpriviledged user. Returned only in
     * getChat.
     * @param stickerSetName Optional. For supergroups, name of group sticker set. Returned only in getChat.
     * @param canSetStickerSet Optional. True, if the bot can change the group sticker set. Returned only in
     */
    @JsonCreator
    public Chat(
            @JsonProperty("id") long id,
            @JsonProperty("type") ChatType type,
            @JsonProperty("title") String title,
            @JsonProperty("username") String username,
            @JsonProperty("first_name") String firstName,
            @JsonProperty("last_name") String lastName,
            @JsonProperty("photo") ChatPhoto photo,
            @JsonProperty("description") String description,
            @JsonProperty("invite_link") String inviteLink,
            @JsonProperty("pinned_message") Message pinnedMessage,
            @JsonProperty("permissions") ChatPermissions permissions,
            @JsonProperty("slow_mode_delay") Integer slowModeDelay,
            @JsonProperty("sticker_set_name") String stickerSetName,
            @JsonProperty("can_set_sticker_set") Boolean canSetStickerSet) {
        if (Math.abs(id) > 1e13) {
            throw new IllegalArgumentException(String.format("Unique identifier for this chat should not exceeding 1e13 by absolute value, but got %d", id));
        }
        this.id = id;
        this.type = checkNotNull(type, "ChatType should be provided.");
        this.title = title;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.photo = photo;
        this.description = description;
        this.inviteLink = inviteLink;
        this.pinnedMessage = pinnedMessage;
        this.permissions = permissions;
        this.slowModeDelay = slowModeDelay;
        this.stickerSetName = stickerSetName;
        this.canSetStickerSet = canSetStickerSet;
    }

    @JsonProperty("id")
    public long getId() {
        return this.id;
    }

    @JsonProperty("type")
    public ChatType getType() {
        return this.type;
    }

    @JsonProperty("title")
    public String getTitle() {
        return this.title;
    }

    @JsonProperty("username")
    public String getUsername() {
        return this.username;
    }

    @JsonProperty("first_name")
    public String getFirstName() {
        return this.firstName;
    }

    @JsonProperty("last_name")
    public String getLastName() {
        return this.lastName;
    }

    @JsonProperty("photo")
    public ChatPhoto getPhoto() {
        return this.photo;
    }

    @JsonProperty("description")
    public String getDescription() {
        return this.description;
    }

    @JsonProperty("invite_link")
    public String getInviteLink() {
        return this.inviteLink;
    }

    @JsonProperty("pinned_message")
    public Message getPinnedMessage() {
        return this.pinnedMessage;
    }

    @JsonProperty("permissions")
    public ChatPermissions getPermissions() {
        return permissions;
    }

    @JsonProperty("slow_mode_delay")
    public Integer getSlowModeDelay() {
        return slowModeDelay;
    }

    @JsonProperty("sticker_set_name")
    public String getStickerSetName() {
        return stickerSetName;
    }

    @JsonProperty("can_set_sticker_set")
    public Boolean canSetStickerSet() {
        return canSetStickerSet;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    @Override
    public String toString() {
        return this.toJson();
    }

}
