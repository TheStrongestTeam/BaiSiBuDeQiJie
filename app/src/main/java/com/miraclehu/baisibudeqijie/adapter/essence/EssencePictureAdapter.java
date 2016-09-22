package com.miraclehu.baisibudeqijie.adapter.essence;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.leavessilent.mylibrary.adapters.UniversalAdapter;
import com.leavessilent.mylibrary.utils.ScreenHelper;
import com.miraclehu.baisibudeqijie.R;
import com.miraclehu.baisibudeqijie.adapter.TopCommentAdapter;
import com.miraclehu.baisibudeqijie.model.VideoList;
import com.miraclehu.baisibudeqijie.widget.CommentListView;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by user on 2016/9/21.
 */
public class EssencePictureAdapter extends UniversalAdapter<VideoList> {

    private static final String TAG = EssencePictureAdapter.class.getSimpleName();
    private Context context;
    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

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
        CommentListView commentListView = (CommentListView) holder.getView(R.id.picture_item_lv_comment);
        commentListView.setSelector(new ColorDrawable(Color.TRANSPARENT));

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

        if (videoList.getImage() != null) {
            if (videoList.getImage().getHeight() > 4000) {

                ViewGroup.LayoutParams layoutParams = picture.getLayoutParams();
                layoutParams.height = ScreenHelper.dp2px(400);
                picture.setLayoutParams(layoutParams);
                fullpic.setVisibility(View.VISIBLE);
                picture.setScaleType(ImageView.ScaleType.CENTER_CROP);
                Glide.with(context).load(videoList.getImage().getMedium().get(0))
                        .placeholder(R.mipmap.post_loading_icon)
                        .into(picture);
            } else {
                ViewGroup.LayoutParams layoutParams = picture.getLayoutParams();
                Log.e(TAG, "onBindDataToView: " + picture.getWidth());
                layoutParams.height = picture.getWidth() * videoList.getImage().getHeight() / videoList.getImage().getWidth();
                picture.setLayoutParams(layoutParams);
                fullpic.setVisibility(View.GONE);
                picture.setScaleType(ImageView.ScaleType.FIT_XY);
                Glide.with(context).load(videoList.getImage().getMedium().get(0))
                        .placeholder(R.mipmap.post_loading_icon)
                        .into(picture);
            }
        }
        if (videoList.getGif() != null){
            ViewGroup.LayoutParams layoutParams = picture.getLayoutParams();
            layoutParams.height = picture.getWidth() * videoList.getGif().getHeight() / videoList.getGif().getWidth();
            picture.setLayoutParams(layoutParams);
            fullpic.setVisibility(View.GONE);
            picture.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(context).load(videoList.getGif().getImages().get(0))
                    .placeholder(R.mipmap.post_loading_icon)
                    .into(picture);
        }

        picture.setTag(R.id.picture_item_picture,position);
        picture.setOnClickListener(this);

        //隐藏loading图，需要再写
        loading.setVisibility(View.GONE);
        cai.setText(String.valueOf(videoList.getDown()));
        ding.setText(String.valueOf(videoList.getUp()));
        share.setText(String.valueOf(videoList.getForward()));
        common.setText(String.valueOf(videoList.getComment()));
        TopCommentAdapter adapter = new TopCommentAdapter(context, videoList.getTop_comments(), R.layout.top_comment_item);
        commentListView.setAdapter(adapter);
    }

    public interface OnItemClickListener{

        void onItemClick(View v,int position);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.picture_item_picture:
                listener.onItemClick(v,((int) v.getTag(R.id.picture_item_picture)));
                break;
        }


    }
}
