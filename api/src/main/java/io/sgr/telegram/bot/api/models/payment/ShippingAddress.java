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

/**
 * This object represents a shipping address.
 *
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShippingAddress {

    private final String countryCode;
    private final String state;
    private final String city;
    private final String streetLine1;
    private final String streetLine2;
    private final String postCode;

    /**
     * @param countryCode ISO 3166-1 alpha-2 country code.
     * @param state       State, if applicable.
     * @param city        City.
     * @param streetLine1 First line for the address.
     * @param streetLine2 Second line for the address.
     * @param postCode    Address post code.
     */
    @JsonCreator
    public ShippingAddress(
            @JsonProperty("country_code") final String countryCode,
            @JsonProperty("state") final String state,
            @JsonProperty("city") final String city,
            @JsonProperty("street_line1") final String streetLine1,
            @JsonProperty("street_line2") final String streetLine2,
            @JsonProperty("post_code") final String postCode) {
        this.countryCode = countryCode;
        this.state = state;
        this.city = city;
        this.streetLine1 = streetLine1;
        this.streetLine2 = streetLine2;
        this.postCode = postCode;
    }

    @JsonProperty("country_code")
    public String getCountryCode() {
        return countryCode;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("street_line1")
    public String getStreetLine1() {
        return streetLine1;
    }

    @JsonProperty("street_line2")
    public String getStreetLine2() {
        return streetLine2;
    }

    @JsonProperty("post_code")
    public String getPostCode() {
        return postCode;
    }

    public String toJSON() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJSON();
    }

}
