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
package io.sgr.telegram.bot.api.models.sticker;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.sgr.telegram.bot.api.models.PointType;
import io.sgr.telegram.bot.api.utils.JsonUtil;

/**
 * This object describes the position on faces where a mask should be placed by default.
 *
 * @author SgrAlpha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MaskPosition {

    private final String point;
    private final float xShift;
    private final float yShift;
    private final float zoom;

    /**
     * @param point  The part of the face relative to which the mask should be placed. One of 'forehead”, 'eyes”,
     *               'mouth”, or 'chin”.
     * @param xShift Shift by X-axis measured in widths of the mask scaled to the face size, from left to right. For
     *               example, choosing -1.0 will place mask just to the left of the default mask position.
     * @param yShift Shift by Y-axis measured in heights of the mask scaled to the face size, from top to bottom. For
     *               example, 1.0 will place the mask just below the default mask position.
     * @param zoom   Mask scaling coefficient. For example, 2.0 means double size.
     */
    @JsonCreator
    public MaskPosition(
            @JsonProperty("point") String point,
            @JsonProperty("x_shift") float xShift,
            @JsonProperty("y_shift") float yShift,
            @JsonProperty("zoom") float zoom) {
        try {
            PointType type = PointType.valueOf(point);
            this.point = type.name().toLowerCase();
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("Point should be one of 'forehead”, 'eyes”, 'mouth”, or 'chin”, but got: %s", point));
        }
        this.xShift = xShift;
        this.yShift = yShift;
        if (zoom <= 0) {
            throw new IllegalArgumentException("Zoom should be greater than zero");
        }
        this.zoom = zoom;
    }

    @JsonProperty("point")
    public String getPoint() {
        return point;
    }

    @JsonProperty("x_shift")
    public float getxShift() {
        return xShift;
    }

    @JsonProperty("y_shift")
    public float getyShift() {
        return yShift;
    }

    @JsonProperty("zoom")
    public float getZoom() {
        return zoom;
    }

    public String toJSON() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJSON();
    }

}
