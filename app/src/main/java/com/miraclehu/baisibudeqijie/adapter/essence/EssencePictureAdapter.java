package com.miraclehu.baisibudeqijie.adapter.essence;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.leavessilent.mylibrary.adapters.UniversalAdapter;
import com.miraclehu.baisibudeqijie.R;
import com.miraclehu.baisibudeqijie.model.VideoList;

import java.util.List;

/**
 * Created by user on 2016/9/21.
 */
public class EssencePictureAdapter extends UniversalAdapter<VideoList> {

    public EssencePictureAdapter(Context context, List<VideoList> data) {
        super(context, data);
    }

    public EssencePictureAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemLayoutID(int viewType) {
        return 0;
    }

    @Override
    protected void onBindDataToView(RecyclerViewHolder holder, VideoList videoList, int position) {
        ImageView head_image = holder.getImageView(R.id.picture_item_img);
        ImageView v = holder.getImageView(R.id.picture_item_v);
        TextView name = holder.getTextView(R.id.picture_item_name);
    }
}
