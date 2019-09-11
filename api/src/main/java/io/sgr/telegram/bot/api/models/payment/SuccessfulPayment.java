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

package io.sgr.telegram.bot.api.models.payment;

import io.sgr.telegram.bot.api.utils.JsonUtil;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This object contains basic information about a successful payment.
 *
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessfulPayment {

    private final String currency;
    private final int totalAmount;
    private final String invoicePayload;
    private final String shippingOptionId;
    private final OrderInfo orderInfo;
    private final String telegramPaymentChargeId;
    private final String providerPaymentChargeId;

    /**
     * @param currency                Three-letter ISO 4217 currency code.
     * @param totalAmount             Total price in the smallest units of the currency (integer, not float/double). For
     *                                example, for a price of US$ 1.45 pass amount = 145. See the exp parameter in
     *                                currencies.json, it shows the number of digits past the decimal point for each
     *                                currency (2 for the majority of currencies).
     * @param invoicePayload          Bot specified invoice payload.
     * @param shippingOptionId        Optional. Identifier of the shipping option chosen by the user.
     * @param orderInfo               Optional. Order info provided by the user.
     * @param telegramPaymentChargeId Telegram payment identifier.
     * @param providerPaymentChargeId Provider payment identifier.
     */
    @JsonCreator
    public SuccessfulPayment(
            @JsonProperty("currency") final String currency,
            @JsonProperty("total_amount") final int totalAmount,
            @JsonProperty("invoice_payload") final String invoicePayload,
            @JsonProperty("shipping_option_id") final String shippingOptionId,
            @JsonProperty("order_info") final OrderInfo orderInfo,
            @JsonProperty("telegram_payment_charge_id") final String telegramPaymentChargeId,
            @JsonProperty("provider_payment_charge_id") final String providerPaymentChargeId) {
        this.currency = currency;
        this.totalAmount = totalAmount;
        this.invoicePayload = invoicePayload;
        this.shippingOptionId = shippingOptionId;
        this.orderInfo = orderInfo;
        this.telegramPaymentChargeId = telegramPaymentChargeId;
        this.providerPaymentChargeId = providerPaymentChargeId;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("total_amount")
    public int getTotalAmount() {
        return totalAmount;
    }

    @JsonProperty("invoice_payload")
    public String getInvoicePayload() {
        return invoicePayload;
    }

    @JsonProperty("shipping_option_id")
    public String getShippingOptionId() {
        return shippingOptionId;
    }

    @JsonProperty("order_info")
    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    @JsonProperty("telegram_payment_charge_id")
    public String getTelegramPaymentChargeId() {
        return telegramPaymentChargeId;
    }

    @JsonProperty("provider_payment_charge_id")
    public String getProviderPaymentChargeId() {
        return providerPaymentChargeId;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJson();
    }

}
