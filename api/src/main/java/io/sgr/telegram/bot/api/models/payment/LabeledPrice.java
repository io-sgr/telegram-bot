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

import static io.sgr.telegram.bot.api.utils.Preconditions.notEmptyString;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.sgr.telegram.bot.api.utils.JsonUtil;

/**
 * This object represents a portion of the price for goods or services.
 *
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LabeledPrice {

    private final String label;
    private final int amount;

    /**
     * @param label  Portion label.
     * @param amount Price of the product in the smallest units of the currency (integer, not float/double). For
     *               example, for a price of US$ 1.45 pass amount = 145. See the exp parameter in currencies.json, it
     *               shows the number of digits past the decimal point for each currency (2 for the majority of
     *               currencies).
     */
    @JsonCreator
    public LabeledPrice(
            @JsonProperty("label") final String label,
            @JsonProperty("amount") final int amount) {
        notEmptyString(label, "Missing label.");
        this.label = label;
        if (amount < 0) {
            throw new IllegalArgumentException(String.format("Amount should be greater than or equal to zero, but got: %d", amount));
        }
        this.amount = amount;
    }

    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    @JsonProperty("amount")
    public int getAmount() {
        return amount;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJson();
    }

}
