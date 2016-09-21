package com.miraclehu.baisibudeqijie.adapter.latest;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.leavessilent.mylibrary.adapters.MultipleBaseAdapter;
import com.leavessilent.mylibrary.adapters.SingleBaseAdapter;
import com.miraclehu.baisibudeqijie.R;
import com.miraclehu.baisibudeqijie.model.Video;
import com.miraclehu.baisibudeqijie.model.VideoList;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class LastestAllAdapter extends SingleBaseAdapter<VideoList> {
    private static final String TAG = LastestAllAdapter.class.getSimpleName();
    private ImageView mVip;
    private ImageView mV;
    private ImageView mHeader;
    private TextView mName;
    private TextView mPasstime;
    private Context mContext;
    private TextView mText;
    private ImageView mThumbnail;
    private View mLayoutVideo;

    public LastestAllAdapter(Context context, List<VideoList> data, int layoutId) {
        super(context, data, layoutId);
        this.mContext = context;
    }


    @Override
    protected void bindData(ViewHolder holder, VideoList item) {
        mVip = ((ImageView) holder.getView(R.id.all_item_vip));
        mV = ((ImageView) holder.getView(R.id.all_item_v));
        mHeader = ((ImageView) holder.getView(R.id.all_item_header));
        mName = ((TextView) holder.getView(R.id.all_item_name));
        mPasstime = ((TextView) holder.getView(R.id.all_item_passtime));
        mText = ((TextView) holder.getView(R.id.all_item_text));
        mThumbnail = ((ImageView) holder.getView(R.id.all_item_thumbnail));
        mLayoutVideo = holder.getView(R.id.all_item_layout_video);
        //判断是否为vip  如果是，设置红色名字，带黄钻图案
        if (item.getU().is_vip()) {
            mName.setTextColor(Color.RED);
            mVip.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(R.raw.members_mark).asGif().into(mVip);
        } else {
            mName.setTextColor(Color.BLACK);
            mVip.setVisibility(View.GONE);
        }
        //判断头像是否带V
        if (!item.getU().is_v()) {
            mV.setVisibility(View.GONE);
        } else {
            mV.setVisibility(View.VISIBLE);
        }
        //加载圆形头像
        Glide.with(mContext).load(item.getU().getHeader().get(0)).bitmapTransform(new CropCircleTransformation(mContext)).into(mHeader);
        //设置name
        mName.setText(item.getU().getName());
        //设置发表时间
        mPasstime.setText(item.getPasstime());
        //设置标题text
        mText.setText(item.getText());
        //判断是否是video
        if (item.getVideo() != null) {
            //设置video预览图片
            mLayoutVideo.setVisibility(View.VISIBLE);
            ViewGroup.LayoutParams params = mLayoutVideo.getLayoutParams();
            params.height=item.getVideo().getHeight();
            ViewGroup.LayoutParams layoutParams = mThumbnail.getLayoutParams();
            layoutParams.height=item.getVideo().getHeight();
            layoutParams.width=ViewGroup.LayoutParams.MATCH_PARENT;
            Glide.with(mContext).load(item.getVideo().getThumbnail().get(0)).into(mThumbnail);
        } else {
            mLayoutVideo.setVisibility(View.GONE);
        }
        //判断是否是Gif
        if (item.getGif()!=null) {
            ViewGroup.LayoutParams layoutParams = mThumbnail.getLayoutParams();
            layoutParams.height=item.getGif().getHeight();
            layoutParams.width=ViewGroup.LayoutParams.MATCH_PARENT;
            Glide.with(mContext).load(item.getGif().getImages().get(0)).asGif().into(mThumbnail);
        }
        //判断是否是image
        if (item.getImage()!=null) {
            ViewGroup.LayoutParams layoutParams = mThumbnail.getLayoutParams();
            layoutParams.height=item.getImage().getHeight();
            layoutParams.width=ViewGroup.LayoutParams.MATCH_PARENT;
//            Glide.with(mContext).load(item.getImage().getMedium().get(0)).into(mThumbnail);
        }
      //
}
        }
