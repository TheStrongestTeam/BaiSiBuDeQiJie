package com.miraclehu.baisibudeqijie.adapter.latest;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.leavessilent.mylibrary.adapters.MultipleBaseAdapter;
import com.miraclehu.baisibudeqijie.R;
import com.miraclehu.baisibudeqijie.model.Video;
import com.miraclehu.baisibudeqijie.model.VideoList;

import java.util.List;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class LastestAllAdapter extends MultipleBaseAdapter<VideoList> {
    private ImageView mVip;
    private ImageView mV;
    private ImageView mHeader;
    private TextView mName;
    private TextView mPasstime;
    private Context mContext;

    public LastestAllAdapter(Context context, List<VideoList> data, int... layoutResIds) {
        super(context, data, layoutResIds);
        this.mContext=context;
    }

    @Override
    protected void bindData(ViewHolder holder, VideoList item) {
        mVip = ((ImageView) holder.getView(R.id.all_item_vip));
        mV = ((ImageView) holder.getView(R.id.all_item_v));
        mHeader = ((ImageView) holder.getView(R.id.all_item_header));
        mName = ((TextView) holder.getView(R.id.all_item_name));
        mPasstime = ((TextView) holder.getView(R.id.all_item_passtime));

        if (item.getU().is_vip()) {
            mName.setTextColor(Color.RED);
            Glide.with(mContext).load(R.raw.members_mark).asGif().into(mVip);
        }
        if (!item.getU().is_v()) {
            mV.setVisibility(View.GONE);
        }
        Glide.with(mContext).load(item.getVideo().getVideo().get(0)).into(mHeader);
        mName.setText(item.getU().getName());
        mPasstime.setText(item.getPasstime());
    }
}
