package com.miraclehu.baisibudeqijie.ui.fragment.essence;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.leavessilent.mylibrary.utils.HttpUtils;
import com.miraclehu.baisibudeqijie.R;
import com.miraclehu.baisibudeqijie.adapter.essence.EssencePictureAdapter;
import com.miraclehu.baisibudeqijie.common.HttpConstants;
import com.miraclehu.baisibudeqijie.model.VideoRoot;
import com.miraclehu.baisibudeqijie.ui.PictureActivity;
import com.miraclehu.baisibudeqijie.ui.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.appsdream.nestrefresh.base.AbsRefreshLayout;
import cn.appsdream.nestrefresh.base.OnPullListener;
import cn.appsdream.nestrefresh.normalstyle.NestRefreshLayout;

/**
 * Created by user on 2016/9/20.
 */
public class EssencePictureFragment extends BaseFragment implements OnPullListener, EssencePictureAdapter.OnItemClickListener {
    public static final String TAG = EssencePictureFragment.class.getSimpleName();
    @BindView(R.id.essence_picture_rv)
    RecyclerView essencePictureRv;
    @BindView(R.id.essence_nest_refresh)
    NestRefreshLayout essenceNestRefresh;
    private EssencePictureAdapter adapter;
    private long pageNum = 0;
    private long np;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_essence_picture;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        essenceNestRefresh.setOnLoadingListener(this);
        essenceNestRefresh.setPullRefreshEnable(true);
        essenceNestRefresh.setPullLoadEnable(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        essencePictureRv.setLayoutManager(layoutManager);
        adapter = new EssencePictureAdapter(context, null);
        adapter.setListener(this);
        essencePictureRv.setAdapter(adapter);
        setupView(State.DOWN);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onItemClick(View v, int position) {
        switch (v.getId()) {
            case R.id.picture_item_picture:
                Intent intent = new Intent(context, PictureActivity.class);
                startActivity(intent);
                break;
        }
    }


    enum State {
        DOWN, UP
    }

    private void setupView(final State state) {

        HttpUtils.getAsynString(HttpConstants.ESSENCE_PICTURE_BEGIN + pageNum + HttpConstants.ESSENCE_PICTURE_END, new HttpUtils.ResultCallback() {

            @Override
            public void onFailure(Exception e) {
                Log.e(TAG, "onFailure: 777777777777777777777");
            }

            @Override
            public void onFinish(String result) {
                Log.e(TAG, "onFinish: " + result);
                Gson gson = new Gson();
                VideoRoot videoRoot = gson.fromJson(result, VideoRoot.class);
                np = videoRoot.getInfo().getNp();
                switch (state) {
                    case DOWN:
                        adapter.updateRes(videoRoot.getList());
                        break;
                    case UP:
                        adapter.addDataList(videoRoot.getList());
                        break;
                }
                essenceNestRefresh.onLoadFinished();
            }
        });
    }

    @Override
    public void onRefresh(AbsRefreshLayout listLoader) {
        pageNum = 0;
        setupView(State.DOWN);
    }

    @Override
    public void onLoading(AbsRefreshLayout listLoader) {
        pageNum = np;
        setupView(State.UP);
    }

}
