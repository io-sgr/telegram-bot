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
    CompletableFuture<List<Update>> getUpdates(@Body final GetUpdatesPayload payload);

    @POST("sendMessage")
    CompletableFuture<Message> sendMessage(@Body final SendMessagePayload payload);

    @POST("forwardMessage")
    CompletableFuture<Message> forwardMessage(@Body final ForwardMessagePayload payload);

    @POST("editMessageText")
    CompletableFuture<Message> editMessageText(@Body final EditMessageTextPayload payload);

    @POST("editMessageCaption")
    CompletableFuture<Message> editMessageCaption(@Body final EditMessageCaptionPayload payload);

    @POST("editMessageReplyMarkup")
    CompletableFuture<Message> editMessageReplyMarkup(@Body final EditMessageReplyMarkupPayload payload);

    @POST("deleteMessage")
    CompletableFuture<Boolean> deleteMessage(@Body final DeleteMessagePayload payload);

    @POST("answerInlineQuery")
    CompletableFuture<Boolean> answerInlineQuery(@Body final AnswerInlineQueryPayload payload);

    @FormUrlEncoded
    @POST("kickChatMember")
    CompletableFuture<Boolean> kickChatMember(@Field("chat_id") final String chatId, @Field("user_id") final long userId);

    @FormUrlEncoded
    @POST("kickChatMember")
    CompletableFuture<Boolean> kickChatMember(@Field("chat_id") final long chatId, @Field("user_id") final long userId);

    @FormUrlEncoded
    @POST("unbanChatMember")
    CompletableFuture<Boolean> unbanChatMember(@Field("chat_id") final String chatId, @Field("user_id") final long userId);

    @FormUrlEncoded
    @POST("unbanChatMember")
    CompletableFuture<Boolean> unbanChatMember(@Field("chat_id") final long chatId, @Field("user_id") final long userId);

    @FormUrlEncoded
    @POST("restrictChatMember")
    CompletableFuture<Boolean> restrictChatMember(@Field("chat_id") final String chatId, @Field("user_id") final long userId, @Field("until_date") final long untilDate, @Field("can_send_messages") final Boolean canSendMessage, @Field("can_send_media_messages") final Boolean canSendMediaMessage, @Field("can_send_other_messages") final Boolean canSendOtherMessage, @Field("can_add_web_page_previews") final Boolean canAddWebPagePreviews);

    @FormUrlEncoded
    @POST("restrictChatMember")
    CompletableFuture<Boolean> restrictChatMember(@Field("chat_id") final long chatId, @Field("user_id") final long userId, @Field("until_date") final long untilDate, @Field("can_send_messages") final Boolean canSendMessage, @Field("can_send_media_messages") final Boolean canSendMediaMessage, @Field("can_send_other_messages") final Boolean canSendOtherMessage, @Field("can_add_web_page_previews") final Boolean canAddWebPagePreviews);

    @FormUrlEncoded
    @POST("promoteChatMember")
    CompletableFuture<Boolean> promoteChatMember(@Field("chat_id") final String chatId, @Field("user_id") final long userId, @Field("can_change_info") final Boolean canChangeInfo, @Field("can_post_messages") final Boolean canPostMessage, @Field("can_edit_messages") final Boolean canEditMessage, @Field("can_delete_messages") final Boolean canDeleteMessage, @Field("can_invite_users") final Boolean canInviteUsers, @Field("can_restrict_members") final Boolean canRestrictMembers, @Field("can_pin_messages") final Boolean canPinMessage, @Field("can_promote_members") final Boolean canPromoteMembers);

    @FormUrlEncoded
    @POST("promoteChatMember")
    CompletableFuture<Boolean> promoteChatMember(@Field("chat_id") final long chatId, @Field("user_id") final long userId, @Field("can_change_info") final Boolean canChangeInfo, @Field("can_post_messages") final Boolean canPostMessage, @Field("can_edit_messages") final Boolean canEditMessage, @Field("can_delete_messages") final Boolean canDeleteMessage, @Field("can_invite_users") final Boolean canInviteUsers, @Field("can_restrict_members") final Boolean canRestrictMembers, @Field("can_pin_messages") final Boolean canPinMessage, @Field("can_promote_members") final Boolean canPromoteMembers);

    @FormUrlEncoded
    @POST("exportChatInviteLink")
    CompletableFuture<String> exportChatInviteLink(@Field("chat_id") final String chatId);

    @FormUrlEncoded
    @POST("exportChatInviteLink")
    CompletableFuture<String> exportChatInviteLink(@Field("chat_id") final long chatId);

    @FormUrlEncoded
    @POST("deleteChatPhoto")
    CompletableFuture<Boolean> deleteChatPhoto(@Field("chat_id") final String chatId);

    @FormUrlEncoded
    @POST("deleteChatPhoto")
    CompletableFuture<Boolean> deleteChatPhoto(@Field("chat_id") final long chatId);

    @FormUrlEncoded
    @POST("setChatTitle")
    CompletableFuture<Boolean> setChatTitle(@Field("chat_id") final String chatId, @Field("title") final String title);

    @FormUrlEncoded
    @POST("setChatTitle")
    CompletableFuture<Boolean> setChatTitle(@Field("chat_id") final long chatId, @Field("title") final String title);

    @FormUrlEncoded
    @POST("setChatDescription")
    CompletableFuture<Boolean> setChatDescription(@Field("chat_id") final String chatId, @Field("description") final String description);

    @FormUrlEncoded
    @POST("setChatDescription")
    CompletableFuture<Boolean> setChatDescription(@Field("chat_id") final long chatId, @Field("description") final String description);

    @FormUrlEncoded
    @POST("pinChatMessage")
    CompletableFuture<Boolean> pinChatMessage(@Field("chat_id") final String chatId, @Field("message_id") final long messageId);

    @FormUrlEncoded
    @POST("pinChatMessage")
    CompletableFuture<Boolean> pinChatMessage(@Field("chat_id") final long chatId, @Field("message_id") final long messageId);

    @FormUrlEncoded
    @POST("unpinChatMessage")
    CompletableFuture<Boolean> unpinChatMessage(@Field("chat_id") final String chatId);

    @FormUrlEncoded
    @POST("unpinChatMessage")
    CompletableFuture<Boolean> unpinChatMessage(@Field("chat_id") final long chatId);

    @FormUrlEncoded
    @POST("leaveChat")
    CompletableFuture<Boolean> leaveChat(@Field("chat_id") final String chatId);

    @FormUrlEncoded
    @POST("leaveChat")
    CompletableFuture<Boolean> leaveChat(@Field("chat_id") final long chatId);

    @FormUrlEncoded
    @POST("getChat")
    CompletableFuture<Chat> getChat(@Field("chat_id") final String chatId);

    @FormUrlEncoded
    @POST("getChat")
    CompletableFuture<Chat> getChat(@Field("chat_id") final long chatId);

    @FormUrlEncoded
    @POST("getChatAdministrators")
    CompletableFuture<List<ChatMember>> getChatAdministrators(@Field("chat_id") final String chatId);

    @FormUrlEncoded
    @POST("getChatAdministrators")
    CompletableFuture<List<ChatMember>> getChatAdministrators(@Field("chat_id") final long chatId);

    @FormUrlEncoded
    @POST("getChatMembersCount")
    CompletableFuture<Integer> getChatMembersCount(@Field("chat_id") final String chatId);

    @FormUrlEncoded
    @POST("getChatMembersCount")
    CompletableFuture<Integer> getChatMembersCount(@Field("chat_id") final long chatId);

    @FormUrlEncoded
    @POST("getChatMember")
    CompletableFuture<ChatMember> getChatMember(@Field("chat_id") final String chatId, @Field("user_id") final long userId);

    @FormUrlEncoded
    @POST("getChatMember")
    CompletableFuture<ChatMember> getChatMember(@Field("chat_id") final long chatId, @Field("user_id") final long userId);

    @POST("answerCallbackQuery")
    CompletableFuture<Boolean> answerCallbackQuery(@Body final AnswerCallbackQueryPayload payload);

    @POST("sendGame")
    CompletableFuture<Message> sendGame(@Body final SendGamePayload payload);

    @POST("setGameScore")
    CompletableFuture<Message> setGameScoreByBot(@Body final SetGameScorePayload payload);

    @POST("setGameScore")
    CompletableFuture<Boolean> setGameScore(@Body final SetGameScorePayload payload);

    @POST("getGameHighScores")
    CompletableFuture<List<GameHighScore>> getGameHighScores(@Body final GetGameScorePayload payload);

}
