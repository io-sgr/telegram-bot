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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.sgr.telegram.bot.api.utils.JsonUtil;

/**
 * This object represents a point on the map.
 *
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {

    private final float latitude;
    private final float longitude;

    /**
     * @param latitude  Latitude as defined by sender
     * @param longitude Longitude as defined by sender
     */
    @JsonCreator
    public Location(@JsonProperty("latitude") float latitude, @JsonProperty("longitude") float longitude) {
        if (Math.abs(latitude) > 90) {
            throw new IllegalArgumentException(String.format("Invalid latitude %f", latitude));
        }
        this.latitude = latitude;
        if (Math.abs(longitude) > 180) {
            throw new IllegalArgumentException(String.format("Invalid longitude %f", longitude));
        }
        this.longitude = longitude;
    }

    @JsonProperty("latitude")
    public float getLatitude() {
        return latitude;
    }

    @JsonProperty("longitude")
    public float getLongitude() {
        return longitude;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJson();
    }

}
