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

package com.bruce.study.demo.studydata.demos60.bezier_project;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.bruce.study.demo.log.Logs;

import java.util.Random;

/**
 * 贝塞尔曲线
 * Created by BruceHurrican on 2015/7/26.
 */
public class BezierView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    public int screenWidth, screenHeight;
    private SurfaceHolder surfaceHolder;
    private boolean flag;
    // 贝塞尔曲线成员变量（起始点,控制，终止点，3点坐标）
    private int startX, startY, controlX, controlY, endX, endY;
    // Path
    private Path path;
    // 为了不影响主画笔，这里绘制贝塞尔曲线单独使用一个新画笔
    private Paint bezierPaint;
    // 随机库，让贝塞尔曲线更明显
    private Random random;

    public BezierView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setAntiAlias(true);
        setFocusable(true);
        path = new Path();
        bezierPaint = new Paint();
        bezierPaint.setAntiAlias(true);
        bezierPaint.setStyle(Paint.Style.STROKE);
        bezierPaint.setStrokeWidth(5);
        bezierPaint.setColor(Color.BLUE);
        random = new Random();
    }

    /**
     * 游戏绘图
     */
    public void myDraw() {
        Canvas mCanvas = null;
        try {
            mCanvas = surfaceHolder.lockCanvas();
            if (null != mCanvas) {
                mCanvas.drawColor(Color.BLACK);
                drawBezier(mCanvas);
            }
        } catch (Exception e) {
            Logs.e("BezierView -- >", "绘制贝塞尔曲线失败");
        } finally {
            if (null != mCanvas) {
                surfaceHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }

    /**
     * 绘制贝塞尔曲线
     *
     * @param canvas
     */
    public void drawBezier(Canvas canvas) {
        path.reset(); // 重置path
        path.moveTo(startX, startY); // 贝塞尔曲线的起始点
        path.quadTo(controlX, controlY, endX, endY); // 设置贝塞尔曲线的操作点以及终止点
        canvas.drawPath(path, bezierPaint);
    }

    /**
     * 游戏逻辑
     */
    private void logic() {
        if (0 != endX && 0 != endY) {
            // 设置操作点为线段x/y的一半
            controlX = random.nextInt((endX - startX) / 2);
            controlY = random.nextInt((endY - startY) / 2);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        endX = (int) event.getX();
        endY = (int) event.getY();
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        screenWidth = getWidth();
        screenHeight = getHeight();
        flag = true;
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag = false;
    }

    @Override
    public void run() {
        while (flag) {
            long start = System.currentTimeMillis();
            myDraw();
            logic();
            long end = System.currentTimeMillis();
            try {
                if (end - start < 50) {
                    Thread.sleep(50 - (end - start));
                }
            } catch (InterruptedException e) {
                Logs.e("InterruptedException", e.toString());
            }
        }
    }
}
