package com.miraclehu.baisibudeqijie.ui.fragment.essence;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.leavessilent.mylibrary.utils.HttpUtils;
import com.miraclehu.baisibudeqijie.R;
import com.miraclehu.baisibudeqijie.adapter.essence.EssenceVideoAdapter;
import com.miraclehu.baisibudeqijie.common.HttpConstants;
import com.miraclehu.baisibudeqijie.model.VideoList;
import com.miraclehu.baisibudeqijie.model.VideoRoot;
import com.miraclehu.baisibudeqijie.ui.fragment.BaseFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.appsdream.nestrefresh.normalstyle.NestRefreshLayout;

/**
 * Created by user on 2016/9/20.
 */
public class EssenceVideoFragment extends BaseFragment {
    public static final String TAG = EssenceVideoFragment.class.getSimpleName();
    @BindView(R.id.essence_video_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.essence_video_refresh)
    NestRefreshLayout mRefreshLayout;
    private EssenceVideoAdapter mAdapter;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_essence_video;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new EssenceVideoAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);

        getDataFromInternet();
    }

    private void getDataFromInternet() {
        HttpUtils.getAsynString(HttpConstants.URL_ESSENCE_VIDEO, new HttpUtils.ResultCallback() {
            @Override
            public void onFailure(Exception e) {

            }

            @Override
            public void onFinish(String result) {
                Gson gson = new Gson();
                VideoRoot root = gson.fromJson(result, VideoRoot.class);
                List<VideoList> list = root.getList();
                mAdapter.updateRes(list);
            }
        });
    }

}
