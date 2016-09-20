package com.leavessilent.mylibrary;

import android.app.Application;
import android.content.Context;


/**
 * 自定义的一个工具库
 * 图片加载：Glide
 * 网络请求：OkHttp
 * Created by Leavessilent on 2016/8/31.
 */
public class MyLibrary {
    private static Context sContext;

    public static void init(Application application) {
        sContext = application;
    }

    public static Context getContext() {
        return sContext;
    }
}
