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
import io.sgr.telegram.bot.api.models.payment.ShippingAddress;

/**
 * This object contains information about an incoming shipping query.
 *
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShippingQueryPayload {

    @JsonProperty("id")
    public final String id;
    @JsonProperty("from")
    public final User from;
    @JsonProperty("invoice_payload")
    public final String invoicePayload;
    @JsonProperty("shipping_address")
    public final ShippingAddress shippingAddress;

    /**
     * @param id              Unique query identifier.
     * @param from            User who sent the query.
     * @param invoicePayload  Bot specified invoice payload.
     * @param shippingAddress User specified shipping address.
     */
    @JsonCreator
    public ShippingQueryPayload(
            @JsonProperty("id") final String id,
            @JsonProperty("from") final User from,
            @JsonProperty("invoice_payload") final String invoicePayload,
            @JsonProperty("shipping_address") final ShippingAddress shippingAddress) {
        this.id = id;
        this.from = from;
        this.invoicePayload = invoicePayload;
        this.shippingAddress = shippingAddress;
    }
}
