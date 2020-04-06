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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

/**
 * Contains information about the current status of a webhook.
 *
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WebhookInfo {

    private final String url;
    private final boolean hasCustomCertificate;
    private final long pendingUpdateCount;
    private final Long lastErrorDate;
    private final String lastErrorMessage;
    private final Integer maxConnections;
    private final List<String> allowedUpdates;

    /**
     * @param url                  Webhook URL, may be empty if webhook is not set up.
     * @param hasCustomCertificate True, if a custom certificate was provided for webhook certificate checks.
     * @param pendingUpdateCount   Number of updates awaiting delivery.
     * @param lastErrorDate        Optional. Unix time for the most recent error that happened when trying to deliver an
     *                             update via webhook.
     * @param lastErrorMessage     Optional. Error message in human-readable format for the most recent error that
     *                             happened when trying to deliver an update via webhook.
     * @param maxConnections       Optional. Maximum allowed number of simultaneous HTTPS connections to the webhook for
     *                             update delivery.
     * @param allowedUpdates       Optional. A list of update types the bot is subscribed to. Defaults to all update
     *                             types.
     */
    public WebhookInfo(
            @JsonProperty("url") final String url,
            @JsonProperty("has_custom_certificate") final boolean hasCustomCertificate,
            @JsonProperty("pending_update_count") final long pendingUpdateCount,
            @JsonProperty("last_error_date") final Long lastErrorDate,
            @JsonProperty("last_error_message") final String lastErrorMessage,
            @JsonProperty("max_connections") final Integer maxConnections,
            @JsonProperty("allowed_updates") final List<String> allowedUpdates) {
        this.url = url;
        this.hasCustomCertificate = hasCustomCertificate;
        this.pendingUpdateCount = pendingUpdateCount;
        this.lastErrorDate = lastErrorDate;
        this.lastErrorMessage = lastErrorMessage;
        this.maxConnections = maxConnections;
        this.allowedUpdates = allowedUpdates;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("has_custom_certificate")
    public boolean hasCustomCertificate() {
        return hasCustomCertificate;
    }

    @JsonProperty("pending_update_count")
    public long getPendingUpdateCount() {
        return pendingUpdateCount;
    }

    @JsonProperty("last_error_date")
    public Long getLastErrorDate() {
        return lastErrorDate;
    }

    @JsonProperty("last_error_message")
    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    @JsonProperty("max_connections")
    public Integer getMaxConnections() {
        return maxConnections;
    }

    @JsonProperty("allowed_updates")
    public List<String> getAllowedUpdates() {
        return allowedUpdates == null ? null : Collections.unmodifiableList(allowedUpdates);
    }
}
