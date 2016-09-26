package com.miraclehu.baisibudeqijie.adapter.essence;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.leavessilent.mylibrary.adapters.UniversalAdapter;
import com.leavessilent.mylibrary.utils.ImageHelper;
import com.leavessilent.mylibrary.utils.ScreenHelper;
import com.miraclehu.baisibudeqijie.R;
import com.miraclehu.baisibudeqijie.model.U;
import com.miraclehu.baisibudeqijie.model.Video;
import com.miraclehu.baisibudeqijie.model.VideoList;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by hasee on 2016/9/23.
 */
public class EssenceVideoAdapter extends UniversalAdapter<VideoList> {
    private static final String TAG = EssenceVideoAdapter.class.getSimpleName();
    private Context mContext;
    private final int mMaxWidth;
    private final int mMaxHeight;


    public EssenceVideoAdapter(Context context, List<VideoList> data) {
        super(context, data);
        mContext = context;
        // 减去两边的margin值
        mMaxWidth = ScreenHelper.getScreenWidth() - ScreenHelper.dp2px(30);
        mMaxHeight = ScreenHelper.getScreenHeight() * 3 / 5;
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
        preview.setVisibility(View.VISIBLE);


        SurfaceView surfaceView = (SurfaceView) holder.getView(R.id.essence_video_item_surface_view);
        View pb = holder.getView(R.id.essence_video_item_pb);
        pb.setVisibility(View.GONE);
        ImageView play = holder.getImageView(R.id.essence_video_item_play);
        play.setVisibility(View.VISIBLE);

        TextView down = holder.getTextView(R.id.cai);
        TextView up = holder.getTextView(R.id.ding);
        TextView forward = holder.getTextView(R.id.share);
        TextView comment = holder.getTextView(R.id.common);

        View layout = holder.getView(R.id.layout);
        layout.setTag(position);
        Video video = videoList.getVideo();
        // 缩放比
        float widthScale = (float) mMaxWidth / video.getWidth();
        float heightScale = (float) mMaxHeight / video.getHeight();
        ViewGroup.LayoutParams params = layout.getLayoutParams();
        if (widthScale < heightScale) {
            params.width = mMaxWidth;
            params.height = ((int) (widthScale * video.getHeight() + 0.5f));
        } else {
            params.height = mMaxHeight;
            params.width = ((int) (heightScale * video.getWidth() + 0.5f));
        }
        layout.setLayoutParams(params);

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

        ImageHelper.display(preview, video.getThumbnail().get(0));

        up.setText(" " + String.valueOf(videoList.getUp()));
        down.setText(" " + String.valueOf(videoList.getDown()));
        forward.setText(" " + String.valueOf(videoList.getForward()));
        comment.setText(" " + String.valueOf(videoList.getComment()));
    }

}
