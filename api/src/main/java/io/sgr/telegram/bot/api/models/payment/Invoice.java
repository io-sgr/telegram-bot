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

package io.sgr.telegram.bot.api.models.payment;

import static io.sgr.telegram.bot.api.utils.Preconditions.notEmptyString;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.sgr.telegram.bot.api.utils.JsonUtil;

/**
 * This object contains basic information about an invoice.
 *
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Invoice {

    private final String title;
    private final String description;
    private final String startParameter;
    private final String currency;
    private final int totalAmount;

    /**
     * @param title          Product name.
     * @param description    Product description.
     * @param startParameter Unique bot deep-linking parameter that can be used to generate this invoice.
     * @param currency       Three-letter ISO 4217 currency code.
     * @param totalAmount    Total price in the smallest units of the currency (integer, not float/double). For example,
     *                       for a price of US$ 1.45 pass amount = 145. See the exp parameter in currencies.json, it
     *                       shows the number of digits past the decimal point for each currency (2 for the majority of
     *                       currencies).
     */
    @JsonCreator
    public Invoice(
            @JsonProperty("title") final String title,
            @JsonProperty("description") final String description,
            @JsonProperty("start_parameter") final String startParameter,
            @JsonProperty("currency") final String currency,
            @JsonProperty("total_amount") final int totalAmount) {
        notEmptyString(title, "Missing product name.");
        this.title = title;
        this.description = description;
        this.startParameter = startParameter;
        notEmptyString(currency, "Missing currency.");
        this.currency = currency;
        if (totalAmount < 0) {
            throw new IllegalArgumentException(String.format("Total amount should be greater than or equal to zero, but got: %d", totalAmount));
        }
        this.totalAmount = totalAmount;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("start_parameter")
    public String getStartParameter() {
        return startParameter;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("total_amount")
    public int getTotalAmount() {
        return totalAmount;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJson();
    }

}
