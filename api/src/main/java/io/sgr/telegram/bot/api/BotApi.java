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

package io.sgr.telegram.bot.api;

import static io.sgr.telegram.bot.api.utils.TelegramUtils.verifyToken;

import io.sgr.telegram.bot.api.http.DefaultCallAdapterFactory;
import io.sgr.telegram.bot.api.models.Chat;
import io.sgr.telegram.bot.api.models.ChatMember;
import io.sgr.telegram.bot.api.models.ChatPermissions;
import io.sgr.telegram.bot.api.models.Message;
import io.sgr.telegram.bot.api.models.Poll;
import io.sgr.telegram.bot.api.models.Update;
import io.sgr.telegram.bot.api.models.User;
import io.sgr.telegram.bot.api.models.WebhookInfo;
import io.sgr.telegram.bot.api.models.game.GameHighScore;
import io.sgr.telegram.bot.api.models.game.http.GetGameScorePayload;
import io.sgr.telegram.bot.api.models.game.http.SendGamePayload;
import io.sgr.telegram.bot.api.models.game.http.SetGameScorePayload;
import io.sgr.telegram.bot.api.models.http.AnswerCallbackQueryPayload;
import io.sgr.telegram.bot.api.models.http.AnswerInlineQueryPayload;
import io.sgr.telegram.bot.api.models.http.DeleteMessagePayload;
import io.sgr.telegram.bot.api.models.http.EditMessageCaptionPayload;
import io.sgr.telegram.bot.api.models.http.EditMessageReplyMarkupPayload;
import io.sgr.telegram.bot.api.models.http.EditMessageTextPayload;
import io.sgr.telegram.bot.api.models.http.ForwardMessagePayload;
import io.sgr.telegram.bot.api.models.http.GetUpdatesPayload;
import io.sgr.telegram.bot.api.models.http.SendMessagePayload;
import io.sgr.telegram.bot.api.models.http.SendPollPayload;
import io.sgr.telegram.bot.api.models.http.StopPollPayload;
import io.sgr.telegram.bot.api.utils.JsonUtil;

import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author SgrAlpha
 */
public interface BotApi {

    /**
     * A simple method for testing your bot's auth token. Requires no parameters.
     *
     * @return basic information about the bot in form of a User object.
     */
    @POST("getMe")
    CompletableFuture<User> getMe();

    @POST("getWebhookInfo")
    CompletableFuture<WebhookInfo> getWebhookInfo();

    @POST("getUpdates")
    CompletableFuture<List<Update>> getUpdates(@Body GetUpdatesPayload payload);

    /**
     * Use this method to send text messages.
     *
     * @param payload The request payload.
     * @return On success, the sent Message is returned.
     */
    @POST("sendMessage")
    CompletableFuture<Message> sendMessage(@Body SendMessagePayload payload);

    /**
     * Use this method to forward messages of any kind.
     *
     * @param payload The request payload.
     * @return On success, the sent Message is returned.
     */
    @POST("forwardMessage")
    CompletableFuture<Message> forwardMessage(@Body ForwardMessagePayload payload);

    /**
     * Use this method to edit text and game messages.
     *
     * @param payload The request payload.
     * @return On success, if edited message is sent by the bot, the edited Message is returned, otherwise True is returned.
     */
    @POST("editMessageText")
    CompletableFuture<Message> editMessageText(@Body EditMessageTextPayload payload);

    /**
     * Use this method to edit captions of messages.
     *
     * @param payload The request payload.
     * @return On success, if edited message is sent by the bot, the edited Message is returned, otherwise True is returned.
     */
    @POST("editMessageCaption")
    CompletableFuture<Message> editMessageCaption(@Body EditMessageCaptionPayload payload);

    /**
     * Use this method to edit only the reply markup of messages.
     *
     * @param payload The request payload.
     * @return On success, if edited message is sent by the bot, the edited Message is returned, otherwise True is returned.
     */
    @POST("editMessageReplyMarkup")
    CompletableFuture<Message> editMessageReplyMarkup(@Body EditMessageReplyMarkupPayload payload);

    /**
     * Use this method to delete a message, including service messages, with the following limitations:
     * <p>
     * - A message can only be deleted if it was sent less than 48 hours ago.
     * <p>
     * - A dice message in a private chat can only be deleted if it was sent more than 24 hours ago.
     * <p>
     * - Bots can delete outgoing messages in private chats, groups, and supergroups.
     * <p>
     * - Bots can delete incoming messages in private chats.
     * <p>
     * - Bots granted can_post_messages permissions can delete outgoing messages in channels.
     * <p>
     * - If the bot is an administrator of a group, it can delete any message there.
     * <p>
     * - If the bot has can_delete_messages permission in a supergroup or a channel, it can delete any message there.
     *
     * @param payload The request payload.
     * @return True on success.
     */
    @POST("deleteMessage")
    CompletableFuture<Boolean> deleteMessage(@Body DeleteMessagePayload payload);

    /**
     * Use this method to send answers to an inline query. No more than 50 results per query are allowed.
     *
     * @param payload The request payload.
     * @return On success, True is returned.
     */
    @POST("answerInlineQuery")
    CompletableFuture<Boolean> answerInlineQuery(@Body AnswerInlineQueryPayload payload);

    /**
     * Use this method to send a native poll.
     *
     * @param payload The request payload.
     * @return On success, the sent Message is returned.
     */
    @POST("sendPoll")
    CompletableFuture<Message> sendPoll(@Body SendPollPayload payload);

    /**
     * Use this method to stop a poll which was sent by the bot.
     *
     * @param payload The request payload.
     * @return On success, the stopped Poll with the final results is returned.
     */
    @POST("stopPoll")
    CompletableFuture<Poll> stopPoll(@Body StopPollPayload payload);

    /**
     * Use this method to kick a user from a group, a supergroup or a channel. In the case of supergroups and channels, the user will not be able to return to
     * the group on their own using invite links, etc., unless unbanned first. The bot must be an administrator in the chat for this to work and must have the
     * appropriate admin rights.
     *
     * @param chatId Unique identifier for the target group or username of the target supergroup or channel (in the format @channelusername).
     * @param userId Unique identifier of the target user.
     * @param untilDate Optional. Date when the user will be unbanned, unix time. If user is banned for more than 366 days or less than 30 seconds from the
     * current time they are considered to be banned forever.
     * @return True on success.
     */
    @FormUrlEncoded
    @POST("kickChatMember")
    CompletableFuture<Boolean> kickChatMember(@Field("chat_id") String chatId, @Field("user_id") long userId, @Field("until_date") Long untilDate);

    /**
     * Use this method to kick a user from a group, a supergroup or a channel. In the case of supergroups and channels, the user will not be able to return to
     * the group on their own using invite links, etc., unless unbanned first. The bot must be an administrator in the chat for this to work and must have the
     * appropriate admin rights.
     *
     * @param chatId Unique identifier for the target group or username of the target supergroup or channel (in the format @channelusername).
     * @param userId Unique identifier of the target user.
     * @param untilDate Optional. Date when the user will be unbanned, unix time. If user is banned for more than 366 days or less than 30 seconds from the
     * current time they are considered to be banned forever.
     * @return True on success.
     */
    @FormUrlEncoded
    @POST("kickChatMember")
    CompletableFuture<Boolean> kickChatMember(@Field("chat_id") long chatId, @Field("user_id") long userId, @Field("until_date") Long untilDate);

    /**
     * Use this method to unban a previously kicked user in a supergroup or channel. The user will not return to the group or channel automatically, but will be
     * able to join via link, etc. The bot must be an administrator for this to work.
     *
     * @param chatId Unique identifier for the target group or username of the target supergroup or channel (in the format @channelusername).
     * @param userId Unique identifier of the target user.
     * @return True on success.
     */
    @FormUrlEncoded
    @POST("unbanChatMember")
    CompletableFuture<Boolean> unbanChatMember(@Field("chat_id") String chatId, @Field("user_id") long userId);

    /**
     * Use this method to unban a previously kicked user in a supergroup or channel. The user will not return to the group or channel automatically, but will be
     * able to join via link, etc. The bot must be an administrator for this to work.
     *
     * @param chatId Unique identifier for the target group or username of the target supergroup or channel (in the format @channelusername).
     * @param userId Unique identifier of the target user.
     * @return True on success.
     */
    @FormUrlEncoded
    @POST("unbanChatMember")
    CompletableFuture<Boolean> unbanChatMember(@Field("chat_id") long chatId, @Field("user_id") long userId);

    /**
     * Use this method to restrict a user in a supergroup. The bot must be an administrator in the supergroup for this to work and must have the appropriate
     * admin rights. Pass True for all permissions to lift restrictions from a user.
     *
     * @param chatId Unique identifier for the target group or username of the target supergroup or channel (in the format @channelusername).
     * @param userId Unique identifier of the target user.
     * @param permissions New user permissions.
     * @param untilDate Optional. Date when restrictions will be lifted for the user, unix time. If user is restricted for more than 366 days or less than 30
     * seconds from the current time, they are considered to be restricted forever.
     * @return True on success.
     */
    @FormUrlEncoded
    @POST("restrictChatMember")
    CompletableFuture<Boolean> restrictChatMember(@Field("chat_id") String chatId, @Field("user_id") long userId,
            @Field("permissions") ChatPermissions permissions, @Field("until_date") Long untilDate);

    /**
     * Use this method to restrict a user in a supergroup. The bot must be an administrator in the supergroup for this to work and must have the appropriate
     * admin rights. Pass True for all permissions to lift restrictions from a user.
     *
     * @param chatId Unique identifier for the target group or username of the target supergroup or channel (in the format @channelusername).
     * @param userId Unique identifier of the target user.
     * @param permissions New user permissions.
     * @param untilDate Optional. Date when restrictions will be lifted for the user, unix time. If user is restricted for more than 366 days or less than 30
     * seconds from the current time, they are considered to be restricted forever.
     * @return True on success.
     */
    @FormUrlEncoded
    @POST("restrictChatMember")
    CompletableFuture<Boolean> restrictChatMember(@Field("chat_id") long chatId, @Field("user_id") long userId,
            @Field("permissions") ChatPermissions permissions, @Field("until_date") Long untilDate);

    /**
     * Use this method to promote or demote a user in a supergroup or a channel. The bot must be an administrator in the chat for this to work and must have the
     * appropriate admin rights. Pass False for all boolean parameters to demote a user.
     *
     * @param chatId Unique identifier for the target group or username of the target supergroup or channel (in the format @channelusername).
     * @param userId Unique identifier of the target user.
     * @param canChangeInfo Optional. Pass True, if the administrator can change chat title, photo and other settings.
     * @param canPostMessage Optional. Pass True, if the administrator can create channel posts, channels only.
     * @param canEditMessage Optional. Pass True, if the administrator can edit messages of other users and can pin messages, channels only.
     * @param canDeleteMessage Optional. Pass True, if the administrator can delete messages of other users.
     * @param canInviteUsers Optional. Pass True, if the administrator can invite new users to the chat.
     * @param canRestrictMembers Optional. Pass True, if the administrator can restrict, ban or unban chat members.
     * @param canPinMessage Optional. Pass True, if the administrator can pin messages, supergroups only.
     * @param canPromoteMembers Optional. Pass True, if the administrator can add new administrators with a subset of his own privileges or demote
     * administrators that he has promoted, directly or indirectly (promoted by administrators that were appointed by him).
     * @return True on success.
     */
    @FormUrlEncoded
    @POST("promoteChatMember")
    CompletableFuture<Boolean> promoteChatMember(@Field("chat_id") String chatId, @Field("user_id") long userId,
            @Field("can_change_info") Boolean canChangeInfo, @Field("can_post_messages") Boolean canPostMessage,
            @Field("can_edit_messages") Boolean canEditMessage, @Field("can_delete_messages") Boolean canDeleteMessage,
            @Field("can_invite_users") Boolean canInviteUsers, @Field("can_restrict_members") Boolean canRestrictMembers,
            @Field("can_pin_messages") Boolean canPinMessage, @Field("can_promote_members") Boolean canPromoteMembers);

    /**
     * Use this method to promote or demote a user in a supergroup or a channel. The bot must be an administrator in the chat for this to work and must have the
     * appropriate admin rights. Pass False for all boolean parameters to demote a user.
     *
     * @param chatId Unique identifier for the target group or username of the target supergroup or channel (in the format @channelusername).
     * @param userId Unique identifier of the target user.
     * @param canChangeInfo Optional. Pass True, if the administrator can change chat title, photo and other settings.
     * @param canPostMessage Optional. Pass True, if the administrator can create channel posts, channels only.
     * @param canEditMessage Optional. Pass True, if the administrator can edit messages of other users and can pin messages, channels only.
     * @param canDeleteMessage Optional. Pass True, if the administrator can delete messages of other users.
     * @param canInviteUsers Optional. Pass True, if the administrator can invite new users to the chat.
     * @param canRestrictMembers Optional. Pass True, if the administrator can restrict, ban or unban chat members.
     * @param canPinMessage Optional. Pass True, if the administrator can pin messages, supergroups only.
     * @param canPromoteMembers Optional. Pass True, if the administrator can add new administrators with a subset of his own privileges or demote
     * administrators that he has promoted, directly or indirectly (promoted by administrators that were appointed by him).
     * @return True on success.
     */
    @FormUrlEncoded
    @POST("promoteChatMember")
    CompletableFuture<Boolean> promoteChatMember(@Field("chat_id") long chatId, @Field("user_id") long userId,
            @Field("can_change_info") Boolean canChangeInfo, @Field("can_post_messages") Boolean canPostMessage,
            @Field("can_edit_messages") Boolean canEditMessage, @Field("can_delete_messages") Boolean canDeleteMessage,
            @Field("can_invite_users") Boolean canInviteUsers, @Field("can_restrict_members") Boolean canRestrictMembers,
            @Field("can_pin_messages") Boolean canPinMessage, @Field("can_promote_members") Boolean canPromoteMembers);

    /**
     * Use this method to set a custom title for an administrator in a supergroup promoted by the bot.
     *
     * @param chatId Unique identifier for the target group or username of the target supergroup or channel (in the format @channelusername).
     * @param userId Unique identifier of the target user.
     * @param customTitle New custom title for the administrator; 0-16 characters, emoji are not allowed.
     * @return True on success.
     */
    @FormUrlEncoded
    @POST("setChatAdministratorCustomTitle")
    CompletableFuture<Boolean> setChatAdministratorCustomTitle(@Field("chat_id") String chatId, @Field("user_id") long userId,
            @Field("custom_title") String customTitle);

    /**
     * Use this method to set a custom title for an administrator in a supergroup promoted by the bot.
     *
     * @param chatId Unique identifier for the target group or username of the target supergroup or channel (in the format @channelusername).
     * @param userId Unique identifier of the target user.
     * @param customTitle New custom title for the administrator; 0-16 characters, emoji are not allowed.
     * @return True on success.
     */
    @FormUrlEncoded
    @POST("setChatAdministratorCustomTitle")
    CompletableFuture<Boolean> setChatAdministratorCustomTitle(@Field("chat_id") long chatId, @Field("user_id") long userId,
            @Field("custom_title") String customTitle);

    /**
     * Use this method to set default chat permissions for all members. The bot must be an administrator in the group or a supergroup for this to work and must
     * have the can_restrict_members admin rights.
     *
     * @param chatId Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername).
     * @param permissions New default chat permissions.
     * @return True on success.
     */
    @FormUrlEncoded
    @POST("setChatPermissions")
    CompletableFuture<Boolean> setChatPermissions(@Field("chat_id") String chatId, @Field("permissions") ChatPermissions permissions);

    /**
     * Use this method to set default chat permissions for all members. The bot must be an administrator in the group or a supergroup for this to work and must
     * have the can_restrict_members admin rights.
     *
     * @param chatId Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername).
     * @param permissions New default chat permissions.
     * @return True on success.
     */
    @FormUrlEncoded
    @POST("setChatPermissions")
    CompletableFuture<Boolean> setChatPermissions(@Field("chat_id") long chatId, @Field("permissions") ChatPermissions permissions);

    /**
     * Use this method to generate a new invite link for a chat; any previously generated link is revoked. The bot must be an administrator in the chat for this
     * to work and must have the appropriate admin rights.
     *
     * Note: Each administrator in a chat generates their own invite links. Bots can't use invite links generated by other administrators. If you want your bot
     * to work with invite links, it will need to generate its own link using exportChatInviteLink – after this the link will become available to the bot via
     * the getChat method. If your bot needs to generate a new invite link replacing its previous one, use exportChatInviteLink again.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the format @channelusername).
     * @return the new invite link as String on success.
     */
    @FormUrlEncoded
    @POST("exportChatInviteLink")
    CompletableFuture<String> exportChatInviteLink(@Field("chat_id") String chatId);

    /**
     * Use this method to generate a new invite link for a chat; any previously generated link is revoked. The bot must be an administrator in the chat for this
     * to work and must have the appropriate admin rights.
     *
     * Note: Each administrator in a chat generates their own invite links. Bots can't use invite links generated by other administrators. If you want your bot
     * to work with invite links, it will need to generate its own link using exportChatInviteLink – after this the link will become available to the bot via
     * the getChat method. If your bot needs to generate a new invite link replacing its previous one, use exportChatInviteLink again.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the format @channelusername).
     * @return the new invite link as String on success.
     */
    @FormUrlEncoded
    @POST("exportChatInviteLink")
    CompletableFuture<String> exportChatInviteLink(@Field("chat_id") long chatId);

    /**
     * Use this method to delete a chat photo. Photos can't be changed for private chats. The bot must be an administrator in the chat for this to work and must
     * have the appropriate admin rights.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the format @channelusername).
     * @return True on success.
     */
    @FormUrlEncoded
    @POST("deleteChatPhoto")
    CompletableFuture<Boolean> deleteChatPhoto(@Field("chat_id") String chatId);

    /**
     * Use this method to delete a chat photo. Photos can't be changed for private chats. The bot must be an administrator in the chat for this to work and must
     * have the appropriate admin rights.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the format @channelusername).
     * @return True on success.
     */
    @FormUrlEncoded
    @POST("deleteChatPhoto")
    CompletableFuture<Boolean> deleteChatPhoto(@Field("chat_id") long chatId);

    /**
     * Use this method to change the title of a chat. Titles can't be changed for private chats. The bot must be an administrator in the chat for this to work
     * and must have the appropriate admin rights.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the format @channelusername).
     * @param title New chat title, 1-255 characters.
     * @return True on success.
     */
    @FormUrlEncoded
    @POST("setChatTitle")
    CompletableFuture<Boolean> setChatTitle(@Field("chat_id") String chatId, @Field("title") String title);

    /**
     * Use this method to change the title of a chat. Titles can't be changed for private chats. The bot must be an administrator in the chat for this to work
     * and must have the appropriate admin rights.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the format @channelusername).
     * @param title New chat title, 1-255 characters.
     * @return True on success.
     */
    @FormUrlEncoded
    @POST("setChatTitle")
    CompletableFuture<Boolean> setChatTitle(@Field("chat_id") long chatId, @Field("title") String title);

    /**
     * Use this method to change the description of a group, a supergroup or a channel. The bot must be an administrator in the chat for this to work and must
     * have the appropriate admin rights.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the format @channelusername).
     * @param description New chat description, 0-255 characters.
     * @return True on success.
     */
    @FormUrlEncoded
    @POST("setChatDescription")
    CompletableFuture<Boolean> setChatDescription(@Field("chat_id") String chatId, @Field("description") String description);

    /**
     * Use this method to change the description of a group, a supergroup or a channel. The bot must be an administrator in the chat for this to work and must
     * have the appropriate admin rights.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the format @channelusername).
     * @param description New chat description, 0-255 characters.
     * @return True on success.
     */
    @FormUrlEncoded
    @POST("setChatDescription")
    CompletableFuture<Boolean> setChatDescription(@Field("chat_id") long chatId, @Field("description") String description);

    /**
     * Use this method to pin a message in a group, a supergroup, or a channel. The bot must be an administrator in the chat for this to work and must have the
     * ‘can_pin_messages’ admin right in the supergroup or ‘can_edit_messages’ admin right in the channel.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the format @channelusername).
     * @param messageId Identifier of a message to pin.
     * @param disableNotification Pass True, if it is not necessary to send a notification to all chat members about the new pinned message. Notifications are
     * always disabled in channels.
     * @return True on success.
     */
    @FormUrlEncoded
    @POST("pinChatMessage")
    CompletableFuture<Boolean> pinChatMessage(@Field("chat_id") String chatId, @Field("message_id") long messageId,
            @Field("disable_notification") Boolean disableNotification);

    /**
     * Use this method to pin a message in a group, a supergroup, or a channel. The bot must be an administrator in the chat for this to work and must have the
     * ‘can_pin_messages’ admin right in the supergroup or ‘can_edit_messages’ admin right in the channel.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the format @channelusername).
     * @param messageId Identifier of a message to pin.
     * @param disableNotification Pass True, if it is not necessary to send a notification to all chat members about the new pinned message. Notifications are
     * always disabled in channels.
     * @return True on success.
     */
    @FormUrlEncoded
    @POST("pinChatMessage")
    CompletableFuture<Boolean> pinChatMessage(@Field("chat_id") long chatId, @Field("message_id") long messageId,
            @Field("disable_notification") Boolean disableNotification);

    /**
     * Use this method to unpin a message in a group, a supergroup, or a channel. The bot must be an administrator in the chat for this to work and must have
     * the ‘can_pin_messages’ admin right in the supergroup or ‘can_edit_messages’ admin right in the channel.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the format @channelusername).
     * @return True on success.
     */
    @FormUrlEncoded
    @POST("unpinChatMessage")
    CompletableFuture<Boolean> unpinChatMessage(@Field("chat_id") String chatId);

    /**
     * Use this method to unpin a message in a group, a supergroup, or a channel. The bot must be an administrator in the chat for this to work and must have
     * the ‘can_pin_messages’ admin right in the supergroup or ‘can_edit_messages’ admin right in the channel.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the format @channelusername).
     * @return True on success.
     */
    @FormUrlEncoded
    @POST("unpinChatMessage")
    CompletableFuture<Boolean> unpinChatMessage(@Field("chat_id") long chatId);

    /**
     * Use this method for your bot to leave a group, supergroup or channel.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the format @channelusername).
     * @return True on success.
     */
    @FormUrlEncoded
    @POST("leaveChat")
    CompletableFuture<Boolean> leaveChat(@Field("chat_id") String chatId);

    /**
     * Use this method for your bot to leave a group, supergroup or channel.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the format @channelusername).
     * @return True on success.
     */
    @FormUrlEncoded
    @POST("leaveChat")
    CompletableFuture<Boolean> leaveChat(@Field("chat_id") long chatId);

    /**
     * Use this method to get up to date information about the chat (current name of the user for one-on-one conversations, current username of a user, group or
     * channel, etc.).
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the format @channelusername).
     * @return a Chat object on success.
     */
    @FormUrlEncoded
    @POST("getChat")
    CompletableFuture<Chat> getChat(@Field("chat_id") String chatId);

    /**
     * Use this method to get up to date information about the chat (current name of the user for one-on-one conversations, current username of a user, group or
     * channel, etc.).
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the format @channelusername).
     * @return a Chat object on success.
     */
    @FormUrlEncoded
    @POST("getChat")
    CompletableFuture<Chat> getChat(@Field("chat_id") long chatId);

    /**
     * Use this method to get a list of administrators in a chat.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the format @channelusername).
     * @return an Array of ChatMember objects that contains information about all chat administrators except other bots. If the chat is a group or a supergroup
     * and no administrators were appointed, only the creator will be returned.
     */
    @FormUrlEncoded
    @POST("getChatAdministrators")
    CompletableFuture<List<ChatMember>> getChatAdministrators(@Field("chat_id") String chatId);

    /**
     * Use this method to get a list of administrators in a chat.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the format @channelusername).
     * @return an Array of ChatMember objects that contains information about all chat administrators except other bots. If the chat is a group or a supergroup
     * and no administrators were appointed, only the creator will be returned.
     */
    @FormUrlEncoded
    @POST("getChatAdministrators")
    CompletableFuture<List<ChatMember>> getChatAdministrators(@Field("chat_id") long chatId);

    /**
     * Use this method to get the number of members in a chat.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the format @channelusername).
     * @return Int on success.
     */
    @FormUrlEncoded
    @POST("getChatMembersCount")
    CompletableFuture<Integer> getChatMembersCount(@Field("chat_id") String chatId);

    /**
     * Use this method to get the number of members in a chat.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the format @channelusername).
     * @return Int on success.
     */
    @FormUrlEncoded
    @POST("getChatMembersCount")
    CompletableFuture<Integer> getChatMembersCount(@Field("chat_id") long chatId);

    /**
     * Use this method to get information about a member of a chat.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the format @channelusername).
     * @param userId Unique identifier of the target user.
     * @return a ChatMember object on success.
     */
    @FormUrlEncoded
    @POST("getChatMember")
    CompletableFuture<ChatMember> getChatMember(@Field("chat_id") String chatId, @Field("user_id") long userId);

    /**
     * Use this method to get information about a member of a chat.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the format @channelusername).
     * @param userId Unique identifier of the target user.
     * @return a ChatMember object on success.
     */
    @FormUrlEncoded
    @POST("getChatMember")
    CompletableFuture<ChatMember> getChatMember(@Field("chat_id") long chatId, @Field("user_id") long userId);

    /**
     * Use this method to send answers to callback queries sent from inline keyboards. The answer will be displayed to the user as a notification at the top of
     * the chat screen or as an alert.
     *
     * @param payload The request payload.
     * @return True on success.
     */
    @POST("answerCallbackQuery")
    CompletableFuture<Boolean> answerCallbackQuery(@Body AnswerCallbackQueryPayload payload);

    @POST("sendGame")
    CompletableFuture<Message> sendGame(@Body SendGamePayload payload);

    @POST("setGameScore")
    CompletableFuture<Message> setGameScoreByBot(@Body SetGameScorePayload payload);

    @POST("setGameScore")
    CompletableFuture<Boolean> setGameScore(@Body SetGameScorePayload payload);

    @POST("getGameHighScores")
    CompletableFuture<List<GameHighScore>> getGameHighScores(@Body GetGameScorePayload payload);

    static BotApiBuilder newBuilder(String botApiToken) {
        return new BotApiBuilder(botApiToken);
    }

    /**
     * @author SgrAlpha
     */
    class BotApiBuilder {

        private static final String BASE_URL_FORMAT = "https://api.telegram.org/bot%s/";

        private final String botApiToken;
        private boolean retry = false;
        private Logger logger;

        BotApiBuilder(final String botApiToken) {
            verifyToken(botApiToken);
            this.botApiToken = botApiToken;
        }

        /**
         * Enable retry
         *
         * @return the builder
         */
        public BotApiBuilder enableRetry() {
            this.retry = true;
            return this;
        }

        /**
         * @param logger the logger
         *
         * @return the builder
         */
        public BotApiBuilder setLogger(final Logger logger) {
            this.logger = logger;
            return this;
        }

        /**
         * @return the bot API client
         */
        public BotApi build() {
            Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                    .baseUrl(String.format(Locale.ENGLISH, BASE_URL_FORMAT, botApiToken))
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(JacksonConverterFactory.create(JsonUtil.getObjectMapper()))
                    .addCallAdapterFactory(new DefaultCallAdapterFactory(retry, Optional.ofNullable(logger).orElse(LoggerFactory.getLogger(BotApi.class))));
            OkHttpClient.Builder clientBuilder = new OkHttpClient().newBuilder()
                    .retryOnConnectionFailure(true)
                    .connectTimeout(5, TimeUnit.MINUTES)
                    .readTimeout(5, TimeUnit.MINUTES)
                    .writeTimeout(5, TimeUnit.MINUTES);
            retrofitBuilder.client(clientBuilder.build());
            return retrofitBuilder.build().create(BotApi.class);
        }

    }

}
