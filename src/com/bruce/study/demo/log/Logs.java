/*
 * Copyright (c) 2015.
 *   This document is just for Bruce's personal study.
 *   Some lines from Internet. Everyone can download and use for study, but can
 *   not be used for commercial purpose. The author does not bear the
 *   corresponding disputes arising therefrom.
 *   Please delete within 24 hours after download.
 *   If you have good suggestions for this code, you can contact BurrceHurrican@foxmail.com.
 *   本文件为Bruce's个人学习android的demo, 其中所用到的代码来源于互联网，仅作为学习交流使用。
 *   任和何人可以下载并使用, 但是不能用于商业用途。
 *   作者不承担由此带来的相应纠纷。
 *   如果对本代码有好的建议，可以联系BurrceHurrican@foxmail.com
 */

package com.bruce.study.demo.log;

import android.util.Log;

/**
 * 日志基类
 * Created by BruceHurrican on 2015/7/5.
 */
public class Logs {
    public static final boolean isDebug = true;

    public static void v(String tag, String text) {
        if (isDebug) {
            Log.v(tag, text);
        }
    }

    public static void d(String tag, String text) {
        if (isDebug) {
            Log.d(tag, text);
        }
    }

    public static void i(String tag, String text) {
        if (isDebug) {
            Log.i(tag, text);
        }
    }

    public static void w(String tag, String text) {
        if (isDebug) {
            Log.w(tag, text);
        }
    }

    public static void e(String tag, String text) {
        if (isDebug) {
            Log.e(tag, text);
        }
    }
}