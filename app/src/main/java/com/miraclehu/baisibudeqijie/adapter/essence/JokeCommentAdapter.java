package com.miraclehu.baisibudeqijie.adapter.essence;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.leavessilent.mylibrary.adapters.SingleBaseAdapter;
import com.miraclehu.baisibudeqijie.R;
import com.miraclehu.baisibudeqijie.model.essence.TopComment;

import java.util.List;

/**
 * Created by hasee on 2016/9/22.
 */
public class JokeCommentAdapter extends SingleBaseAdapter<TopComment> {
    private final Context mContext;

    public JokeCommentAdapter(Context context, List<TopComment> data, int layoutId) {
        super(context, data, layoutId);
        mContext = context;
    }

    @Override
    protected void bindData(ViewHolder holder, TopComment item) {
        TextView textView = (TextView) holder.getView(R.id.joke_comment_item_text);
        String name = item.getU().getName();
        String content = item.getContent();
        SpannableString string = new SpannableString(name + ": " + content);
        ForegroundColorSpan what = new ForegroundColorSpan(Color.parseColor("#e716a2ff"));
        string.setSpan(what, 0, name.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(string);
    }
}
