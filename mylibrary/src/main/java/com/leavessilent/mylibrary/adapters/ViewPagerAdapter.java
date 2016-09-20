package com.leavessilent.mylibrary.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Leavessilent on 2016/9/1.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mData;

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> data) {
        super(fm);
        mData = data;
    }

    /**
     * 更新资源
     *
     * @param data
     */
    public void updataRes(List<Fragment> data) {
        if (data != null) {
            mData.clear();
            mData.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }
}
