package com.miraclehu.baisibudeqijie.ui.fragment.essence;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miraclehu.baisibudeqijie.R;
import com.miraclehu.baisibudeqijie.ui.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.appsdream.nestrefresh.normalstyle.NestRefreshLayout;

/**
 * Created by user on 2016/9/20.
 */
public class EssenceJokeFragment extends BaseFragment {
    public static final String TAG = EssenceJokeFragment.class.getSimpleName();
    @BindView(R.id.essence_joke_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.essence_joke_refresh_layout)
    NestRefreshLayout mRefreshLayout;


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_essence_joke;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }
}
