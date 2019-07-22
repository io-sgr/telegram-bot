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

import static io.sgr.telegram.bot.api.utils.Preconditions.notNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.sgr.telegram.bot.api.models.game.Game;
import io.sgr.telegram.bot.api.models.sticker.Sticker;
import io.sgr.telegram.bot.api.utils.JsonUtil;

import java.util.Collections;
import java.util.List;

/**
 * This object represents a message.
 *
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message {

    private final long id;
    private final User from;
    private final long date;
    private final Chat chat;
    private final User forwardFrom;
    private final Chat forwardFromChat;
    private final Long forwardFromMessageId;
    private final String forwardSignature;
    private final String forwardSenderName;
    private final Long forwardDate;
    private final Message replyTo;
    private final Long editDate;
    private final String mediaGroupId;
    private final String authorSignature;
    private final String text;
    private final List<MessageEntity> entities;
    private final List<MessageEntity> captionEntities;
    private final Audio audio;
    private final Document document;
    private final Game game;
    private final List<PhotoSize> photo;
    private final Sticker sticker;
    private final Video video;
    private final Voice voice;
    private final VideoNote videoNote;
    private final String caption;
    private final Contact contact;
    private final Location location;
    private final Venue venue;
    private final Poll poll;
    private final List<User> newChatMembers;
    private final User leftChatMember;
    private final String newChatTitle;
    private final List<PhotoSize> newChatPhoto;
    private final Boolean deleteChatPhoto;
    private final Boolean groupChatCreated;
    private final Boolean superGroupChatCreated;
    private final Boolean channelChatCreated;
    private final Long migrateToChatId;
    private final Long migrateFromChatId;
    private final Message pinned;
    private final String connectedWebsite;

    /**
     * @param id                    Unique message identifier
     * @param from                  Optional. Sender, can be empty for messages sent to channels
     * @param date                  Date the message was sent in Unix time
     * @param chat                  Conversation the message belongs to
     * @param forwardFrom           Optional. For forwarded messages, sender of the original message
     * @param forwardFromChat       Optional. For messages forwarded from a channel, information about the original
     *                              channel.
     * @param forwardFromMessageId  Optional. For forwarded channel posts, identifier of the original message in the
     *                              channel.
     * @param forwardSignature      Optional. For messages forwarded from channels, signature of the post author if
     *                              present.
     * @param forwardSenderName     Optional. Sender's name for messages forwarded from users who disallow adding a link
     *                              to their account in forwarded messages.
     * @param forwardDate           Optional. For forwarded messages, date the original message was sent in Unix time
     * @param replyTo               Optional. For replies, the original message. Note that the Message object in this
     *                              field will not contain further reply_to_message fields even if it itself is a
     *                              reply.
     * @param editDate              Optional. Date the message was last edited in Unix time
     * @param mediaGroupId          Optional. The unique identifier of a media message group this message belongs to.
     * @param authorSignature       Optional. Signature of the post author for messages in channels
     * @param text                  Optional. For text messages, the actual UTF-8 text of the message
     * @param entities              Optional. Optional. For text messages, special entities like usernames, URLs, bot
     *                              commands, etc. that appear in the text
     * @param captionEntities       Optional. For messages with a caption, special entities like usernames, URLs, bot
     *                              commands, etc. that appear in the caption.
     * @param audio                 Optional. Message is an audio file, information about the file
     * @param document              Optional. Message is a general file, information about the file
     * @param game                  Optional. Message is a game, information about the game.
     * @param photo                 Optional. Message is a photo, available sizes of the photo.
     * @param sticker               Optional. Message is a sticker, information about the sticker.
     * @param video                 Optional. Message is a video, information about the video.
     * @param voice                 Optional. Message is a voice message, information about the file
     * @param videoNote             Optional. Message is a video note, information about the video message
     * @param caption               Optional. Caption for the document, photo or video, 0-200 characters
     * @param contact               Optional. Message is a shared contact, information about the contact
     * @param location              Optional. Message is a shared location, information about the location
     * @param venue                 Optional. Message is a venue, information about the venue.
     * @param poll                  Optional. Message is a native poll, information about the poll.
     * @param newChatMembers        Optional. New members that were added to the group or supergroup and information
     *                              about them (the bot itself may be one of these members)
     * @param leftChatMember        Optional. A member was removed from the group, information about them (this member
     *                              may be the bot itself)
     * @param newChatTitle          Optional. A chat title was changed to this value
     * @param newChatPhoto          Optional. A chat photo was change to this value
     * @param deleteChatPhoto       Optional. Optional. Service message: the chat photo was deleted
     * @param groupChatCreated      Optional. Optional. Service message: the group has been created
     * @param superGroupChatCreated Optional. Optional. Service message: the supergroup has been created. This field
     *                              can‘t be received in a message coming through updates, because bot can’t be a member
     *                              of a supergroup when it is created. It can only be found in reply_to_message if
     *                              someone replies to a very first message in a directly created supergroup.
     * @param channelChatCreated    Optional. Optional. Service message: the channel has been created. This field can‘t
     *                              be received in a message coming through updates, because bot can’t be a member of a
     *                              channel when it is created. It can only be found in reply_to_message if someone
     *                              replies to a very first message in a channel.
     * @param migrateToChatId       Optional. The group has been migrated to a supergroup with the specified identifier.
     *                              This number may be greater than 32 bits and some programming languages may have
     *                              difficulty/silent defects in interpreting it. But it smaller than 52 bits, so a
     *                              signed 64 bit integer or double-precision float type are safe for storing this
     *                              identifier.
     * @param migrateFromChatId     Optional. The supergroup has been migrated from a group with the specified
     *                              identifier. This number may be greater than 32 bits and some programming languages
     *                              may have difficulty/silent defects in interpreting it. But it smaller than 52 bits,
     *                              so a signed 64 bit integer or double-precision float type are safe for storing this
     *                              identifier.
     * @param pinned                Optional. Specified message was pinned. Note that the Message object in this field
     *                              will not contain further reply_to_message fields even if it is itself a reply.
     * @param connectedWebsite      Optional. The domain name of the website on which the user has logged in.
     */
    public Message(
            @JsonProperty("message_id") Long id,
            @JsonProperty("from") final User from,
            @JsonProperty("date") final Long date,
            @JsonProperty("chat") final Chat chat,
            @JsonProperty("forward_from") final User forwardFrom,
            @JsonProperty("forward_from_chat") final Chat forwardFromChat,
            @JsonProperty("forward_from_message_id") final Long forwardFromMessageId,
            @JsonProperty("forward_signature") final String forwardSignature,
            @JsonProperty("forward_sender_name") final String forwardSenderName,
            @JsonProperty("forward_date") final Long forwardDate,
            @JsonProperty("reply_to_message") final Message replyTo,
            @JsonProperty("edit_date") final Long editDate,
            @JsonProperty("media_group_id") final String mediaGroupId,
            @JsonProperty("author_signature") final String authorSignature,
            @JsonProperty("text") final String text,
            @JsonProperty("entities") final List<MessageEntity> entities,
            @JsonProperty("caption_entities") final List<MessageEntity> captionEntities,
            @JsonProperty("audio") final Audio audio,
            @JsonProperty("document") final Document document,
            @JsonProperty("game") final Game game,
            @JsonProperty("photo") final List<PhotoSize> photo,
            @JsonProperty("sticker") final Sticker sticker,
            @JsonProperty("video") final Video video,
            @JsonProperty("voice") final Voice voice,
            @JsonProperty("video_note") final VideoNote videoNote,
            @JsonProperty("caption") final String caption,
            @JsonProperty("contact") final Contact contact,
            @JsonProperty("location") final Location location,
            @JsonProperty("venue") final Venue venue,
            @JsonProperty("poll") final Poll poll,
            @JsonProperty("new_chat_members") final List<User> newChatMembers,
            @JsonProperty("left_chat_member") final User leftChatMember,
            @JsonProperty("new_chat_title") final String newChatTitle,
            @JsonProperty("new_chat_photo") final List<PhotoSize> newChatPhoto,
            @JsonProperty("delete_chat_photo") final Boolean deleteChatPhoto,
            @JsonProperty("group_chat_created") final Boolean groupChatCreated,
            @JsonProperty("supergroup_chat_created") final Boolean superGroupChatCreated,
            @JsonProperty("channel_chat_created") final Boolean channelChatCreated,
            @JsonProperty("migrate_to_chat_id") final Long migrateToChatId,
            @JsonProperty("migrate_from_chat_id") final Long migrateFromChatId,
            @JsonProperty("pinned_message") final Message pinned,
            @JsonProperty("connected_website") final String connectedWebsite) {
        notNull(id, "Message ID should be provided.");
        this.id = id;
        this.from = from;
        notNull(date, "Message sent date should be provided.");
        this.date = date;
        this.chat = chat;
        this.forwardFrom = forwardFrom;
        this.forwardFromChat = forwardFromChat;
        this.forwardFromMessageId = forwardFromMessageId;
        this.forwardSignature = forwardSignature;
        this.forwardSenderName = forwardSenderName;
        this.forwardDate = forwardDate;
        this.replyTo = replyTo;
        this.editDate = editDate;
        this.mediaGroupId = mediaGroupId;
        this.authorSignature = authorSignature;
        this.text = text;
        this.entities = entities;
        this.captionEntities = captionEntities;
        this.audio = audio;
        this.document = document;
        this.game = game;
        this.photo = photo;
        this.sticker = sticker;
        this.video = video;
        this.voice = voice;
        this.videoNote = videoNote;
        this.poll = poll;
        this.newChatMembers = newChatMembers;
        this.caption = caption == null ? null : caption.length() <= 200 ? caption : caption.substring(0, 200);
        this.contact = contact;
        this.location = location;
        this.venue = venue;
        this.leftChatMember = leftChatMember;
        this.newChatTitle = newChatTitle;
        this.newChatPhoto = newChatPhoto;
        this.deleteChatPhoto = deleteChatPhoto;
        this.groupChatCreated = groupChatCreated;
        this.superGroupChatCreated = superGroupChatCreated;
        this.channelChatCreated = channelChatCreated;
        this.migrateToChatId = migrateToChatId;
        this.migrateFromChatId = migrateFromChatId;
        this.pinned = pinned;
        this.connectedWebsite = connectedWebsite;
    }

    @JsonProperty("message_id")
    public long getId() {
        return id;
    }

    @JsonProperty("from")
    public User getFrom() {
        return from;
    }

    @JsonProperty("date")
    public long getDate() {
        return date;
    }

    @JsonProperty("chat")
    public Chat getChat() {
        return chat;
    }

    @JsonProperty("forward_from")
    public User getForwardFrom() {
        return forwardFrom;
    }

    @JsonProperty("forward_from_chat")
    public Chat getForwardFromChat() {
        return forwardFromChat;
    }

    @JsonProperty("forward_from_message_id")
    public Long getForwardFromMessageId() {
        return forwardFromMessageId;
    }

    @JsonProperty("forward_signature")
    public String getForwardSignature() {
        return forwardSignature;
    }

    @JsonProperty("forward_sender_name")
    public String getForwardSenderName() {
        return forwardSenderName;
    }

    @JsonProperty("forward_date")
    public Long getForwardDate() {
        return forwardDate;
    }

    @JsonProperty("reply_to_message")
    public Message getReplyTo() {
        return replyTo;
    }

    @JsonProperty("edit_date")
    public Long getEditDate() {
        return editDate;
    }

    @JsonProperty("media_group_id")
    public String getMediaGroupId() {
        return mediaGroupId;
    }

    @JsonProperty("author_signature")
    public String getAuthorSignature() {
        return authorSignature;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("entities")
    public List<MessageEntity> getEntities() {
        return entities == null ? null : Collections.unmodifiableList(entities);
    }

    @JsonProperty("caption_entities")
    public List<MessageEntity> getCaptionEntities() {
        return captionEntities == null ? null : Collections.unmodifiableList(captionEntities);
    }

    @JsonProperty("audio")
    public Audio getAudio() {
        return audio;
    }

    @JsonProperty("document")
    public Document getDocument() {
        return document;
    }

    @JsonProperty("game")
    public Game getGame() {
        return game;
    }

    @JsonProperty("photo")
    public List<PhotoSize> getPhoto() {
        return photo == null ? null : Collections.unmodifiableList(photo);
    }

    @JsonProperty("sticker")
    public Sticker getSticker() {
        return sticker;
    }

    @JsonProperty("video")
    public Video getVideo() {
        return video;
    }

    @JsonProperty("voice")
    public Voice getVoice() {
        return voice;
    }

    @JsonProperty("video_note")
    public VideoNote getVideoNote() {
        return videoNote;
    }

    @JsonProperty("caption")
    public String getCaption() {
        return caption;
    }

    @JsonProperty("contact")
    public Contact getContact() {
        return contact;
    }

    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

    @JsonProperty("venue")
    public Venue getVenue() {
        return venue;
    }

    @JsonProperty("poll")
    public Poll getPoll() {
        return poll;
    }

    @JsonProperty("new_chat_members")
    public List<User> getNewChatMembers() {
        return newChatMembers == null ? null : Collections.unmodifiableList(newChatMembers);
    }

    @JsonProperty("left_chat_member")
    public User getLeftChatMember() {
        return leftChatMember;
    }

    @JsonProperty("new_chat_title")
    public String getNewChatTitle() {
        return newChatTitle;
    }

    @JsonProperty("new_chat_photo")
    public List<PhotoSize> getNewChatPhoto() {
        return newChatPhoto == null ? null : Collections.unmodifiableList(newChatPhoto);
    }

    @JsonProperty("delete_chat_photo")
    public Boolean getDeleteChatPhoto() {
        return deleteChatPhoto;
    }

    @JsonProperty("group_chat_created")
    public Boolean getGroupChatCreated() {
        return groupChatCreated;
    }

    @JsonProperty("supergroup_chat_created")
    public Boolean getSuperGroupChatCreated() {
        return superGroupChatCreated;
    }

    @JsonProperty("channel_chat_created")
    public Boolean getChannelChatCreated() {
        return channelChatCreated;
    }

    @JsonProperty("migrate_to_chat_id")
    public Long getMigrateToChatId() {
        return migrateToChatId;
    }

    @JsonProperty("migrate_from_chat_id")
    public Long getMigrateFromChatId() {
        return migrateFromChatId;
    }

    @JsonProperty("pinned_message")
    public Message getPinned() {
        return pinned;
    }

    @JsonProperty("connected_website")
    public String getConnectedWebsite() {
        return connectedWebsite;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJson();
    }

}
