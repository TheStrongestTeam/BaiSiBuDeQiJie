package com.miraclehu.baisibudeqijie.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by user on 2016/9/19.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder mBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        mBinder = ButterKnife.bind(this);

        initView(savedInstanceState);

    }

    public abstract int getContentViewId();

    public abstract void initView(Bundle savedInstanceState);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinder.unbind();
    }
}
