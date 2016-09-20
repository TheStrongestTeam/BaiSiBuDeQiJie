package com.miraclehu.baisibudeqijie.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by hasee on 2016/9/20.
 */
public class CommonViewPagerAdapter extends FragmentPagerAdapter {


    private List<Fragment> mData;
    // 每一个fragment的title
    private String[] mTitles;

    public CommonViewPagerAdapter(FragmentManager fm, List<Fragment> data, String[] titles) {
        super(fm);
        mData = data;
        mTitles = titles;
    }


    @Override
    public Fragment getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
