package com.leavessilent.mylibrary.utils;

import android.text.format.DateFormat;

/**
 * Created by Leavessilent on 2016/9/7.
 */
public class DateUtil {

    public static CharSequence parseTime(int time) {
        return DateFormat.format("mm:ss", time);
    }
}
