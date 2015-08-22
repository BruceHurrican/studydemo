/*
 * Copyright (c) 2015.
 *   This document is just for Bruce's personal study.
 *   Some resources come from the Internet. Everyone can download and use it for study, but can
 *   not be used for commercial purpose. The author does not bear the
 *   corresponding disputes arising therefrom.
 *   Please delete within 24 hours after download.
 *   If you have good suggestions for this code, you can contact BurrceHurrican@foxmail.com.
 *   本文件为Bruce's个人学习android的demo, 其中所用到的代码来源于互联网，仅作为学习交流使用。
 *   任和何人可以下载并使用, 但是不能用于商业用途。
 *   作者不承担由此带来的相应纠纷。
 *   如果对本代码有好的建议，可以联系BurrceHurrican@foxmail.com
 */

package com.bruce.study.demo.github.custom_shape_imageview_demo.svg_android;

/**
 * Runtime exception thrown when there is a problem parsing a SVG.
 * Created by BruceHurrican on 2015/8/22.
 */
public class SVGParseException extends RuntimeException {
    public SVGParseException(String detailMessage) {
        super(detailMessage);
    }

    public SVGParseException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public SVGParseException(Throwable throwable) {
        super(throwable);
    }
}
