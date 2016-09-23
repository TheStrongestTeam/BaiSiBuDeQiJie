package com.miraclehu.baisibudeqijie;

import android.app.Application;

import com.leavessilent.mylibrary.MyLibrary;
import com.miraclehu.baisibudeqijie.util.PlayerManager;


/**
 * Created by user on 2016/9/19.
 */
public class BSBDQJApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MyLibrary.init(this);
    }
}
