package com.miraclehu.baisibudeqijie.adapter.essence;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.leavessilent.mylibrary.adapters.UniversalAdapter;
import com.leavessilent.mylibrary.utils.ImageHelper;
import com.miraclehu.baisibudeqijie.R;
import com.miraclehu.baisibudeqijie.model.U;
import com.miraclehu.baisibudeqijie.model.VideoList;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by hasee on 2016/9/23.
 */
public class EssenceVideoAdapter extends UniversalAdapter<VideoList> {
    private Context mContext;

    public EssenceVideoAdapter(Context context, List<VideoList> data) {
        super(context, data);
        mContext = context;
    }

    public EssenceVideoAdapter(Context context) {
        this(context, null);
    }

    @Override
    public int getItemLayoutID(int viewType) {
        return R.layout.essence_video_item;
    }

    @Override
    protected void onBindDataToView(RecyclerViewHolder holder, VideoList videoList, int position) {
        ImageView icon = holder.getImageView(R.id.essence_video_item_icon);
        TextView name = holder.getTextView(R.id.essence_video_item_name);
        ImageView isV = holder.getImageView(R.id.essence_video_item_is_v);
        TextView time = holder.getTextView(R.id.essence_video_item_passtime);
        TextView title = holder.getTextView(R.id.essence_video_item_title);
        ImageView preview = holder.getImageView(R.id.essence_video_item_preview);
        ImageView play = holder.getImageView(R.id.essence_video_item_play);
        TextView down = holder.getTextView(R.id.cai);
        TextView up = holder.getTextView(R.id.ding);
        TextView forward = holder.getTextView(R.id.share);
        TextView comment = holder.getTextView(R.id.common);

        U u = videoList.getU();
        CropCircleTransformation transformation = new CropCircleTransformation(mContext);
        Glide.with(mContext).load(u.getHeader().get(0)).bitmapTransform(transformation).into(icon);
        name.setText(u.getName());
        if (u.is_v()) {
            isV.setVisibility(View.VISIBLE);
        } else {
            isV.setVisibility(View.GONE);
        }
        String passtime = videoList.getPasstime();
        time.setText(passtime.substring(0, passtime.length() - 3));
        title.setText(videoList.getText());
        ImageHelper.display(preview, videoList.getVideo().getThumbnail().get(0));
        play.setOnClickListener(this);

        up.setText(videoList.getUp());
        down.setText(videoList.getDown());
        forward.setText(videoList.getForward());
        comment.setText(videoList.getComment());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.essence_video_item_play:

                break;
        }
    }
}
