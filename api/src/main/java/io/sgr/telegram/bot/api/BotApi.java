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
import retrofit2.http.Path;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author SgrAlpha
 */
public interface BotApi {

    @POST("/bot{apiToken}/getMe")
    CompletableFuture<User> getMe(@Path("apiToken") final String apiToken);

    @POST("/bot{apiToken}/getWebhookInfo")
    CompletableFuture<WebhookInfo> getWebhookInfo(@Path("apiToken") final String apiToken);

    @POST("/bot{apiToken}/getUpdates")
    CompletableFuture<List<Update>> getUpdates(@Path("apiToken") final String apiToken, @Body final GetUpdatesPayload payload);

    @POST("/bot{apiToken}/sendMessage")
    CompletableFuture<Message> sendMessage(@Path("apiToken") final String apiToken, @Body final SendMessagePayload payload);

    @POST("/bot{apiToken}/forwardMessage")
    CompletableFuture<Message> forwardMessage(@Path("apiToken") final String apiToken, @Body final ForwardMessagePayload payload);

    @POST("/bot{apiToken}/editMessageText")
    CompletableFuture<Message> editMessageText(@Path("apiToken") final String apiToken, @Body final EditMessageTextPayload payload);

    @POST("/bot{apiToken}/editMessageCaption")
    CompletableFuture<Message> editMessageCaption(@Path("apiToken") final String apiToken, @Body final EditMessageCaptionPayload payload);

    @POST("/bot{apiToken}/editMessageReplyMarkup")
    CompletableFuture<Message> editMessageReplyMarkup(@Path("apiToken") final String apiToken, @Body final EditMessageReplyMarkupPayload payload);

    @POST("/bot{apiToken}/deleteMessage")
    CompletableFuture<Boolean> deleteMessage(@Path("apiToken") final String apiToken, @Body final DeleteMessagePayload payload);

    @POST("/bot{apiToken}/answerInlineQuery")
    CompletableFuture<Boolean> answerInlineQuery(@Path("apiToken") final String apiToken, @Body final AnswerInlineQueryPayload payload);

    @FormUrlEncoded
    @POST("/bot{apiToken}/kickChatMember")
    CompletableFuture<Boolean> kickChatMember(@Path("apiToken") final String apiToken, @Field("chat_id") final String chatId, @Field("user_id") final long userId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/kickChatMember")
    CompletableFuture<Boolean> kickChatMember(@Path("apiToken") final String apiToken, @Field("chat_id") final long chatId, @Field("user_id") final long userId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/unbanChatMember")
    CompletableFuture<Boolean> unbanChatMember(@Path("apiToken") final String apiToken, @Field("chat_id") final String chatId, @Field("user_id") final long userId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/unbanChatMember")
    CompletableFuture<Boolean> unbanChatMember(@Path("apiToken") final String apiToken, @Field("chat_id") final long chatId, @Field("user_id") final long userId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/restrictChatMember")
    CompletableFuture<Boolean> restrictChatMember(@Path("apiToken") final String apiToken, @Field("chat_id") final String chatId, @Field("user_id") final long userId, @Field("until_date") final long untilDate, @Field("can_send_messages") final Boolean canSendMessage, @Field("can_send_media_messages") final Boolean canSendMediaMessage, @Field("can_send_other_messages") final Boolean canSendOtherMessage, @Field("can_add_web_page_previews") final Boolean canAddWebPagePreviews);

    @FormUrlEncoded
    @POST("/bot{apiToken}/restrictChatMember")
    CompletableFuture<Boolean> restrictChatMember(@Path("apiToken") final String apiToken, @Field("chat_id") final long chatId, @Field("user_id") final long userId, @Field("until_date") final long untilDate, @Field("can_send_messages") final Boolean canSendMessage, @Field("can_send_media_messages") final Boolean canSendMediaMessage, @Field("can_send_other_messages") final Boolean canSendOtherMessage, @Field("can_add_web_page_previews") final Boolean canAddWebPagePreviews);

    @FormUrlEncoded
    @POST("/bot{apiToken}/promoteChatMember")
    CompletableFuture<Boolean> promoteChatMember(@Path("apiToken") final String apiToken, @Field("chat_id") final String chatId, @Field("user_id") final long userId, @Field("can_change_info") final Boolean canChangeInfo, @Field("can_post_messages") final Boolean canPostMessage, @Field("can_edit_messages") final Boolean canEditMessage, @Field("can_delete_messages") final Boolean canDeleteMessage, @Field("can_invite_users") final Boolean canInviteUsers, @Field("can_restrict_members") final Boolean canRestrictMembers, @Field("can_pin_messages") final Boolean canPinMessage, @Field("can_promote_members") final Boolean canPromoteMembers);

    @FormUrlEncoded
    @POST("/bot{apiToken}/promoteChatMember")
    CompletableFuture<Boolean> promoteChatMember(@Path("apiToken") final String apiToken, @Field("chat_id") final long chatId, @Field("user_id") final long userId, @Field("can_change_info") final Boolean canChangeInfo, @Field("can_post_messages") final Boolean canPostMessage, @Field("can_edit_messages") final Boolean canEditMessage, @Field("can_delete_messages") final Boolean canDeleteMessage, @Field("can_invite_users") final Boolean canInviteUsers, @Field("can_restrict_members") final Boolean canRestrictMembers, @Field("can_pin_messages") final Boolean canPinMessage, @Field("can_promote_members") final Boolean canPromoteMembers);

    @FormUrlEncoded
    @POST("/bot{apiToken}/exportChatInviteLink")
    CompletableFuture<String> exportChatInviteLink(@Path("apiToken") final String apiToken, @Field("chat_id") final String chatId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/exportChatInviteLink")
    CompletableFuture<String> exportChatInviteLink(@Path("apiToken") final String apiToken, @Field("chat_id") final long chatId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/deleteChatPhoto")
    CompletableFuture<Boolean> deleteChatPhoto(@Path("apiToken") final String apiToken, @Field("chat_id") final String chatId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/deleteChatPhoto")
    CompletableFuture<Boolean> deleteChatPhoto(@Path("apiToken") final String apiToken, @Field("chat_id") final long chatId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/setChatTitle")
    CompletableFuture<Boolean> setChatTitle(@Path("apiToken") final String apiToken, @Field("chat_id") final String chatId, @Field("title") final String title);

    @FormUrlEncoded
    @POST("/bot{apiToken}/setChatTitle")
    CompletableFuture<Boolean> setChatTitle(@Path("apiToken") final String apiToken, @Field("chat_id") final long chatId, @Field("title") final String title);

    @FormUrlEncoded
    @POST("/bot{apiToken}/setChatDescription")
    CompletableFuture<Boolean> setChatDescription(@Path("apiToken") final String apiToken, @Field("chat_id") final String chatId, @Field("description") final String description);

    @FormUrlEncoded
    @POST("/bot{apiToken}/setChatDescription")
    CompletableFuture<Boolean> setChatDescription(@Path("apiToken") final String apiToken, @Field("chat_id") final long chatId, @Field("description") final String description);

    @FormUrlEncoded
    @POST("/bot{apiToken}/pinChatMessage")
    CompletableFuture<Boolean> pinChatMessage(@Path("apiToken") final String apiToken, @Field("chat_id") final String chatId, @Field("message_id") final long messageId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/pinChatMessage")
    CompletableFuture<Boolean> pinChatMessage(@Path("apiToken") final String apiToken, @Field("chat_id") final long chatId, @Field("message_id") final long messageId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/unpinChatMessage")
    CompletableFuture<Boolean> unpinChatMessage(@Path("apiToken") final String apiToken, @Field("chat_id") final String chatId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/unpinChatMessage")
    CompletableFuture<Boolean> unpinChatMessage(@Path("apiToken") final String apiToken, @Field("chat_id") final long chatId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/leaveChat")
    CompletableFuture<Boolean> leaveChat(@Path("apiToken") final String apiToken, @Field("chat_id") final String chatId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/leaveChat")
    CompletableFuture<Boolean> leaveChat(@Path("apiToken") final String apiToken, @Field("chat_id") final long chatId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/getChat")
    CompletableFuture<Chat> getChat(@Path("apiToken") final String apiToken, @Field("chat_id") final String chatId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/getChat")
    CompletableFuture<Chat> getChat(@Path("apiToken") final String apiToken, @Field("chat_id") final long chatId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/getChatAdministrators")
    CompletableFuture<List<ChatMember>> getChatAdministrators(@Path("apiToken") final String apiToken, @Field("chat_id") final String chatId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/getChatAdministrators")
    CompletableFuture<List<ChatMember>> getChatAdministrators(@Path("apiToken") final String apiToken, @Field("chat_id") final long chatId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/getChatMembersCount")
    CompletableFuture<Integer> getChatMembersCount(@Path("apiToken") final String apiToken, @Field("chat_id") final String chatId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/getChatMembersCount")
    CompletableFuture<Integer> getChatMembersCount(@Path("apiToken") final String apiToken, @Field("chat_id") final long chatId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/getChatMember")
    CompletableFuture<ChatMember> getChatMember(@Path("apiToken") final String apiToken, @Field("chat_id") final String chatId, @Field("user_id") final long userId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/getChatMember")
    CompletableFuture<ChatMember> getChatMember(@Path("apiToken") final String apiToken, @Field("chat_id") final long chatId, @Field("user_id") final long userId);

    @POST("/bot{apiToken}/answerCallbackQuery")
    CompletableFuture<Boolean> answerCallbackQuery(@Path("apiToken") final String apiToken, @Body final AnswerCallbackQueryPayload payload);

    @POST("/bot{apiToken}/sendGame")
    CompletableFuture<Message> sendGame(@Path("apiToken") final String apiToken, @Body final SendGamePayload payload);

    @POST("/bot{apiToken}/setGameScore")
    CompletableFuture<Message> setGameScoreByBot(@Path("apiToken") final String apiToken, @Body final SetGameScorePayload payload);

    @POST("/bot{apiToken}/setGameScore")
    CompletableFuture<Boolean> setGameScore(@Path("apiToken") final String apiToken, @Body final SetGameScorePayload payload);

    @POST("/bot{apiToken}/getGameHighScores")
    CompletableFuture<List<GameHighScore>> getGameHighScores(@Path("apiToken") final String apiToken, @Body final GetGameScorePayload payload);

}
