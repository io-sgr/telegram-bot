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

package io.sgr.telegram.bot.api.models;

import static io.sgr.telegram.bot.api.utils.Preconditions.notEmptyString;
import static io.sgr.telegram.bot.api.utils.Preconditions.notNull;

import io.sgr.telegram.bot.api.utils.JsonUtil;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Venue {

    private final Location location;
    private final String title;
    private final String address;
    private final String foursquareId;

    /**
     * @param location     Venue location.
     * @param title        Name of the venue.
     * @param address      Address of the venue.
     * @param foursquareId Optional. Foursquare identifier of the venue.
     */
    @JsonCreator
    public Venue(
            @JsonProperty("location") Location location,
            @JsonProperty("title") String title,
            @JsonProperty("address") String address,
            @JsonProperty("foursquare_id") String foursquareId) {
        notNull(location, "Location should be provided");
        this.location = location;
        notEmptyString(title, "Title should be provided");
        this.title = title;
        this.address = address;
        this.foursquareId = foursquareId;
    }

    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("foursquare_id")
    public String getFoursquareId() {
        return foursquareId;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJson();
    }

}
