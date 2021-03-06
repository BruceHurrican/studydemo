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

package com.bruce.study.demo.log;

import android.util.Log;

/**
 * 日志基类
 * Created by BruceHurrican on 2015/7/5.
 */
public final class Logs {
    public static final boolean isDebug = true;
    private static Logs instance;

    private Logs() {
    }

    public static Logs getInstance() {
        if (null != instance) {
            instance = new Logs();
        }
        return instance;
    }

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
