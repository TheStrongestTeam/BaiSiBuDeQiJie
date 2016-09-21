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
import com.miraclehu.baisibudeqijie.adapter.essence.EssencePictureAdapter;
import com.miraclehu.baisibudeqijie.common.HttpConstants;
import com.miraclehu.baisibudeqijie.model.VideoRoot;
import com.miraclehu.baisibudeqijie.ui.fragment.BaseFragment;

import javax.security.auth.login.LoginException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2016/9/20.
 */
public class EssencePictureFragment extends BaseFragment {
    public static final String TAG = EssencePictureFragment.class.getSimpleName();
    @BindView(R.id.essence_picture_rv)
    RecyclerView essencePictureRv;
    private EssencePictureAdapter adapter;
    private int pageNum = 0;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_essence_picture;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        essencePictureRv.setLayoutManager(layoutManager);
        adapter = new EssencePictureAdapter(context,null);
        essencePictureRv.setAdapter(adapter);

        setupView(State.DOWN);
    }

    enum State{
        DOWN,UP
    }
    private void setupView(final State state) {

        HttpUtils.getAsynString(HttpConstants.ESSENCE_PICTURE_BEGIN + pageNum + HttpConstants.ESSENCE_PICTURE_END, new HttpUtils.ResultCallback() {
            @Override
            public void onFailure(Exception e) {
                Log.e(TAG, "onFailure: 777777777777777777777" );
            }

            @Override
            public void onFinish(String result) {
                Log.e(TAG, "onFinish: " + result );
                Gson gson = new Gson();
                VideoRoot videoRoot = gson.fromJson(result, VideoRoot.class);
                switch (state) {
                    case DOWN:
                        adapter.updateRes(videoRoot.getList());
                        videoRoot.getList().get(0).getImage().getHeight();
                        break;
                    case UP:
                        break;
                }
            }
        });
    }

}
