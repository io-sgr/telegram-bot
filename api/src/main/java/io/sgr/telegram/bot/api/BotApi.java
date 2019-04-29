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

package io.sgr.telegram.bot.api;

import io.sgr.telegram.bot.api.models.Chat;
import io.sgr.telegram.bot.api.models.ChatMember;
import io.sgr.telegram.bot.api.models.Message;
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
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author SgrAlpha
 */
public interface BotApi {

    @POST("getMe")
    CompletableFuture<User> getMe();

    @POST("getWebhookInfo")
    CompletableFuture<WebhookInfo> getWebhookInfo();

    @POST("getUpdates")
    CompletableFuture<List<Update>> getUpdates(@Body GetUpdatesPayload payload);

    @POST("sendMessage")
    CompletableFuture<Message> sendMessage(@Body SendMessagePayload payload);

    @POST("forwardMessage")
    CompletableFuture<Message> forwardMessage(@Body ForwardMessagePayload payload);

    @POST("editMessageText")
    CompletableFuture<Message> editMessageText(@Body EditMessageTextPayload payload);

    @POST("editMessageCaption")
    CompletableFuture<Message> editMessageCaption(@Body EditMessageCaptionPayload payload);

    @POST("editMessageReplyMarkup")
    CompletableFuture<Message> editMessageReplyMarkup(@Body EditMessageReplyMarkupPayload payload);

    @POST("deleteMessage")
    CompletableFuture<Boolean> deleteMessage(@Body DeleteMessagePayload payload);

    @POST("answerInlineQuery")
    CompletableFuture<Boolean> answerInlineQuery(@Body AnswerInlineQueryPayload payload);

    @FormUrlEncoded
    @POST("kickChatMember")
    CompletableFuture<Boolean> kickChatMember(@Field("chat_id") String chatId, @Field("user_id") long userId);

    @FormUrlEncoded
    @POST("kickChatMember")
    CompletableFuture<Boolean> kickChatMember(@Field("chat_id") long chatId, @Field("user_id") long userId);

    @FormUrlEncoded
    @POST("unbanChatMember")
    CompletableFuture<Boolean> unbanChatMember(@Field("chat_id") String chatId, @Field("user_id") long userId);

    @FormUrlEncoded
    @POST("unbanChatMember")
    CompletableFuture<Boolean> unbanChatMember(@Field("chat_id") long chatId, @Field("user_id") long userId);

    @FormUrlEncoded
    @POST("restrictChatMember")
    CompletableFuture<Boolean> restrictChatMember(@Field("chat_id") String chatId, @Field("user_id") long userId, @Field("until_date") long untilDate,
            @Field("can_send_messages") Boolean canSendMessage, @Field("can_send_media_messages") Boolean canSendMediaMessage,
            @Field("can_send_other_messages") Boolean canSendOtherMessage, @Field("can_add_web_page_previews") Boolean canAddWebPagePreviews);

    @FormUrlEncoded
    @POST("restrictChatMember")
    CompletableFuture<Boolean> restrictChatMember(@Field("chat_id") long chatId, @Field("user_id") long userId, @Field("until_date") long untilDate,
            @Field("can_send_messages") Boolean canSendMessage, @Field("can_send_media_messages") Boolean canSendMediaMessage,
            @Field("can_send_other_messages") Boolean canSendOtherMessage, @Field("can_add_web_page_previews") Boolean canAddWebPagePreviews);

    @FormUrlEncoded
    @POST("promoteChatMember")
    CompletableFuture<Boolean> promoteChatMember(@Field("chat_id") String chatId, @Field("user_id") long userId,
            @Field("can_change_info") Boolean canChangeInfo, @Field("can_post_messages") Boolean canPostMessage,
            @Field("can_edit_messages") Boolean canEditMessage, @Field("can_delete_messages") Boolean canDeleteMessage,
            @Field("can_invite_users") Boolean canInviteUsers, @Field("can_restrict_members") Boolean canRestrictMembers,
            @Field("can_pin_messages") Boolean canPinMessage, @Field("can_promote_members") Boolean canPromoteMembers);

    @FormUrlEncoded
    @POST("promoteChatMember")
    CompletableFuture<Boolean> promoteChatMember(@Field("chat_id") long chatId, @Field("user_id") long userId,
            @Field("can_change_info") Boolean canChangeInfo, @Field("can_post_messages") Boolean canPostMessage,
            @Field("can_edit_messages") Boolean canEditMessage, @Field("can_delete_messages") Boolean canDeleteMessage,
            @Field("can_invite_users") Boolean canInviteUsers, @Field("can_restrict_members") Boolean canRestrictMembers,
            @Field("can_pin_messages") Boolean canPinMessage, @Field("can_promote_members") Boolean canPromoteMembers);

    @FormUrlEncoded
    @POST("exportChatInviteLink")
    CompletableFuture<String> exportChatInviteLink(@Field("chat_id") String chatId);

    @FormUrlEncoded
    @POST("exportChatInviteLink")
    CompletableFuture<String> exportChatInviteLink(@Field("chat_id") long chatId);

    @FormUrlEncoded
    @POST("deleteChatPhoto")
    CompletableFuture<Boolean> deleteChatPhoto(@Field("chat_id") String chatId);

    @FormUrlEncoded
    @POST("deleteChatPhoto")
    CompletableFuture<Boolean> deleteChatPhoto(@Field("chat_id") long chatId);

    @FormUrlEncoded
    @POST("setChatTitle")
    CompletableFuture<Boolean> setChatTitle(@Field("chat_id") String chatId, @Field("title") String title);

    @FormUrlEncoded
    @POST("setChatTitle")
    CompletableFuture<Boolean> setChatTitle(@Field("chat_id") long chatId, @Field("title") String title);

    @FormUrlEncoded
    @POST("setChatDescription")
    CompletableFuture<Boolean> setChatDescription(@Field("chat_id") String chatId, @Field("description") String description);

    @FormUrlEncoded
    @POST("setChatDescription")
    CompletableFuture<Boolean> setChatDescription(@Field("chat_id") long chatId, @Field("description") String description);

    @FormUrlEncoded
    @POST("pinChatMessage")
    CompletableFuture<Boolean> pinChatMessage(@Field("chat_id") String chatId, @Field("message_id") long messageId);

    @FormUrlEncoded
    @POST("pinChatMessage")
    CompletableFuture<Boolean> pinChatMessage(@Field("chat_id") long chatId, @Field("message_id") long messageId);

    @FormUrlEncoded
    @POST("unpinChatMessage")
    CompletableFuture<Boolean> unpinChatMessage(@Field("chat_id") String chatId);

    @FormUrlEncoded
    @POST("unpinChatMessage")
    CompletableFuture<Boolean> unpinChatMessage(@Field("chat_id") long chatId);

    @FormUrlEncoded
    @POST("leaveChat")
    CompletableFuture<Boolean> leaveChat(@Field("chat_id") String chatId);

    @FormUrlEncoded
    @POST("leaveChat")
    CompletableFuture<Boolean> leaveChat(@Field("chat_id") long chatId);

    @FormUrlEncoded
    @POST("getChat")
    CompletableFuture<Chat> getChat(@Field("chat_id") String chatId);

    @FormUrlEncoded
    @POST("getChat")
    CompletableFuture<Chat> getChat(@Field("chat_id") long chatId);

    @FormUrlEncoded
    @POST("getChatAdministrators")
    CompletableFuture<List<ChatMember>> getChatAdministrators(@Field("chat_id") String chatId);

    @FormUrlEncoded
    @POST("getChatAdministrators")
    CompletableFuture<List<ChatMember>> getChatAdministrators(@Field("chat_id") long chatId);

    @FormUrlEncoded
    @POST("getChatMembersCount")
    CompletableFuture<Integer> getChatMembersCount(@Field("chat_id") String chatId);

    @FormUrlEncoded
    @POST("getChatMembersCount")
    CompletableFuture<Integer> getChatMembersCount(@Field("chat_id") long chatId);

    @FormUrlEncoded
    @POST("getChatMember")
    CompletableFuture<ChatMember> getChatMember(@Field("chat_id") String chatId, @Field("user_id") long userId);

    @FormUrlEncoded
    @POST("getChatMember")
    CompletableFuture<ChatMember> getChatMember(@Field("chat_id") long chatId, @Field("user_id") long userId);

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

}
