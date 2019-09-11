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

package io.sgr.telegram.bot.api.models.inline;

import io.sgr.telegram.bot.api.utils.JsonUtil;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the content of a venue message to be sent as the result of an inline query.
 *
 * @author SgrAlpha
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InputVenueMessageContent implements InputMessageContent {

    private final float latitude;
    private final float longitude;
    private final String title;
    private final String address;
    private final String foursquareId;
    private final String foursquareType;

    /**
     * @param latitude       Latitude of the location in degrees
     * @param longitude      Longitude of the location in degrees
     * @param title          Name of the venue
     * @param address        Address of the venue
     * @param foursquareId   Optional. Foursquare identifier of the venue, if known
     * @param foursquareType Optional. Foursquare type of the venue, if known. (For example,
     *                       “arts_entertainment/default”, “arts_entertainment/aquarium” or “food/icecream”.)
     */
    public InputVenueMessageContent(float latitude, float longitude, String title, String address, String foursquareId, final String foursquareType) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
        this.address = address;
        this.foursquareId = foursquareId;
        this.foursquareType = foursquareType;
    }

    @JsonProperty("latitude")
    public float getLatitude() {
        return latitude;
    }

    @JsonProperty("longitude")
    public float getLongitude() {
        return longitude;
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

    @JsonProperty("foursquare_type")
    public String getFoursquareType() {
        return foursquareType;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJson();
    }

}
