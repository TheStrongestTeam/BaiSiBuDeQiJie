package com.miraclehu.baisibudeqijie.ui.fragment.essence;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.leavessilent.mylibrary.adapters.UniversalAdapter;
import com.leavessilent.mylibrary.utils.HttpUtils;
import com.miraclehu.baisibudeqijie.R;
import com.miraclehu.baisibudeqijie.adapter.essence.EssenceVideoAdapter;
import com.miraclehu.baisibudeqijie.common.HttpConstants;
import com.miraclehu.baisibudeqijie.model.VideoList;
import com.miraclehu.baisibudeqijie.model.VideoRoot;
import com.miraclehu.baisibudeqijie.ui.fragment.BaseFragment;
import com.miraclehu.baisibudeqijie.util.PlayerManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.appsdream.nestrefresh.normalstyle.NestRefreshLayout;

/**
 * Created by user on 2016/9/20.
 */
public class EssenceVideoFragment extends BaseFragment implements UniversalAdapter.OnItemClickListener, MediaPlayer.OnPreparedListener {
    public static final String TAG = EssenceVideoFragment.class.getSimpleName();
    @BindView(R.id.essence_video_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.essence_video_refresh)
    NestRefreshLayout mRefreshLayout;
    private EssenceVideoAdapter mAdapter;
    private View mPreview;
    private View mPb;

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

        mAdapter.setOnItemClickListener(this);

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


    @Override
    public void onItemClick(View view, int position) {
        TextView title = (TextView) view.findViewById(R.id.essence_video_item_title);

        Log.e(TAG, "onItemClick: " + title.getText().toString());
        SurfaceView surfaceView = (SurfaceView) view.findViewById(R.id.essence_video_item_surface_view);
        mPb = view.findViewById(R.id.essence_video_item_pb);
        View play = view.findViewById(R.id.essence_video_item_play);
        mPreview = view.findViewById(R.id.essence_video_item_preview);
        mPb.setVisibility(View.VISIBLE);
        play.setVisibility(View.GONE);
        PlayerManager.reset();
        PlayerManager.setDataUrl(mAdapter.getItem(position).getVideo().getVideo().get(0));
        PlayerManager.prepareAsyn();
        PlayerManager.setDisplay(surfaceView.getHolder());
        PlayerManager.setOnPreparedListener(this);
    }


    @Override
    public void onPrepared(MediaPlayer mp) {
        PlayerManager.start();
        mPreview.setVisibility(View.GONE);
        mPb.setVisibility(View.GONE);
    }
}
