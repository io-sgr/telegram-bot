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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.sgr.telegram.bot.api.utils.JsonUtil;

import java.util.Collections;
import java.util.List;

/**
 * This object represents one shipping option.
 *
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShippingOption {

    private final String id;
    private final String title;
    private final List<LabeledPrice> prices;

    /**
     * @param id     Shipping option identifier.
     * @param title  Option title.
     * @param prices List of price portions.
     */
    @JsonCreator
    public ShippingOption(
            @JsonProperty("id") final String id,
            @JsonProperty("title") final String title,
            @JsonProperty("prices") final List<LabeledPrice> prices) {
        this.id = id;
        this.title = title;
        this.prices = prices;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("prices")
    public List<LabeledPrice> getPrices() {
        return prices == null ? null : Collections.unmodifiableList(prices);
    }

    public String toJSON() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJSON();
    }

}
