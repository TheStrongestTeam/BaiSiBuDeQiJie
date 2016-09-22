package com.miraclehu.baisibudeqijie.ui.fragment.essence;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.leavessilent.mylibrary.utils.HttpUtils;
import com.miraclehu.baisibudeqijie.R;
import com.miraclehu.baisibudeqijie.adapter.essence.EssenceJokeAdapter;
import com.miraclehu.baisibudeqijie.common.HttpConstants;
import com.miraclehu.baisibudeqijie.model.essence.EssenceJoke;
import com.miraclehu.baisibudeqijie.ui.fragment.BaseFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

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
    private EssenceJokeAdapter mAdapter;


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_essence_joke;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new EssenceJokeAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);
        getDataFromInternet();
    }

    /**
     * 从网络获取数据
     */
    private void getDataFromInternet() {
        HttpUtils.getAsynString(HttpConstants.URL_ESSENCE_JOKE, new HttpUtils.ResultCallback() {
            @Override
            public void onFailure(Exception e) {

            }

            @Override
            public void onFinish(String result) {
                try {
                    String list = new JSONObject(result).getJSONArray("list").toString();
                    Gson gson = new Gson();
                    List<EssenceJoke> jokeList = gson.fromJson(list, new TypeToken<List<EssenceJoke>>() {
                    }.getType());
                    mAdapter.updateRes(jokeList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
