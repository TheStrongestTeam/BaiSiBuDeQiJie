package com.miraclehu.baisibudeqijie.adapter.essence;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.leavessilent.mylibrary.adapters.SingleBaseAdapter;
import com.miraclehu.baisibudeqijie.R;
import com.miraclehu.baisibudeqijie.model.U;
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

        String comment = name + ": " + content;
        String commentWithPrecmt = null;
        SpannableString string;

        TopComment precmt = item.getPrecmt();
        if (precmt != null) {
            U u = precmt.getU();
            String uName = u.getName();
            String precmtContent = precmt.getContent();
            commentWithPrecmt = "//@" + uName + ": " + precmtContent;
            string = new SpannableString(comment + commentWithPrecmt);

            // 评论名字颜色
            ForegroundColorSpan what01 = new ForegroundColorSpan(Color.parseColor("#e716a2ff"));
            string.setSpan(what01, 0, name.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            // 分隔线颜色
            ForegroundColorSpan what02 = new ForegroundColorSpan(Color.parseColor("#afaeae"));
            string.setSpan(what02, comment.length(), comment.length() + 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            // 被评论者名字颜色
            ForegroundColorSpan what03 = new ForegroundColorSpan(Color.parseColor("#e77cbeec"));
            string.setSpan(what03, comment.length() + 2, comment.length() + 2 + uName.length() + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            // 被评论内容
            ForegroundColorSpan what04 = new ForegroundColorSpan(Color.parseColor("#afaeae"));
            string.setSpan(what04, string.length() - precmtContent.length(), string.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            string = new SpannableString(comment);
            ForegroundColorSpan what = new ForegroundColorSpan(Color.parseColor("#e716a2ff"));
            string.setSpan(what, 0, name.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        textView.setText(string);

    }
}
