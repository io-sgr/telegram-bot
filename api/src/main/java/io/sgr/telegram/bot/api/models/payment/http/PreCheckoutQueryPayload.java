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

package io.sgr.telegram.bot.api.models.payment.http;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.sgr.telegram.bot.api.models.User;
import io.sgr.telegram.bot.api.models.payment.OrderInfo;

/**
 * This object contains information about an incoming pre-checkout query.
 *
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PreCheckoutQueryPayload {

    @JsonProperty("id")
    public final String id;
    @JsonProperty("from")
    public final User from;
    @JsonProperty("currency")
    public final String currency;
    @JsonProperty("total_amount")
    public final int totalAmount;
    @JsonProperty("invoice_payload")
    public final String invoicePayload;
    @JsonProperty("shipping_option_id")
    public final String shippingOptionId;
    @JsonProperty("order_info")
    public final OrderInfo orderInfo;

    /**
     * @param id               Unique query identifier.
     * @param from             User who sent the query.
     * @param currency         Three-letter ISO 4217 currency code.
     * @param totalAmount      Total price in the smallest units of the currency (integer, not float/double). For
     *                         example, for a price of US$ 1.45 pass amount = 145. See the exp parameter in
     *                         currencies.json, it shows the number of digits past the decimal point for each currency
     *                         (2 for the majority of currencies).
     * @param invoicePayload   Bot specified invoice payload.
     * @param shippingOptionId Optional. Identifier of the shipping option chosen by the user.
     * @param orderInfo        Optional. Order info provided by the user.
     */
    @JsonCreator
    public PreCheckoutQueryPayload(
            @JsonProperty("id") final String id,
            @JsonProperty("from") final User from,
            @JsonProperty("currency") final String currency,
            @JsonProperty("total_amount") final int totalAmount,
            @JsonProperty("invoice_payload") final String invoicePayload,
            @JsonProperty("shipping_option_id") final String shippingOptionId,
            @JsonProperty("order_info") final OrderInfo orderInfo) {
        this.id = id;
        this.from = from;
        this.currency = currency;
        this.totalAmount = totalAmount;
        this.invoicePayload = invoicePayload;
        this.shippingOptionId = shippingOptionId;
        this.orderInfo = orderInfo;
    }
}
