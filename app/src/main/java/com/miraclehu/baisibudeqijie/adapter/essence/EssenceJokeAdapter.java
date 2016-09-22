package com.miraclehu.baisibudeqijie.adapter.essence;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.leavessilent.mylibrary.adapters.UniversalAdapter;
import com.miraclehu.baisibudeqijie.R;
import com.miraclehu.baisibudeqijie.model.U;
import com.miraclehu.baisibudeqijie.model.essence.EssenceJoke;
import com.miraclehu.baisibudeqijie.model.essence.TopComment;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by hasee on 2016/9/22.
 */
public class EssenceJokeAdapter extends UniversalAdapter<EssenceJoke> {

    private static final String TAG = EssenceJokeAdapter.class.getSimpleName();
    private Context mContext;

    public EssenceJokeAdapter(Context context, List<EssenceJoke> data) {
        super(context, data);
        mContext = context;
    }

    public EssenceJokeAdapter(Context context) {
        this(context, null);
    }

    @Override
    public int getItemLayoutID(int viewType) {
        return R.layout.essence_joke_item;
    }

    @Override
    protected void onBindDataToView(RecyclerViewHolder holder, EssenceJoke essenceJoke, int position) {

        // 先第一个将其渲染出来
        TextView content = holder.getTextView(R.id.essence_joke_item_content);
        content.setText(essenceJoke.getText());

        ImageView icon = holder.getImageView(R.id.essence_joke_item_icon);
        TextView name = holder.getTextView(R.id.essence_joke_item_name);
        TextView time = holder.getTextView(R.id.essence_joke_item_time);
        //全文
        TextView fullText = holder.getTextView(R.id.essence_joke_item_full_text);
        fullText.setTag(position);
        fullText.setOnClickListener(this);
        TextView up = holder.getTextView(R.id.cai);
        TextView down = holder.getTextView(R.id.ding);
        TextView forward = holder.getTextView(R.id.share);
        TextView comment = holder.getTextView(R.id.common);

        //评论的ListView
        ListView listView = (ListView) holder.getView(R.id.essence_joke_item_lv);


        U u = essenceJoke.getU();
        CropCircleTransformation transformation = new CropCircleTransformation(mContext);
        Glide.with(mContext).load(u.getHeader().get(0)).bitmapTransform(transformation).into(icon);
        name.setText(u.getName());
        String passtime = essenceJoke.getPasstime();
        time.setText(passtime.substring(0, passtime.length() - 3));
        up.setText(" " + String.valueOf(essenceJoke.getUp()));
        down.setText(" " + String.valueOf(essenceJoke.getDown()));
        forward.setText(" " + String.valueOf(essenceJoke.getForward()));
        comment.setText(" " + String.valueOf(essenceJoke.getComment()));

        //判断是否显示评论的listview，为其设置适配器
        List<TopComment> commentList = essenceJoke.getTop_comments();
        if (commentList != null) {
            listView.setVisibility(View.VISIBLE);
            JokeCommentAdapter adapter = new JokeCommentAdapter(mContext, commentList, R.layout.joke_comment_item);
            listView.setAdapter(adapter);
        } else {
            listView.setVisibility(View.GONE);
        }

        // 判断全文选项是否显示
        int lineCount = content.getLineCount();

        if (lineCount > 7) {
            fullText.setVisibility(View.VISIBLE);
        } else {
            fullText.setVisibility(View.GONE);
        }

        if (essenceJoke.isFullText()) {
            content.setMaxLines(lineCount);
            fullText.setText("收起");
        } else {
            content.setMaxLines(7);
            fullText.setText("全文");
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.essence_joke_item_full_text:
                int postion = (Integer) v.getTag();
                EssenceJoke item = getItem(postion);
                item.setFullText(!item.isFullText());
                notifyDataSetChanged();
                break;
        }
    }
}
