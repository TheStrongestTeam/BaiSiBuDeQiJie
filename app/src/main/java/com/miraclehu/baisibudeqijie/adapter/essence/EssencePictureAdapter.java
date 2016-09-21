package com.miraclehu.baisibudeqijie.adapter.essence;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.leavessilent.mylibrary.adapters.UniversalAdapter;
import com.miraclehu.baisibudeqijie.R;
import com.miraclehu.baisibudeqijie.model.VideoList;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by user on 2016/9/21.
 */
public class EssencePictureAdapter extends UniversalAdapter<VideoList> {

    private static final String TAG = EssencePictureAdapter.class.getSimpleName();
    private Context context;
    public EssencePictureAdapter(Context context, List<VideoList> data) {
        super(context, data);
        this.context = context;
    }

    public EssencePictureAdapter(Context context) {
        this(context,null);
    }

    @Override
    public int getItemLayoutID(int viewType) {
        return R.layout.picture_item;
    }

    @Override
    protected void onBindDataToView(RecyclerViewHolder holder, VideoList videoList, int position) {
        ImageView head_image = holder.getImageView(R.id.picture_item_img);
        ImageView v = holder.getImageView(R.id.picture_item_v);
        TextView name = holder.getTextView(R.id.picture_item_name);
        TextView data = holder.getTextView(R.id.picture_item_data);
        TextView content = holder.getTextView(R.id.picture_item_content);
        ImageView picture = holder.getImageView(R.id.picture_item_picture);
        ImageView loading = holder.getImageView(R.id.picture_item_loading);
        TextView fullpic = holder.getTextView(R.id.picture_item_fullpic);
        CheckBox cai = (CheckBox) holder.getView(R.id.cai);
        CheckBox ding = (CheckBox) holder.getView(R.id.ding);
        TextView share = holder.getTextView(R.id.share);
        TextView common = holder.getTextView(R.id.common);

        Glide.with(context).load(videoList.getU().getHeader().get(0))
                .placeholder(R.mipmap.ic_launcher)
                .bitmapTransform(new CropCircleTransformation(context))
                .into(head_image);
        if (videoList.getU().is_v()){
            v.setVisibility(View.VISIBLE);
        }else {
            v.setVisibility(View.GONE);
        }

        name.setText(videoList.getU().getName());
        data.setText(videoList.getPasstime());
        content.setText(videoList.getText());

        Log.e(TAG, "onBindDataToView:       "+ videoList.getImage().getHeight() );
        if (videoList.getImage() != null) {
            if (videoList.getImage().getHeight() > 5000) {

                ViewGroup.LayoutParams layoutParams = picture.getLayoutParams();
                layoutParams.height = 400;
                picture.setLayoutParams(layoutParams);
                fullpic.setVisibility(View.VISIBLE);
            } else {
                ViewGroup.LayoutParams layoutParams = picture.getLayoutParams();
                layoutParams.height = picture.getWidth() * videoList.getImage().getHeight() / videoList.getImage().getWidth();
                picture.setLayoutParams(layoutParams);
                fullpic.setVisibility(View.GONE);
            }
        }
        //隐藏loading图，需要再写
        loading.setVisibility(View.GONE);
        cai.setText(String.valueOf(videoList.getDown()));
        ding.setText(String.valueOf(videoList.getUp()));
        share.setText(String.valueOf(videoList.getForward()));
        common.setText(String.valueOf(videoList.getComment()));

    }
}
