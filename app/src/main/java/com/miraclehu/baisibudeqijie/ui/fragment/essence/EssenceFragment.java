package com.miraclehu.baisibudeqijie.ui.fragment.essence;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.miraclehu.baisibudeqijie.R;
import com.miraclehu.baisibudeqijie.adapter.CommonViewPagerAdapter;
import com.miraclehu.baisibudeqijie.ui.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by user on 2016/9/20.
 */
public class EssenceFragment extends BaseFragment {

    public static final String TAG = EssenceFragment.class.getSimpleName();

    @BindView(R.id.essence_iv_refresh)
    ImageView essenceIvRefresh;
    @BindView(R.id.essence_iv_through)
    ImageView essenceIvThrough;
    @BindView(R.id.essence_tab_title)
    TabLayout essenceTabTitle;
    @BindView(R.id.essence_vp_content)
    ViewPager essenceVpContent;
    @BindArray(R.array.essence_title)
    String[] mTitle;
    private CommonViewPagerAdapter adapter;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_essence;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        adapter = new CommonViewPagerAdapter(getChildFragmentManager(),getFragmentData(),mTitle);
        essenceVpContent.setAdapter(adapter);
        essenceTabTitle.setupWithViewPager(essenceVpContent);
        essenceTabTitle.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private List<Fragment> getFragmentData() {
        List<Fragment> data = new ArrayList<>();
        EssenceRecommendFragment recommendFragment = new EssenceRecommendFragment();
        EssenceVideoFragment videoFragment = new EssenceVideoFragment();
        EssencePictureFragment pictureFragment = new EssencePictureFragment();
        EssenceJokeFragment jokeFragment = new EssenceJokeFragment();
        EssenceStarFragment starFragment = new EssenceStarFragment();
        EssenceRankFragment rankFragment = new EssenceRankFragment();
        EssenceSocietyFragment societyFragment = new EssenceSocietyFragment();
        EssenceBeautyFragment beautyFragment = new EssenceBeautyFragment();
        EssenceKnowledgeFragment knowledgeFragment = new EssenceKnowledgeFragment();
        EssenceGameFragment gameFragment = new EssenceGameFragment();
        EssenceSelifeFragment soundFragment = new EssenceSelifeFragment();
        data.add(recommendFragment);
        data.add(videoFragment);
        data.add(pictureFragment);
        data.add(jokeFragment);
        data.add(starFragment);
        data.add(rankFragment);
        data.add(societyFragment);
        data.add(beautyFragment);
        data.add(knowledgeFragment);
        data.add(gameFragment);
        data.add(soundFragment);
        return data;
    }

    @OnClick({R.id.essence_iv_refresh, R.id.essence_iv_through})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.essence_iv_refresh:

                break;
            case R.id.essence_iv_through:

                break;
        }
    }
}
