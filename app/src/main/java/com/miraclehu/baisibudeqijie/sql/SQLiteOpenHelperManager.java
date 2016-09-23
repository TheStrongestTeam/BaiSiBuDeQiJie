package com.miraclehu.baisibudeqijie.sql;

import android.content.Context;

/**
 * 该类存在的目的为了保证全局只有一个BaiSiBuDeQiJieSQLiteOpenHelper实例
 * Created by hasee on 2016/9/23.
 */
public class SQLiteOpenHelperManager {

    //数据库名一般是app_name.db
    private static final String DB_NAME = "BaiSiBuDeQiJie.db";
    // 数据库版本
    private static final int DB_VERSION = 1;

    private static SQLiteOpenHelperManager sManager;

    private BaiSiBuDeQiJieSQLiteOpenHelper sHelper;

    private SQLiteOpenHelperManager(Context context) {
        sHelper = new BaiSiBuDeQiJieSQLiteOpenHelper(context, DB_NAME, null, DB_VERSION);
    }

    public static SQLiteOpenHelperManager getInstance(Context context) {
        if (sManager == null) {
            synchronized (SQLiteOpenHelperManager.class) {
                if (sManager == null) {
                    sManager = new SQLiteOpenHelperManager(context);
                }
            }
        }
        return sManager;
    }


    public BaiSiBuDeQiJieSQLiteOpenHelper getsHelper() {
        return sHelper;
    }

    /**
     * 通过这个方法获取全局唯一个OpenHelper，通过OpenHelper可获得数据库
     *
     * @param context
     * @return
     */
    public static BaiSiBuDeQiJieSQLiteOpenHelper getHelper(Context context) {
        return getInstance(context).getsHelper();
    }
}
