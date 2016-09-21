package com.miraclehu.baisibudeqijie.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.leavessilent.mylibrary.adapters.SingleBaseAdapter;
import com.miraclehu.baisibudeqijie.R;
import com.miraclehu.baisibudeqijie.model.Commenter;

import java.util.List;

/**
 * Created by user on 2016/9/21.
 */
public class TopCommentAdapter extends SingleBaseAdapter<Commenter>{

    private Context context;
    public TopCommentAdapter(Context context, List<Commenter> data, int layoutId) {
        super(context, data, layoutId);
        this.context = context;
    }

    @Override
    protected void bindData(ViewHolder holder, final Commenter item) {
        TextView view = (TextView) holder.getView(R.id.top_comment_content);
        SpannableString spannableString = new SpannableString(item.getU().getName());
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);
            }

            @Override
            public void onClick(View widget) {
                Toast.makeText(context, item.getU().getName(), Toast.LENGTH_SHORT).show();
            }
        },0,item.getU().getName().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        view.setText(spannableString);
        view.append(":" + item.getContent());
    }
}
