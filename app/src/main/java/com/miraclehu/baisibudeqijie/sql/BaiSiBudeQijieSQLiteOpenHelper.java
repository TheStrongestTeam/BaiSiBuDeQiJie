package com.miraclehu.baisibudeqijie.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hasee on 2016/9/23.
 */
public class BaiSiBuDeQiJieSQLiteOpenHelper extends SQLiteOpenHelper {

    // 为保证代码可观性，可以这里进行数据库的建表操作
    // 如：
    private static final String CREATE_VIDEO = "create video if not exists (_id integer primary key autoincrement,title text etc...)";

    public BaiSiBuDeQiJieSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 执行建表操作都在这里，根据你们的需要创建
        db.execSQL(CREATE_VIDEO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
