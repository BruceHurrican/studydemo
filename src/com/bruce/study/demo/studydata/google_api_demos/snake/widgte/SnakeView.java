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

package com.bruce.study.demo.studydata.google_api_demos.snake.widgte;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import com.bruce.study.demo.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by BruceHurrican on 2015/10/10.
 */
public class SnakeView extends TitleView {
    private static final String TAG = "SnakeView";
    /**
     * Current mode of application: READY to run, Running, or you have already
     * lost. static final ints are used instead of an enum for performance.
     */
    private int mMode = READY;
    public static final int PAUSE = 0;
    public static final int READY = 1;
    public static final int RUNNING = 2;
    public static final int LOSE = 3;

    /**
     * Current direction the snake is headed.
     */
    private int mDirection = NORTH;
    private int mNextDirection = NORTH;
    private static final int NORTH = 1;
    private static final int SOUTH = 2;
    private static final int EAST = 3;
    private static final int WEST = 4;

    /**
     * Labels for the drawables that will be loaded into the TileView class
     */
    private static final int RED_STAR = 1;
    private static final int YELLOW_STAR = 2;
    private static final int GREEN_STAR = 3;

    /**
     * used to track the number of apples captured
     * This will decrease as apples are captured.
     */
    private long mScore = 0;
    /**
     * number of milliseconds between snake movements.
     */
    private long mMoveDelay = 600;
    /**
     * tracks the absolute time when the snake last moved, and is used
     * to determine if a move should be made based on mMoveDelay
     */
    private long mlastMove;
    /**
     * text shows to the user in some run states
     */
    private TextView mStatusText;
    /**
     * a list of Coordinate that make up the snake's body
     */
    private ArrayList<Coordinate> mSnakeTrail = new ArrayList<>(5);
    /**
     * the secret location the juicy apples the snake craves
     */
    private ArrayList<Coordinate> mAppleList = new ArrayList<>(5);

    private static final Random RNG = new Random();
    /**
     * Create a simple handler that we can use to cause animation to happen.
     * We set ourselves as a target and we can use the sleep()
     * function to cause an update/invalidate to occur at a later date.
     */
    private RefreshHandler mRefreshHandler = new RefreshHandler();
    private class RefreshHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            SnakeView.this.update();
            SnakeView.this.invalidate();
        }

        public void sleep(long delayMillis){
            removeMessages(0);
            sendMessageDelayed(obtainMessage(0),delayMillis);
        }
    }
    public SnakeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSnakeView();
    }

    public SnakeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSnakeView();
    }

    private void initSnakeView(){
        setFocusable(true);
        Resources r = getContext().getResources();
        resetTiles(4);
        loadTile(RED_STAR,r.getDrawable(R.drawable.snake_redstar));
        loadTile(YELLOW_STAR, r.getDrawable(R.drawable.snake_yellowstar));
        loadTile(GREEN_STAR, r.getDrawable(R.drawable.snake_greenstar));
    }

    private void initNewGame(){
        mSnakeTrail.clear();
        mAppleList.clear();

        // For now we're just going to load up a short default eastbound snake
        // that's just turned north
        mSnakeTrail.add(new Coordinate(7,7));
        mSnakeTrail.add(new Coordinate(6,7));
        mSnakeTrail.add(new Coordinate(5,7));
        mSnakeTrail.add(new Coordinate(4,7));
        mSnakeTrail.add(new Coordinate(3,7));
        mSnakeTrail.add(new Coordinate(2,7));
        mNextDirection = NORTH;

        // Two apples to start with
        addRandomApple();
        addRandomApple();

        mMoveDelay = 600;
        mScore =0;
    }

    /**
     * Given a ArrayList of Coordinates, we need to flatten them into an array
     * of ints before we can stuff them into a map for flattening and storage.
     * @param evec a ArrayList of Coordinate objects
     * @return a simple array containing the x/y values of the coordinates as [x1,y1,x2,y2,x3,y3...]
     */
    private int[] coordArrayListToArray(ArrayList<Coordinate> evec){
        int count = evec.size();
        int[] rawArray = new int[count*2];
        for (int i = 0; i < count; i++) {
            Coordinate c = evec.get(i);
            rawArray[2*i] = c.x;
            rawArray[2*i+1] = c.y;
        }
        return rawArray;
    }

    /**
     * Save game state so that the user does not lose
     * anything if the game process is killed while we are
     * in the background.
     * @return a Bundle with this view's state
     */
    public Bundle saveState(){
        Bundle map = new Bundle();
        map.putIntArray("mAppleList", coordArrayListToArray(mAppleList));
        map.putInt("mDirection", mDirection);
        map.putInt("mNextDirection", mNextDirection);
        map.putLong("mMoveDelay", mMoveDelay);
        map.putLong("mScore", mScore);
        map.putIntArray("mSnakeTrail",coordArrayListToArray(mSnakeTrail));
        return map;
    }

    /**
     * Given a flattened array of ordinate pairs, we reconstitute them into a
     * ArrayList of Coordinate objects
     * @param rawArray [x1,y1,x2,y2,...]
     * @return a ArrayList of Coordinates
     */
    private ArrayList<Coordinate> coordArrayToArrayList(int[] rawArray){
        ArrayList<Coordinate> coordArrayList = new ArrayList<>(5);
        int coordCount = rawArray.length;
        for (int i = 0; i < coordCount; i+=2) {
            Coordinate c = new Coordinate(rawArray[i],rawArray[i+1]);
            coordArrayList.add(c);
        }
        return coordArrayList;
    }

    /**
     * Restore game state if our process is being relaunched
     * @param icicle a Bundle containing the game state
     */
    public void restoreState(Bundle icicle){
        setMode(PAUSE);
        mAppleList = coordArrayToArrayList(icicle.getIntArray("mAppleList"));
        mDirection = icicle.getInt("mDirection");
        mNextDirection = icicle.getInt("mNextDirection");
        mMoveDelay = icicle.getLong("mMoveDelay");
        mScore = icicle.getLong("mScore");
        mSnakeTrail = coordArrayToArrayList(icicle.getIntArray("mSnakeTrail"));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
            if (mMode == READY | mMode == LOSE) {
                /*
                 * At the beginning of the game, or the end of a previous one,
                 * we should start a new game.
                 */
                initNewGame();
                setMode(RUNNING);
                update();
                return (true);
            }
            if (mMode == PAUSE) {
                /*
                 * If the game is merely paused, we should just continue where
                 * we left off.
                 */
                setMode(RUNNING);
                update();
                return (true);
            }
            if (mDirection != SOUTH) {
                mNextDirection = NORTH;
            }
            return (true);
        }
        if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
            if (mDirection != NORTH) {
                mNextDirection = SOUTH;
            }
            return (true);
        }
        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            if (mDirection != EAST) {
                mNextDirection = WEST;
            }
            return (true);
        }
        if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            if (mDirection != WEST) {
                mNextDirection = EAST;
            }
            return (true);
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * Sets the TextView that will be used to give information(such as "Game Over" to the user
     * @param newView
     */
    public void setTextView(TextView newView){
        mStatusText = newView;
    }

    public void setMode(int newMode){
        int oldMode = mMode;
        mMode = newMode;
        if (newMode == RUNNING & oldMode != RUNNING){
            mStatusText.setVisibility(View.INVISIBLE);
            update();
            return;
        }

        Resources res = getContext().getResources();
        CharSequence str = "";
        if (newMode ==PAUSE){
            str = res.getText(R.string.mode_pause);
        }
        if (newMode == READY){
            str = res.getText(R.string.mode_ready);
        }
        if (newMode == LOSE){
            str = res.getText(R.string.mode_lose_prefix)+(mScore+"")+res.getText(R.string.mode_lose_suffix);
        }
        mStatusText.setText(str);
        mStatusText.setVisibility(VISIBLE);
    }

    // TODO
    private void addRandomApple(){

    }

    /**
     * Simple class containing two integer values and a comparison function.
     * There's probably something I should use instead, but this was quick and
     * easy to build.
     */
    private class Coordinate {
        public int x;
        public int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean equals(Coordinate other) {
            if (x == other.x && y == other.y) {
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            return "Coordinate:[" + x + "," + y + "]";
        }
    }
}
