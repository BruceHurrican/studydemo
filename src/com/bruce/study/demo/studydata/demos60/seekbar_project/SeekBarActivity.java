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

package com.bruce.study.demo.studydata.demos60.seekbar_project;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import com.bruce.study.demo.R;
import com.bruce.study.demo.base.BaseActivity;
import com.bruce.study.demo.log.Logs;

/**
 * SeekBarActivity 练习
 * Created by BruceHurrican on 2015/7/5.
 */
public class SeekBarActivity extends BaseActivity {
    private TextView tv_sb;

    @Override
    public String getTAG() {
        return "SeekBarActivity -- >";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demos60_activity_seekbarproject);
        SeekBar sb = (SeekBar) findViewById(R.id.sb);
        tv_sb = (TextView) findViewById(R.id.tv_sb);
        sb.setSecondaryProgress(20);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv_sb.setText("当前<拖动条>的值为：" + progress);
                Logs.i(getTAG(), "当前<拖动条>的值为：" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                tv_sb.setText("拖动中...");
                Logs.i(getTAG(), "拖动中...");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tv_sb.setText("<拖动条>完成拖动");
                Logs.i(getTAG(), "<拖动条>完成拖动");
            }
        });
    }
}
