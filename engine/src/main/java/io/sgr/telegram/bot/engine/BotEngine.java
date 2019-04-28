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
package io.sgr.telegram.bot.engine;

import static io.sgr.telegram.bot.api.utils.Preconditions.isEmptyString;
import static io.sgr.telegram.bot.api.utils.Preconditions.notNull;

import io.sgr.telegram.bot.api.BotApi;
import io.sgr.telegram.bot.api.BotApiBuilder;
import io.sgr.telegram.bot.api.models.Update;
import io.sgr.telegram.bot.api.models.WebhookInfo;
import io.sgr.telegram.bot.api.models.http.GetUpdatesPayload;
import io.sgr.telegram.bot.api.utils.JsonUtil;
import io.sgr.telegram.bot.engine.processors.NoOpBotUpdateProcessor;
import io.sgr.telegram.bot.engine.utils.BackOff;
import io.sgr.telegram.bot.engine.utils.ExponentialBackOff;
import io.sgr.telegram.bot.engine.utils.SteadyBackOff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author SgrAlpha
 */
public class BotEngine implements Runnable {

    public static final Integer DEFAULT_GET_UPDATES_LIMIT = null;
    public static final int DEFAULT_GET_UPDATES_TIMEOUT_IN_SEC = 60;
    public static final String[] DEFAULT_ALLOWED_UPDATE_TYPE = null;
    public static final BackOff DEFAULT_REQUEST_TIMEOUT_BACK_OFF = ExponentialBackOff.getDefault();
    public static final BackOff DEFAULT_NO_UPDATE_BACK_OFF = SteadyBackOff.getDefault();
    public static final NoOpBotUpdateProcessor DEFAULT_BOT_UPDATE_PROCESSOR = NoOpBotUpdateProcessor.getDefault();

    private static final Logger LOGGER = LoggerFactory.getLogger(BotEngine.class);

    private final BotApi botApi;

    private Integer limit;
    private Integer timeout;
    private String[] allowedUpdates;

    private BotUpdateProcessor botUpdateProcessor;

    private BackOff requestTimeoutBackOff;
    private BackOff noUpdateBackOff;

    private Long offset = null;
    private volatile boolean stopped = false;

    /**
     * @param botApiToken        The token of Telegram bot API.
     * @param botUpdateProcessor Handler of update.
     */
    public BotEngine(final String botApiToken, final BotUpdateProcessor botUpdateProcessor) {
        this(new BotApiBuilder(botApiToken).setLogger(LOGGER).build(), botUpdateProcessor);
    }

    /**
     * @param botApi             Telegram bot API client.
     * @param botUpdateProcessor Handler of update.
     */
    public BotEngine(final BotApi botApi, final BotUpdateProcessor botUpdateProcessor) {
        notNull(botApi, "Telegram bot API should be specified");
        this.botApi = botApi;
        this.setGetUpdatesLimit(DEFAULT_GET_UPDATES_LIMIT);
        this.setGetUpdatesTimeoutInSec(DEFAULT_GET_UPDATES_TIMEOUT_IN_SEC);
        this.setAllowedUpdateTypes(DEFAULT_ALLOWED_UPDATE_TYPE);
        this.setRequestTimeoutBackOff(DEFAULT_REQUEST_TIMEOUT_BACK_OFF);
        this.setNoUpdateBackOff(DEFAULT_NO_UPDATE_BACK_OFF);
        this.setBotUpdateProcessor(botUpdateProcessor);
    }

    @Override public void run() {
        start();
    }

    /**
     * Start bot engine.
     */
    public void start() {
        this.setStopped(false); // This allows you to start / stop one bot engine multiple times.
        WebhookInfo hookInfo = null;
        try {
            hookInfo = botApi.getWebhookInfo().get(1, TimeUnit.MINUTES);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        if (hookInfo == null) {
            LOGGER.error("Unable to verify the given API token, please check your API token or network connection and try again.");
            this.setStopped(true);
            return;
        }
        LOGGER.info("The given API token has been verified successfully.");
        if (!isEmptyString(hookInfo.getUrl())) {
            LOGGER.error(String.format("Conflict detected! Webhook has been enabled and set to '%s', remove it before start engine! Details: %s", hookInfo.getUrl(), hookInfo));
            this.setStopped(true);
            return;
        }
        LOGGER.info("Bot engine started.");
        while (!this.needToStop()) {
            final GetUpdatesPayload payload = new GetUpdatesPayload(this.offset, this.limit, this.timeout, this.allowedUpdates);
            List<Update> received;
            try {
                received = this.botApi.getUpdates(payload).get(30, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                if (this.needToStop()) {
                    break;
                }
                long wait = requestTimeoutBackOff.getNextBackOffInMilli();
                LOGGER.warn(String.format("Interrupted when getting updates, wait for %d milliseconds to retry.", wait));
                try {
                    TimeUnit.MILLISECONDS.sleep(wait);
                } catch (InterruptedException e1) {
                    LOGGER.debug("Interrupted when waiting to retry a interrupted / failed get update request.");
                }
                continue;
            } catch (ExecutionException e) {
                if (this.needToStop()) {
                    break;
                }
                long wait = requestTimeoutBackOff.getNextBackOffInMilli();
                LOGGER.error(String.format("Hit '%s' when getting updates, wait for %d milliseconds to retry.", e.getMessage(), wait), e);
                try {
                    TimeUnit.MILLISECONDS.sleep(wait);
                } catch (InterruptedException e1) {
                    LOGGER.debug("Interrupted when waiting to retry a interrupted / failed get update request.");
                }
                continue;
            } catch (TimeoutException e) {
                if (this.needToStop()) {
                    break;
                }
                long wait = requestTimeoutBackOff.getNextBackOffInMilli();
                LOGGER.warn(String.format("Timeout when getting updates from Telegram server, wait for %d milliseconds.", wait));
                try {
                    TimeUnit.MILLISECONDS.sleep(wait);
                } catch (InterruptedException e1) {
                    LOGGER.debug("Interrupted when waiting for Telegram server to get updates.");
                }
                continue;
            }
            if (received.isEmpty()) {
                if (this.needToStop()) {
                    break;
                }
                long wait = noUpdateBackOff.getNextBackOffInMilli();
                LOGGER.debug(String.format("No new update available, wait for %d milliseconds.", wait));
                try {
                    TimeUnit.MILLISECONDS.sleep(wait);
                } catch (InterruptedException e) {
                    LOGGER.debug("Interrupted when waiting to get updates.");
                }
                continue;
            }
            noUpdateBackOff.reset();
            LOGGER.debug(String.format("Received %d new update(s)", received.size()));
            for (final Update update : received) {
                if (update == null) {
                    continue;
                }
                if (this.botUpdateProcessor.handleUpdate(update)) {
                    offset = update.getId() + 1;
                    continue;
                }
                offset = update.getId();
                LOGGER.error(String.format("Failed to handle update: %s", JsonUtil.toJson(update)));
                break;
            }
        }
        LOGGER.info("Bot engine stopped.");
    }

    private boolean needToStop() {
        return this.isStopped() || Thread.currentThread().isInterrupted();
    }

    /**
     * Stop bot engine.
     */
    public void stop() {
        this.setStopped(true);
    }

    private boolean isStopped() {
        return stopped;
    }

    private void setStopped(final boolean stopped) {
        this.stopped = stopped;
    }

    /**
     * @param limit Optional. Limits the number of updates to be retrieved. Values between 1—100 are accepted. NULL or
     *              not in range will use Telegram's defaults, which is 100.
     *
     * @return The bot engine.
     */
    public BotEngine setGetUpdatesLimit(final Integer limit) {
        this.limit = limit == null || limit <= 0 || limit > 100 ? null : limit;
        return this;
    }

    /**
     * @param timeout Optional. Timeout in seconds for long polling, should be greater than 0. Set to negative value
     *                will use {@link #DEFAULT_GET_UPDATES_TIMEOUT_IN_SEC}
     *
     * @return The bot engine.
     */
    public BotEngine setGetUpdatesTimeoutInSec(final int timeout) {
        this.timeout = timeout <= 0 ? DEFAULT_GET_UPDATES_TIMEOUT_IN_SEC : timeout;
        return this;
    }

    /**
     * @param allowedUpdates Optional. List the types of updates you want your bot to receive. Set to NULL will use
     *                       Telegram's default, which will accept all types.
     *
     * @return The bot engine.
     */
    public BotEngine setAllowedUpdateTypes(final String... allowedUpdates) {
        this.allowedUpdates = allowedUpdates;
        return this;
    }

    /**
     * @param requestTimeoutBackOff Optional. Default to {@link #DEFAULT_REQUEST_TIMEOUT_BACK_OFF}
     *
     * @return The bot engine.
     */
    public BotEngine setRequestTimeoutBackOff(final BackOff requestTimeoutBackOff) {
        this.requestTimeoutBackOff = requestTimeoutBackOff == null ? DEFAULT_REQUEST_TIMEOUT_BACK_OFF : requestTimeoutBackOff;
        return this;
    }

    /**
     * @param noUpdateBackOff Optional. Default to {@link #DEFAULT_NO_UPDATE_BACK_OFF}
     *
     * @return The bot engine.
     */
    public BotEngine setNoUpdateBackOff(final BackOff noUpdateBackOff) {
        this.noUpdateBackOff = noUpdateBackOff == null ? DEFAULT_NO_UPDATE_BACK_OFF : noUpdateBackOff;
        return this;
    }

    /**
     * @param botUpdateProcessor Optional. Set to NULL will use {@link #DEFAULT_BOT_UPDATE_PROCESSOR}
     *
     * @return The bot engine.
     */
    public BotEngine setBotUpdateProcessor(final BotUpdateProcessor botUpdateProcessor) {
        this.botUpdateProcessor = botUpdateProcessor == null ? DEFAULT_BOT_UPDATE_PROCESSOR : botUpdateProcessor;
        return this;
    }

}
