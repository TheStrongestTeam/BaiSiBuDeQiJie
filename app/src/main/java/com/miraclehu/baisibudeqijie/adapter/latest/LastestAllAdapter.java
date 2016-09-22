package com.miraclehu.baisibudeqijie.adapter.latest;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.leavessilent.mylibrary.adapters.SingleBaseAdapter;
import com.miraclehu.baisibudeqijie.R;
import com.miraclehu.baisibudeqijie.model.VideoList;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class LastestAllAdapter extends SingleBaseAdapter<VideoList> implements View.OnClickListener{
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
    private ImageView mPlay;
    private View mLayoutPlay;
    private CheckBox mCai;
    private CheckBox mDing;
    private TextView mShare;
    private TextView mCommen;
    private onItemClickListener listener;
    private View mViewPlay;
    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }

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
        mPlay = ((ImageView) holder.getView(R.id.all_item_play));
        mLayoutPlay = holder.getView(R.id.linearLayout);
        mCai = ((CheckBox) holder.getView(R.id.cai));
        mDing = ((CheckBox) holder.getView(R.id.ding));
        mShare = ((TextView) holder.getView(R.id.share));
        mCommen = ((TextView) holder.getView(R.id.common));
        mViewPlay = holder.getView(R.id.all_item_view_play);
        mViewPlay.setOnClickListener(this);
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
            mLayoutPlay.setVisibility(View.VISIBLE);
            mLayoutVideo.setVisibility(View.VISIBLE);
            int width = mThumbnail.getWidth();
            int imageHeight = item.getVideo().getHeight();
            int imageWidth = item.getVideo().getWidth();
            int height = width * imageHeight / imageWidth;
            ViewGroup.LayoutParams params = mLayoutVideo.getLayoutParams();
            params.height= height;
            ViewGroup.LayoutParams layoutParams = mThumbnail.getLayoutParams();
            layoutParams.height = height;
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            Glide.with(mContext).load(item.getVideo().getThumbnail().get(0)).into(mThumbnail);
            mViewPlay.setTag(R.id.position,getPosition());
        } else
        //判断是否是Gif
        if (item.getGif() != null) {
            mLayoutPlay.setVisibility(View.GONE);
            mLayoutVideo.setVisibility(View.VISIBLE);
            ViewGroup.LayoutParams params = mLayoutVideo.getLayoutParams();
            params.height=item.getGif().getHeight();
            ViewGroup.LayoutParams layoutParams = mThumbnail.getLayoutParams();
            layoutParams.height = item.getGif().getHeight();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            Glide.with(mContext).load(item.getGif().getImages().get(0)).asGif().into(mThumbnail);
        } else
        //判断是否是image
        if (item.getImage() != null) {
            mLayoutPlay.setVisibility(View.GONE);
            int width = mThumbnail.getWidth();
            int imageHeight = item.getImage().getHeight();
            int imageWidth = item.getImage().getWidth();
            int height = width * imageHeight / imageWidth;
            mLayoutVideo.setVisibility(View.VISIBLE);
            ViewGroup.LayoutParams params = mLayoutVideo.getLayoutParams();
            params.height= height;
            ViewGroup.LayoutParams layoutParams = mThumbnail.getLayoutParams();
            layoutParams.height = height;
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            if (item.getImage().getMedium().size() != 0) {
                Glide.with(mContext).load(item.getImage().getMedium().get(0)).into(mThumbnail);
            } else if (item.getImage().getBig().size() != 0) {
                Glide.with(mContext).load(item.getImage().getBig().get(0)).into(mThumbnail);
            }
        } else {
            mLayoutVideo.setVisibility(View.GONE);
        }
        //设置 踩 顶 分享 评论
        mCai.setText(String.valueOf(item.getDown()));
        mDing.setText(String.valueOf(item.getUp()));
        mShare.setText(String.valueOf(item.getForward()));
        mCommen.setText(String.valueOf(item.getComment()));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.all_item_view_play:
                listener.onItemClick(v,getItem(((int) v.getTag(R.id.position))));
                break;
        }
    }
    public interface onItemClickListener{
        void onItemClick(View v,VideoList item);
    }
}
