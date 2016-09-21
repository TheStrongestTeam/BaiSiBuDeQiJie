package com.miraclehu.baisibudeqijie.ui.fragment.essence;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miraclehu.baisibudeqijie.R;
import com.miraclehu.baisibudeqijie.ui.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2016/9/20.
 */
public class EssencePictureFragment extends BaseFragment {
    public static final String TAG = EssencePictureFragment.class.getSimpleName();
    @BindView(R.id.essence_picture_rv)
    RecyclerView essencePictureRv;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_essence_picture;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        essencePictureRv.setLayoutManager(layoutManager);
//        essencePictureRv.setAdapter();
    }

}
