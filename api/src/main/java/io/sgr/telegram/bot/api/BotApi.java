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
import io.sgr.telegram.bot.api.models.http.ApiResponse;
import io.sgr.telegram.bot.api.models.http.DeleteMessagePayload;
import io.sgr.telegram.bot.api.models.http.EditMessageCaptionPayload;
import io.sgr.telegram.bot.api.models.http.EditMessageReplyMarkupPayload;
import io.sgr.telegram.bot.api.models.http.EditMessageTextPayload;
import io.sgr.telegram.bot.api.models.http.ForwardMessagePayload;
import io.sgr.telegram.bot.api.models.http.GetUpdatesPayload;
import io.sgr.telegram.bot.api.models.http.SendMessagePayload;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

/**
 * @author SgrAlpha
 */
public interface BotApi {

    @POST("/bot{apiToken}/getMe")
    Call<ApiResponse<User>> getMe(@Path("apiToken") final String apiToken);

    @POST("/bot{apiToken}/getWebhookInfo")
    Call<ApiResponse<WebhookInfo>> getWebhookInfo(@Path("apiToken") final String apiToken);

    @POST("/bot{apiToken}/getUpdates")
    Call<ApiResponse<List<Update>>> getUpdates(@Path("apiToken") final String apiToken, @Body final GetUpdatesPayload payload);

    @POST("/bot{apiToken}/sendMessage")
    Call<ApiResponse<Message>> sendMessage(@Path("apiToken") final String apiToken, @Body final SendMessagePayload payload);

    @POST("/bot{apiToken}/forwardMessage")
    Call<ApiResponse<Message>> forwardMessage(@Path("apiToken") final String apiToken, @Body final ForwardMessagePayload payload);

    @POST("/bot{apiToken}/editMessageText")
    Call<ApiResponse<Message>> editMessageText(@Path("apiToken") final String apiToken, @Body final EditMessageTextPayload payload);

    @POST("/bot{apiToken}/editMessageCaption")
    Call<ApiResponse<Message>> editMessageCaption(@Path("apiToken") final String apiToken, @Body final EditMessageCaptionPayload payload);

    @POST("/bot{apiToken}/editMessageReplyMarkup")
    Call<ApiResponse<Message>> editMessageReplyMarkup(@Path("apiToken") final String apiToken, @Body final EditMessageReplyMarkupPayload payload);

    @POST("/bot{apiToken}/deleteMessage")
    Call<ApiResponse<Boolean>> deleteMessage(@Path("apiToken") final String apiToken, @Body final DeleteMessagePayload payload);

    @POST("/bot{apiToken}/answerInlineQuery")
    Call<ApiResponse<Boolean>> answerInlineQuery(@Path("apiToken") final String apiToken, @Body final AnswerInlineQueryPayload payload);

    @FormUrlEncoded
    @POST("/bot{apiToken}/kickChatMember")
    Call<ApiResponse<Boolean>> kickChatMember(@Path("apiToken") final String apiToken, @Field("chat_id") final String chatId, @Field("user_id") final long userId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/kickChatMember")
    Call<ApiResponse<Boolean>> kickChatMember(@Path("apiToken") final String apiToken, @Field("chat_id") final long chatId, @Field("user_id") final long userId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/unbanChatMember")
    Call<ApiResponse<Boolean>> unbanChatMember(@Path("apiToken") final String apiToken, @Field("chat_id") final String chatId, @Field("user_id") final long userId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/unbanChatMember")
    Call<ApiResponse<Boolean>> unbanChatMember(@Path("apiToken") final String apiToken, @Field("chat_id") final long chatId, @Field("user_id") final long userId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/restrictChatMember")
    Call<ApiResponse<Boolean>> restrictChatMember(@Path("apiToken") final String apiToken, @Field("chat_id") final String chatId, @Field("user_id") final long userId, @Field("until_date") final long untilDate, @Field("can_send_messages") final Boolean canSendMessage, @Field("can_send_media_messages") final Boolean canSendMediaMessage, @Field("can_send_other_messages") final Boolean canSendOtherMessage, @Field("can_add_web_page_previews") final Boolean canAddWebPagePreviews);

    @FormUrlEncoded
    @POST("/bot{apiToken}/restrictChatMember")
    Call<ApiResponse<Boolean>> restrictChatMember(@Path("apiToken") final String apiToken, @Field("chat_id") final long chatId, @Field("user_id") final long userId, @Field("until_date") final long untilDate, @Field("can_send_messages") final Boolean canSendMessage, @Field("can_send_media_messages") final Boolean canSendMediaMessage, @Field("can_send_other_messages") final Boolean canSendOtherMessage, @Field("can_add_web_page_previews") final Boolean canAddWebPagePreviews);

    @FormUrlEncoded
    @POST("/bot{apiToken}/promoteChatMember")
    Call<ApiResponse<Boolean>> promoteChatMember(@Path("apiToken") final String apiToken, @Field("chat_id") final String chatId, @Field("user_id") final long userId, @Field("can_change_info") final Boolean canChangeInfo, @Field("can_post_messages") final Boolean canPostMessage, @Field("can_edit_messages") final Boolean canEditMessage, @Field("can_delete_messages") final Boolean canDeleteMessage, @Field("can_invite_users") final Boolean canInviteUsers, @Field("can_restrict_members") final Boolean canRestrictMembers, @Field("can_pin_messages") final Boolean canPinMessage, @Field("can_promote_members") final Boolean canPromoteMembers);

    @FormUrlEncoded
    @POST("/bot{apiToken}/promoteChatMember")
    Call<ApiResponse<Boolean>> promoteChatMember(@Path("apiToken") final String apiToken, @Field("chat_id") final long chatId, @Field("user_id") final long userId, @Field("can_change_info") final Boolean canChangeInfo, @Field("can_post_messages") final Boolean canPostMessage, @Field("can_edit_messages") final Boolean canEditMessage, @Field("can_delete_messages") final Boolean canDeleteMessage, @Field("can_invite_users") final Boolean canInviteUsers, @Field("can_restrict_members") final Boolean canRestrictMembers, @Field("can_pin_messages") final Boolean canPinMessage, @Field("can_promote_members") final Boolean canPromoteMembers);

    @FormUrlEncoded
    @POST("/bot{apiToken}/exportChatInviteLink")
    Call<ApiResponse<String>> exportChatInviteLink(@Path("apiToken") final String apiToken, @Field("chat_id") final String chatId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/exportChatInviteLink")
    Call<ApiResponse<String>> exportChatInviteLink(@Path("apiToken") final String apiToken, @Field("chat_id") final long chatId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/deleteChatPhoto")
    Call<ApiResponse<Boolean>> deleteChatPhoto(@Path("apiToken") final String apiToken, @Field("chat_id") final String chatId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/deleteChatPhoto")
    Call<ApiResponse<Boolean>> deleteChatPhoto(@Path("apiToken") final String apiToken, @Field("chat_id") final long chatId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/setChatTitle")
    Call<ApiResponse<Boolean>> setChatTitle(@Path("apiToken") final String apiToken, @Field("chat_id") final String chatId, @Field("title") final String title);

    @FormUrlEncoded
    @POST("/bot{apiToken}/setChatTitle")
    Call<ApiResponse<Boolean>> setChatTitle(@Path("apiToken") final String apiToken, @Field("chat_id") final long chatId, @Field("title") final String title);

    @FormUrlEncoded
    @POST("/bot{apiToken}/setChatDescription")
    Call<ApiResponse<Boolean>> setChatDescription(@Path("apiToken") final String apiToken, @Field("chat_id") final String chatId, @Field("description") final String description);

    @FormUrlEncoded
    @POST("/bot{apiToken}/setChatDescription")
    Call<ApiResponse<Boolean>> setChatDescription(@Path("apiToken") final String apiToken, @Field("chat_id") final long chatId, @Field("description") final String description);

    @FormUrlEncoded
    @POST("/bot{apiToken}/pinChatMessage")
    Call<ApiResponse<Boolean>> pinChatMessage(@Path("apiToken") final String apiToken, @Field("chat_id") final String chatId, @Field("message_id") final long messageId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/pinChatMessage")
    Call<ApiResponse<Boolean>> pinChatMessage(@Path("apiToken") final String apiToken, @Field("chat_id") final long chatId, @Field("message_id") final long messageId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/unpinChatMessage")
    Call<ApiResponse<Boolean>> unpinChatMessage(@Path("apiToken") final String apiToken, @Field("chat_id") final String chatId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/unpinChatMessage")
    Call<ApiResponse<Boolean>> unpinChatMessage(@Path("apiToken") final String apiToken, @Field("chat_id") final long chatId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/leaveChat")
    Call<ApiResponse<Boolean>> leaveChat(@Path("apiToken") final String apiToken, @Field("chat_id") final String chatId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/leaveChat")
    Call<ApiResponse<Boolean>> leaveChat(@Path("apiToken") final String apiToken, @Field("chat_id") final long chatId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/getChat")
    Call<ApiResponse<Chat>> getChat(@Path("apiToken") final String apiToken, @Field("chat_id") final String chatId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/getChat")
    Call<ApiResponse<Chat>> getChat(@Path("apiToken") final String apiToken, @Field("chat_id") final long chatId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/getChatAdministrators")
    Call<ApiResponse<List<ChatMember>>> getChatAdministrators(@Path("apiToken") final String apiToken, @Field("chat_id") final String chatId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/getChatAdministrators")
    Call<ApiResponse<List<ChatMember>>> getChatAdministrators(@Path("apiToken") final String apiToken, @Field("chat_id") final long chatId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/getChatMembersCount")
    Call<ApiResponse<Integer>> getChatMembersCount(@Path("apiToken") final String apiToken, @Field("chat_id") final String chatId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/getChatMembersCount")
    Call<ApiResponse<Integer>> getChatMembersCount(@Path("apiToken") final String apiToken, @Field("chat_id") final long chatId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/getChatMember")
    Call<ApiResponse<ChatMember>> getChatMember(@Path("apiToken") final String apiToken, @Field("chat_id") final String chatId, @Field("user_id") final long userId);

    @FormUrlEncoded
    @POST("/bot{apiToken}/getChatMember")
    Call<ApiResponse<ChatMember>> getChatMember(@Path("apiToken") final String apiToken, @Field("chat_id") final long chatId, @Field("user_id") final long userId);

    @POST("/bot{apiToken}/answerCallbackQuery")
    Call<ApiResponse<Boolean>> answerCallbackQuery(@Path("apiToken") final String apiToken, @Body final AnswerCallbackQueryPayload payload);

    @POST("/bot{apiToken}/sendGame")
    Call<ApiResponse<Message>> sendGame(@Path("apiToken") final String apiToken, @Body final SendGamePayload payload);

    @POST("/bot{apiToken}/setGameScore")
    Call<ApiResponse<Message>> setGameScoreByBot(@Path("apiToken") final String apiToken, @Body final SetGameScorePayload payload);

    @POST("/bot{apiToken}/setGameScore")
    Call<ApiResponse<Boolean>> setGameScore(@Path("apiToken") final String apiToken, @Body final SetGameScorePayload payload);

    @POST("/bot{apiToken}/getGameHighScores")
    Call<ApiResponse<List<GameHighScore>>> getGameHighScores(@Path("apiToken") final String apiToken, @Body final GetGameScorePayload payload);
}
