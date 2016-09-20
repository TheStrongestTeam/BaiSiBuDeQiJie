package com.miraclehu.baisibudeqijie.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by user on 2016/9/20.
 */
public abstract class BaseFragment extends Fragment {

    public View layout;
    public Unbinder mBinder;
    public Context context;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(getLayoutResId(),container,false);
        mBinder = ButterKnife.bind(this,layout);
        initView(savedInstanceState);
        return layout;
    }

    public abstract int getLayoutResId();

    protected abstract void initView(Bundle savedInstanceState);

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBinder.unbind();
    }
}