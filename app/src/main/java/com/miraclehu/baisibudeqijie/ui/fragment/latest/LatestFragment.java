package com.miraclehu.baisibudeqijie.ui.fragment.latest;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leavessilent.mylibrary.adapters.ViewPagerAdapter;
import com.miraclehu.baisibudeqijie.R;
import com.miraclehu.baisibudeqijie.adapter.CommonViewPagerAdapter;
import com.miraclehu.baisibudeqijie.ui.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user on 2016/9/20.
 */
public class LatestFragment extends BaseFragment {

    public static final String TAG = LatestFragment.class.getSimpleName();
    @BindView(R.id.latest_tab_layout)
    TabLayout mLatestTabLayout;
    @BindView(R.id.latest_view_pager)
    ViewPager mLatestViewPager;

    @BindArray(R.array.latest_title)
    String[] mTitles;
    private CommonViewPagerAdapter mAdapter;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_latest;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mAdapter = new CommonViewPagerAdapter(getChildFragmentManager(), getData(), mTitles);
        mLatestViewPager.setAdapter(mAdapter);
        mLatestTabLayout.setupWithViewPager(mLatestViewPager);
        mLatestTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

    }

    private List<Fragment> getData() {
        List<Fragment> data = new ArrayList<>();
        LatestAllFragment allFragment = new LatestAllFragment();
        LatestVideoFragment videoFragment = new LatestVideoFragment();
        LatestPicFragment picFragment = new LatestPicFragment();
        LatestJokeFragment jokeFragment = new LatestJokeFragment();
        LatestStarFragment starFragment = new LatestStarFragment();
        LatestBeautyFragment beautyFragment = new LatestBeautyFragment();
        LatestKnowledgeFragment knowledgeFragment = new LatestKnowledgeFragment();
        LatestGameFragment gameFragment = new LatestGameFragment();
        LatestSoundFragment soundFragment = new LatestSoundFragment();
        LatestSelfieFragment selfieFragment = new LatestSelfieFragment();
        data.add(allFragment);
        data.add(videoFragment);
        data.add(picFragment);
        data.add(jokeFragment);
        data.add(starFragment);
        data.add(beautyFragment);
        data.add(knowledgeFragment);
        data.add(gameFragment);
        data.add(soundFragment);
        data.add(selfieFragment);
        return data;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
