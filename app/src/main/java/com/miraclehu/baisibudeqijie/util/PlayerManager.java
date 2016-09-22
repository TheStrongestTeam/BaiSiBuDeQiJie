package com.miraclehu.baisibudeqijie.util;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.SurfaceHolder;

import java.io.IOException;

/**
 * Created by hasee on 2016/9/22.
 */
public class PlayerManager {
    private static Context sContext;
    private MediaPlayer mPlayer;

    private static PlayerManager sManager;


    private PlayerManager() {
        mPlayer = new MediaPlayer();
    }

    private static PlayerManager getInstance() {
        if (sManager == null) {
            synchronized (PlayerManager.class) {
                sManager = new PlayerManager();
            }
        }
        return sManager;
    }

    /**
     * 开始
     */
    private void _start() {
        if (mPlayer != null)
            mPlayer.start();
    }

    /**
     * 暂停
     */
    private void _pause() {
        if (mPlayer != null) {
            if (mPlayer.isPlaying())
                mPlayer.pause();
        }
    }

    /**
     * 停止
     */
    private void _stop() {
        if (mPlayer != null) {
            if (mPlayer.isPlaying()) {
                mPlayer.pause();
                mPlayer.stop();
            }
        }
    }

    /**
     * 释放资源
     */
    private void _release() {
        if (mPlayer != null)
            mPlayer.release();
    }


    private void _setDisplay(SurfaceHolder holder) {
        if (mPlayer != null)
            mPlayer.setDisplay(holder);
    }


    /**
     * 设置数据资源
     */
    private void _setDataUrl(String url) {
        try {
            if (mPlayer != null)
                mPlayer.setDataSource(sContext, Uri.parse(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 重置
     */
    private void _reset() {
        if (mPlayer != null) {
            if (mPlayer.isPlaying()) {
                _stop();
                mPlayer.reset();
            }
        }
    }

    private void _prepare() {
        try {
            mPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MediaPlayer getPlayer() {
        return mPlayer;
    }

    public static void init(Context context) {
        sContext = context;
    }


    // ---------------------------为外界提供的方法----------------------------------
    public static void start() {
        getInstance()._start();
    }

    public static void pause() {
        getInstance()._pause();
    }

    public static void prepare() {
        getInstance()._prepare();
    }

    public static void stop() {
        getInstance()._stop();
    }

    public static void setDataUrl(String url) {
        getInstance()._setDataUrl(url);
    }

    public static void reset() {
        getInstance()._reset();
    }

    public static void release() {
        getInstance()._release();
    }

    public static void setDisplay(SurfaceHolder holder) {
        getInstance()._setDisplay(holder);
    }

    // 为外界提供设置准备监听
    public static void setOnPreparedListener(MediaPlayer.OnPreparedListener listener) {
        getInstance().getPlayer().setOnPreparedListener(listener);
    }

}
