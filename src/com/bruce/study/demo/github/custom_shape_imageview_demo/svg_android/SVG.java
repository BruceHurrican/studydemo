/*
 * BruceHurrican
 * Copyright (c) 2016.
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 *    This document is Bruce's individual learning the android demo, wherein the use of the code from the Internet, only to use as a learning exchanges.
 *   And where any person can download and use, but not for commercial purposes.
 *   Author does not assume the resulting corresponding disputes.
 *   If you have good suggestions for the code, you can contact BurrceHurrican@foxmail.com
 *   本文件为Bruce's个人学习android的demo, 其中所用到的代码来源于互联网，仅作为学习交流使用。
 *   任和何人可以下载并使用, 但是不能用于商业用途。
 *   作者不承担由此带来的相应纠纷。
 *   如果对本代码有好的建议，可以联系BurrceHurrican@foxmail.com
 */

package com.bruce.study.demo.github.custom_shape_imageview_demo.svg_android;

import android.graphics.Picture;
import android.graphics.RectF;
import android.graphics.drawable.PictureDrawable;

/**
 * Describes a vector Picture object, and optionally its bounds.
 *
 * @author Larval Labs,LLC
 *         Created by BruceHurrican on 2015/8/22.
 */
public class SVG {
    /**
     * The parsed Picture object.
     */
    private Picture picture;
    /**
     * These are the bounds for the SVG specified as a hidden "bounds" layer in the SVG.
     */
    private RectF bounds;
    /**
     * These are the estimated bounds of the SVG computed from the SVG elements while parsing.
     * Note that this could be null if there was a failure to compute limits(ie.an empty SVG).
     */
    private RectF limits = null;

    /**
     * Construct a new SVG
     *
     * @param picture the parsed picture object
     * @param bounds  bounds the bounds computed from the "bounds" layer in the SVG.
     */
    public SVG(Picture picture, RectF bounds) {
        this.picture = picture;
        this.bounds = bounds;
    }

    /**
     * Gets the bounding rectangle for the SVG that was computed upon parsing.
     * It may not be entirely accurate for certain curves or transformations,
     * but is often better than nothing.
     *
     * @return rectangle representing the computed bounds.
     */
    public RectF getLimits() {
        return limits;
    }

    /**
     * Set the limits of the SVG, which are the estimated bounds computed by the parser.
     *
     * @param limits the bounds computed while parsing the SVG, may not be entirely accurate.
     */
    public void setLimits(RectF limits) {
        this.limits = limits;
    }

    /**
     * Get the bounding rectangle for the SVG, if one was specified.
     *
     * @return rectangle representing the bounds.
     */
    public RectF getBounds() {
        return bounds;
    }

    /**
     * Get the parsed SVG picture data.
     *
     * @return the picture.
     */
    public Picture getPicture() {
        return picture;
    }

    public PictureDrawable createPictureDrawable() {
        return new PictureDrawable(picture);
//        return new PictureDrawable(picture) {
//            @Override
//            public int getIntrinsicWidth() {
//                if (bounds != null) {
//                    return (int) bounds.width();
//                } else if (limits != null) {
//                    return (int) limits.width();
//                } else {
//                    return -1;
//                }
//            }
//
//            @Override
//            public int getIntrinsicHeight() {
//                if (bounds != null) {
//                    return (int) bounds.height();
//                } else if (limits != null) {
//                    return (int) limits.height();
//                } else {
//                    return -1;
//                }
//            }
//        };
    }
}
