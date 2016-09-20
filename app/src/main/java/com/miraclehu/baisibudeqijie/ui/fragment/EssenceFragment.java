package com.miraclehu.baisibudeqijie.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.miraclehu.baisibudeqijie.R;
import com.miraclehu.baisibudeqijie.adapter.CommonViewPagerAdapter;

import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
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
    @Override
    public int getLayoutResId() {
        return R.layout.fragment_essence;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        essenceVpContent.setAdapter(new CommonViewPagerAdapter(getChildFragmentManager(),getFragmentData(),mTitle));
    }

    private List<Fragment> getFragmentData() {

        return null;
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
