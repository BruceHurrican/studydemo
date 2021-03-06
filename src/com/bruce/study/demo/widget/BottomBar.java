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

package com.bruce.study.demo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.bruce.study.demo.R;

import java.util.List;

/**
 * 底栏
 * Created by BruceHurrican on 2015/12/1.
 */
public class BottomBar extends RadioGroup implements RadioGroup.OnCheckedChangeListener {
    private Context context;
    private RadioButton btn1, btn2, btn3, btn4;
    private RadioGroup container;

    public BottomBar(Context context) {
        super(context);
        this.context = context;
        initViews();
    }

    public BottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initViews();
    }

    private void initViews() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_widget_bottombar, this);
        btn1 = (RadioButton) findViewById(R.id.btn_1);
        btn2 = (RadioButton) findViewById(R.id.btn_2);
        btn3 = (RadioButton) findViewById(R.id.btn_3);
        btn4 = (RadioButton) findViewById(R.id.btn_4);
        container = (RadioGroup) findViewById(R.id.container);
        bindListener();
    }

    private void bindListener() {
        container.setOnCheckedChangeListener(this);
    }

    /**
     * 设置导航栏文本信息
     *
     * @param texts size大小应与 radiobutton 数目一致
     */
    public void setBtnText(List<String> texts) {
        if (null != texts && null != container && texts.size() == container.getChildCount()) {
            for (int i = 0; i < texts.size(); i++) {
                ((RadioButton) container.getChildAt(i)).setText(texts.get(i));
            }
        } else {
            Toast.makeText(context, "数据格式错误", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (group.equals(container)) {
            switch (checkedId) {
                case R.id.btn_1:
                    Toast.makeText(context, "btn1 clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_2:
                    Toast.makeText(context, "btn2 clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_3:
                    Toast.makeText(context, "btn3 clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_4:
                    Toast.makeText(context, "btn4 clicked", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
