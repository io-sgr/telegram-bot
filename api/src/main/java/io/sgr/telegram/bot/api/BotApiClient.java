/*
 * Copyright 2017-2018 SgrAlpha
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

import static io.sgr.telegram.bot.api.utils.Preconditions.notNull;

import io.sgr.telegram.bot.api.exceptions.ApiCallException;
import io.sgr.telegram.bot.api.models.Chat;
import io.sgr.telegram.bot.api.models.ChatMember;
import io.sgr.telegram.bot.api.models.Message;
import io.sgr.telegram.bot.api.models.Update;
import io.sgr.telegram.bot.api.models.User;
import io.sgr.telegram.bot.api.models.WebhookInfo;
import io.sgr.telegram.bot.api.models.http.AnswerCallbackQueryPayload;
import io.sgr.telegram.bot.api.models.http.AnswerInlineQueryPayload;
import io.sgr.telegram.bot.api.models.http.ApiErrorResponse;
import io.sgr.telegram.bot.api.models.http.ApiResponse;
import io.sgr.telegram.bot.api.models.http.DeleteMessagePayload;
import io.sgr.telegram.bot.api.models.http.EditMessageCaptionPayload;
import io.sgr.telegram.bot.api.models.http.EditMessageReplyMarkupPayload;
import io.sgr.telegram.bot.api.models.http.EditMessageTextPayload;
import io.sgr.telegram.bot.api.models.http.ForwardMessagePayload;
import io.sgr.telegram.bot.api.models.http.GetUpdatesPayload;
import io.sgr.telegram.bot.api.models.http.SendMessagePayload;
import io.sgr.telegram.bot.api.utils.JsonUtil;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author SgrAlpha
 */
public class BotApiClient implements Closeable {

    private static final Logger LOGGER = LoggerFactory.getLogger(BotApiClient.class.getName());

    private final OkHttpClient okHttpClient;
    private final Retrofit retrofit;
    private final BotApi botApi;
    private final boolean skipRetry;

    BotApiClient(final OkHttpClient okHttpClient, final Retrofit retrofit, final boolean skipRetry) {
        this.okHttpClient = okHttpClient;
        this.retrofit = retrofit;
        this.botApi = retrofit.create(BotApi.class);
        this.skipRetry = skipRetry;
    }

    public CompletableFuture<User> getMe(final String botApiToken) {
        final CompletableFuture<User> fut = new CompletableFuture<>();
        this.botApi.getMe(botApiToken).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<WebhookInfo> getWebhookInfo(final String botApiToken) {
        final CompletableFuture<WebhookInfo> fut = new CompletableFuture<>();
        this.botApi.getWebhookInfo(botApiToken).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<List<Update>> getUpdates(final String botApiToken, final GetUpdatesPayload payload) {
        CompletableFuture<List<Update>> fut = new CompletableFuture<>();
        this.botApi.getUpdates(botApiToken, payload).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<Message> sendMessage(final String botApiToken, final SendMessagePayload payload) {
        CompletableFuture<Message> fut = new CompletableFuture<>();
        this.botApi.sendMessage(botApiToken, payload).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<Message> forwardMessage(final String botApiToken, final ForwardMessagePayload payload) {
        CompletableFuture<Message> fut = new CompletableFuture<>();
        this.botApi.forwardMessage(botApiToken, payload).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<Message> editMessageText(final String botApiToken, final EditMessageTextPayload payload) {
        CompletableFuture<Message> fut = new CompletableFuture<>();
        this.botApi.editMessageText(botApiToken, payload).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<Message> editMessageCaption(final String botApiToken, final EditMessageCaptionPayload payload) {
        CompletableFuture<Message> fut = new CompletableFuture<>();
        this.botApi.editMessageCaption(botApiToken, payload).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<Message> editMessageReplyMarkup(final String botApiToken, final EditMessageReplyMarkupPayload payload) {
        CompletableFuture<Message> fut = new CompletableFuture<>();
        this.botApi.editMessageReplyMarkup(botApiToken, payload).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<Boolean> deleteMessage(final String botApiToken, final DeleteMessagePayload payload) {
        CompletableFuture<Boolean> fut = new CompletableFuture<>();
        this.botApi.deleteMessage(botApiToken, payload).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<Boolean> answerInlineQuery(final String botApiToken, final AnswerInlineQueryPayload payload) {
        CompletableFuture<Boolean> fut = new CompletableFuture<>();
        this.botApi.answerInlineQuery(botApiToken, payload).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<Boolean> kickChatMember(final String botApiToken, final String chatId, final long userId) {
        CompletableFuture<Boolean> fut = new CompletableFuture<>();
        this.botApi.kickChatMember(botApiToken, chatId, userId).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<Boolean> kickChatMember(final String botApiToken, final long chatId, final long userId) {
        CompletableFuture<Boolean> fut = new CompletableFuture<>();
        this.botApi.kickChatMember(botApiToken, chatId, userId).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<Boolean> unbanChatMember(final String botApiToken, final String chatId, final long userId) {
        CompletableFuture<Boolean> fut = new CompletableFuture<>();
        this.botApi.unbanChatMember(botApiToken, chatId, userId).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<Boolean> unbanChatMember(final String botApiToken, final long chatId, final long userId) {
        CompletableFuture<Boolean> fut = new CompletableFuture<>();
        this.botApi.unbanChatMember(botApiToken, chatId, userId).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<Boolean> restrictChatMember(final String apiToken, final String chatId, final long userId, final Long untilDate, final Boolean canSendMessage, final Boolean canSendMediaMessage, final Boolean canSendOtherMessage, final Boolean canAddWebPagePreviews) {
        CompletableFuture<Boolean> fut = new CompletableFuture<>();
        this.botApi.restrictChatMember(apiToken, chatId, userId, untilDate, canSendMessage, canSendMediaMessage, canSendOtherMessage, canAddWebPagePreviews).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<Boolean> restrictChatMember(final String apiToken, final long chatId, final long userId, final Long untilDate, final Boolean canSendMessage, final Boolean canSendMediaMessage, final Boolean canSendOtherMessage, final Boolean canAddWebPagePreviews) {
        CompletableFuture<Boolean> fut = new CompletableFuture<>();
        this.botApi.restrictChatMember(apiToken, chatId, userId, untilDate, canSendMessage, canSendMediaMessage, canSendOtherMessage, canAddWebPagePreviews).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<Boolean> promoteChatMember(final String apiToken, final String chatId, final long userId, final Boolean canChangeInfo, final Boolean canPostMessage, final Boolean canEditMessage, final Boolean canDeleteMessage, final Boolean canInviteUsers, final Boolean canRestrictMembers, final Boolean canPinMessage, final Boolean canPromoteMembers) {
        CompletableFuture<Boolean> fut = new CompletableFuture<>();
        this.botApi.promoteChatMember(apiToken, chatId, userId, canChangeInfo, canPostMessage, canEditMessage, canDeleteMessage, canInviteUsers, canRestrictMembers, canPinMessage, canPromoteMembers).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<Boolean> promoteChatMember(final String apiToken, final long chatId, final long userId, final Boolean canChangeInfo, final Boolean canPostMessage, final Boolean canEditMessage, final Boolean canDeleteMessage, final Boolean canInviteUsers, final Boolean canRestrictMembers, final Boolean canPinMessage, final Boolean canPromoteMembers) {
        CompletableFuture<Boolean> fut = new CompletableFuture<>();
        this.botApi.promoteChatMember(apiToken, chatId, userId, canChangeInfo, canPostMessage, canEditMessage, canDeleteMessage, canInviteUsers, canRestrictMembers, canPinMessage, canPromoteMembers).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<String> exportChatInviteLink(final String apiToken, final String chatId) {
        CompletableFuture<String> fut = new CompletableFuture<>();
        this.botApi.exportChatInviteLink(apiToken, chatId).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<String> exportChatInviteLink(final String apiToken, final long chatId) {
        CompletableFuture<String> fut = new CompletableFuture<>();
        this.botApi.exportChatInviteLink(apiToken, chatId).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<Boolean> deleteChatPhoto(final String apiToken, final String chatId) {
        CompletableFuture<Boolean> fut = new CompletableFuture<>();
        this.botApi.deleteChatPhoto(apiToken, chatId).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<Boolean> deleteChatPhoto(final String apiToken, final long chatId) {
        CompletableFuture<Boolean> fut = new CompletableFuture<>();
        this.botApi.deleteChatPhoto(apiToken, chatId).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<Boolean> setChatTitle(final String apiToken, final String chatId, final String title) {
        CompletableFuture<Boolean> fut = new CompletableFuture<>();
        this.botApi.setChatTitle(apiToken, chatId, title).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<Boolean> setChatTitle(final String apiToken, final long chatId, final String title) {
        CompletableFuture<Boolean> fut = new CompletableFuture<>();
        this.botApi.setChatTitle(apiToken, chatId, title).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<Boolean> setChatDescription(final String apiToken, final String chatId, final String description) {
        CompletableFuture<Boolean> fut = new CompletableFuture<>();
        this.botApi.setChatDescription(apiToken, chatId, description).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<Boolean> setChatDescription(final String apiToken, final long chatId, final String description) {
        CompletableFuture<Boolean> fut = new CompletableFuture<>();
        this.botApi.setChatDescription(apiToken, chatId, description).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<Boolean> pinChatMessage(final String apiToken, final String chatId, final long messageId) {
        CompletableFuture<Boolean> fut = new CompletableFuture<>();
        this.botApi.pinChatMessage(apiToken, chatId, messageId).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<Boolean> pinChatMessage(final String apiToken, final long chatId, final long messageId) {
        CompletableFuture<Boolean> fut = new CompletableFuture<>();
        this.botApi.pinChatMessage(apiToken, chatId, messageId).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<Boolean> unpinChatMessage(final String apiToken, final String chatId) {
        CompletableFuture<Boolean> fut = new CompletableFuture<>();
        this.botApi.unpinChatMessage(apiToken, chatId).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<Boolean> unpinChatMessage(final String apiToken, final long chatId) {
        CompletableFuture<Boolean> fut = new CompletableFuture<>();
        this.botApi.unpinChatMessage(apiToken, chatId).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<Boolean> leaveChat(final String apiToken, final String chatId) {
        CompletableFuture<Boolean> fut = new CompletableFuture<>();
        this.botApi.leaveChat(apiToken, chatId).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<Boolean> leaveChat(final String apiToken, final long chatId) {
        CompletableFuture<Boolean> fut = new CompletableFuture<>();
        this.botApi.leaveChat(apiToken, chatId).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<Chat> getChat(final String botApiToken, final String chatId) {
        CompletableFuture<Chat> fut = new CompletableFuture<>();
        this.botApi.getChat(botApiToken, chatId).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<Chat> getChat(final String botApiToken, final long chatId) {
        CompletableFuture<Chat> fut = new CompletableFuture<>();
        this.botApi.getChat(botApiToken, chatId).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<List<ChatMember>> getChatAdministrators(final String botApiToken, final String chatId) {
        CompletableFuture<List<ChatMember>> fut = new CompletableFuture<>();
        this.botApi.getChatAdministrators(botApiToken, chatId).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<List<ChatMember>> getChatAdministrators(final String botApiToken, final long chatId) {
        CompletableFuture<List<ChatMember>> fut = new CompletableFuture<>();
        this.botApi.getChatAdministrators(botApiToken, chatId).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<Integer> getChatMembersCount(final String botApiToken, final String chatId) {
        CompletableFuture<Integer> fut = new CompletableFuture<>();
        this.botApi.getChatMembersCount(botApiToken, chatId).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<Integer> getChatMembersCount(final String botApiToken, final long chatId) {
        CompletableFuture<Integer> fut = new CompletableFuture<>();
        this.botApi.getChatMembersCount(botApiToken, chatId).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<ChatMember> getChatMember(final String botApiToken, final String chatId, final long userId) {
        CompletableFuture<ChatMember> fut = new CompletableFuture<>();
        this.botApi.getChatMember(botApiToken, chatId, userId).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<ChatMember> getChatMember(final String botApiToken, final long chatId, final long userId) {
        CompletableFuture<ChatMember> fut = new CompletableFuture<>();
        this.botApi.getChatMember(botApiToken, chatId, userId).enqueue(this.prepareCallback(fut));
        return fut;
    }

    public CompletableFuture<Boolean> answerCallbackQuery(final String apiToken, final AnswerCallbackQueryPayload payload) {
        CompletableFuture<Boolean> fut = new CompletableFuture<>();
        this.botApi.answerCallbackQuery(apiToken, payload).enqueue(this.prepareCallback(fut));
        return fut;
    }

    private <T> Callback<ApiResponse<T>> prepareCallback(final CompletableFuture<T> fut) {
        notNull(fut, "Missing CompletableFuture");
        return new Callback<ApiResponse<T>>() {
            @Override
            public void onResponse(final Call<ApiResponse<T>> call, final Response<ApiResponse<T>> response) {
                if (response.isSuccessful()) {
                    final ApiResponse<T> body = response.body();
                    fut.complete(body == null ? null : body.getResult());
                    return;
                }
                final ResponseBody errorBody = response.errorBody();
                ApiErrorResponse err = null;
                if (errorBody != null) {
                    try {
                        err = JsonUtil.getObjectMapper().readValue(errorBody.bytes(), ApiErrorResponse.class);
                    } catch (IOException e) {
                        getLogger().error("{}: {}", e.getClass().getSimpleName(), e.getMessage());
                    }
                }
                final String errDesc = err == null ? "NA" : err.getDescription().orElse("NA");
                if (response.code() >= 400 && response.code() < 500) {
                    if (response.code() == 409) {
                        fut.completeExceptionally(new ApiCallException(err));
                        return;
                    }
                    if (response.code() == 429) {
                        int index = errDesc.lastIndexOf(" ");
                        int time;
                        try {
                            time = Integer.parseInt(errDesc.substring(index));
                        } catch (Exception e) {
                            time = 3;
                        }
                        try {
                            TimeUnit.SECONDS.sleep(time);
                        } catch (InterruptedException e) {
                            // Ignore
                        }
                        call.clone().enqueue(this);
                        return;
                    }
                    getLogger().error("Error '{}:{}' received.", response.code(), errDesc);
                    fut.completeExceptionally(new ApiCallException(err));
                    return;
                }
                if (skipRetry()) {
                    getLogger().error("Error '{}:{}' received.", response.code(), errDesc);
                    fut.completeExceptionally(new ApiCallException(err));
                    return;
                }
                getLogger().error("Error '{}:{}' received, retrying ...", response.code(), errDesc);
                call.clone().enqueue(this);
            }

            @Override
            public void onFailure(final Call<ApiResponse<T>> call, final Throwable t) {
                if (t == null) {
                    getLogger().error("Ran into failure when calling API, but no exception has been caught.");
                    call.clone().enqueue(this);
                    return;
                }
                getLogger().error("{} '{}' received when sending message.", t.getClass().getSimpleName(), t.getMessage());
                getOkHttpClient().connectionPool().evictAll();
                // Ignore client read timeout
                if ("timeout".equalsIgnoreCase(t.getMessage())) {
                    fut.completeExceptionally(t);
                    return;
                }
                if (skipRetry()) {
                    fut.completeExceptionally(t);
                    return;
                }
                call.clone().enqueue(this);
            }
        };
    }

    private static Logger getLogger() {
        return LOGGER;
    }

    private boolean skipRetry() {
        return this.skipRetry;
    }

    /**
     * @return the okHttp client
     */
    public OkHttpClient getOkHttpClient() {
        return this.okHttpClient;
    }

    /**
     * @return the retrofit instance
     */
    public Retrofit getRetrofit() {
        return this.retrofit;
    }

    /**
     * @return a new client builder
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    @Override public void close() {
        getOkHttpClient().dispatcher().executorService().shutdown();
    }

    public static class Builder {

        private static final String BASE_URL = "https://api.telegram.org/";

        private OkHttpClient okHttpClient;
        private Boolean skipRetry;

        /**
         * @param okHttpClient the OkHttpClient to set
         * @return the builder
         */
        public Builder setOkHttpClient(final OkHttpClient okHttpClient) {
            this.okHttpClient = okHttpClient;
            return this;
        }

        /**
         * @param skipRetry the skipRetry to set
         * @return the builder
         */
        public Builder setSkipRetry(Boolean skipRetry) {
            this.skipRetry = skipRetry;
            return this;
        }

        /**
         * @return the bot API client
         */
        public BotApiClient build() {
            Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(JacksonConverterFactory.create(JsonUtil.getObjectMapper()));
            if (this.okHttpClient == null) {
                OkHttpClient.Builder clientBuilder = new OkHttpClient().newBuilder()
                        .retryOnConnectionFailure(true)
                        .connectTimeout(5, TimeUnit.MINUTES)
                        .readTimeout(5, TimeUnit.MINUTES)
                        .writeTimeout(5, TimeUnit.MINUTES);
                this.okHttpClient = clientBuilder.build();
            }
            retrofitBuilder.client(this.okHttpClient);
            return new BotApiClient(this.okHttpClient, retrofitBuilder.build(), this.skipRetry != null && this.skipRetry);
        }

    }

}
