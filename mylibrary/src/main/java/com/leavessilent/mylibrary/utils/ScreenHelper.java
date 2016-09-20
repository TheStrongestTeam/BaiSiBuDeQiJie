package com.leavessilent.mylibrary.utils;

import com.leavessilent.mylibrary.MyLibrary;

/**
 * Created by Leavessilent on 2016/8/31.
 */
public class ScreenHelper {
    public static float getScreeDensity() {
        return MyLibrary.getContext().getResources().getDisplayMetrics().density;
    }

    public static int getScreenWidth() {
        return MyLibrary.getContext().getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return MyLibrary.getContext().getResources().getDisplayMetrics().heightPixels;
    }

    public static int dp2px(int dpValue) {
        float scale = MyLibrary.getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dp(int pxValue) {
        float scale = MyLibrary.getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
