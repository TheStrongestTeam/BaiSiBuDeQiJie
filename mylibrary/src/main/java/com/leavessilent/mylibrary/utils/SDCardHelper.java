package com.leavessilent.mylibrary.utils;

import android.content.Context;
import android.os.Environment;

public class SDCardHelper {

    /**
     * 获取缓冲的路径
     *
     * @param context
     * @return
     */
    public static String getDiskCacheDir(Context context) {
        String cachePath;
        if (hasSDCard()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }

    private static boolean hasSDCard() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            return true;
        }
        return false;
    }


}

