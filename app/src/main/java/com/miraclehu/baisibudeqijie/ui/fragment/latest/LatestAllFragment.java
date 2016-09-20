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

/**
 * A simple {@link Fragment} subclass.
 */
public class LatestAllFragment extends BaseFragment {


    @BindView(R.id.latest_all_lv)
    ListView mLatestAllLv;
    private LastestAllAdapter mAdapter;
    private long  page=0;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_lastest_all;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mAdapter = new LastestAllAdapter(context,null, R.layout.lastest_all_item_1);
        mLatestAllLv.setAdapter(mAdapter);
        setupView(page,option.UP);
    }

    private void setupView(long sPage, final option option) {
        HttpUtils.getAsynString(HttpConstants.LASTEST_ALL_BEGIN + sPage + HttpConstants.LASTEST_ALL_END, new HttpUtils.ResultCallback() {
            @Override
            public void onFailure(Exception e) {
            }

            @Override
            public void onFinish(String result) {
                Gson gson = new Gson();
                VideoRoot root = gson.fromJson(result, VideoRoot.class);
                switch (option) {
                    case UP:
                        mAdapter.updataData(root.getList());
                        break;
                    case DOWM:
                        mAdapter.addData(root.getList());
                        break;
                }
            }
        });
    }

    enum option{
        UP,DOWM
    }
}
