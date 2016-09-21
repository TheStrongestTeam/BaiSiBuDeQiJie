package com.miraclehu.baisibudeqijie.ui.fragment.latest;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.leavessilent.mylibrary.utils.HttpUtils;
import com.miraclehu.baisibudeqijie.R;
import com.miraclehu.baisibudeqijie.adapter.latest.LastestAllAdapter;
import com.miraclehu.baisibudeqijie.common.HttpConstants;
import com.miraclehu.baisibudeqijie.model.VideoRoot;
import com.miraclehu.baisibudeqijie.ui.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.appsdream.nestrefresh.base.AbsRefreshLayout;
import cn.appsdream.nestrefresh.base.OnPullListener;
import cn.appsdream.nestrefresh.normalstyle.NestRefreshLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class LatestAllFragment extends BaseFragment implements OnPullListener {


    @BindView(R.id.latest_all_lv)
    ListView mLatestAllLv;
    @BindView(R.id.latest_all_referesh)
    NestRefreshLayout mLatestAllReferesh;
    private LastestAllAdapter mAdapter;
    private long page = 0;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_lastest_all;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mLatestAllReferesh.setOnLoadingListener(this);
        mAdapter = new LastestAllAdapter(context, null, R.layout.lastest_all_item_1);
        mLatestAllLv.setAdapter(mAdapter);
        setupView(option.UP);
    }

    private void setupView(final option option) {
        HttpUtils.getAsynString(HttpConstants.LASTEST_ALL_BEGIN + page + HttpConstants.LASTEST_ALL_END, new HttpUtils.ResultCallback() {
            @Override
            public void onFailure(Exception e) {
            }

            @Override
            public void onFinish(String result) {
                Gson gson = new Gson();
                VideoRoot root = gson.fromJson(result, VideoRoot.class);
                page=root.getInfo().getNp();
                switch (option) {
                    case UP:
                        mAdapter.updateData(root.getList());
                        break;
                    case DOWM:
                        mAdapter.addData(root.getList());
                        break;
                }
            }
        });
    }

    @OnClick(R.id.latest_all_referesh)
    public void onClick() {
    }

    @Override
    public void onRefresh(AbsRefreshLayout listLoader) {
        page=0;
        setupView(option.UP);
        mLatestAllReferesh.onLoadFinished();
    }

    @Override
    public void onLoading(AbsRefreshLayout listLoader) {
        setupView(option.DOWM);
        mLatestAllReferesh.onLoadFinished();
    }

    enum option {
        UP, DOWM
    }
}
