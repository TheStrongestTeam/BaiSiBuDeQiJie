package com.miraclehu.baisibudeqijie.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by user on 2016/9/21.
 */
public class CommentListView extends ListView {
    public CommentListView(Context context) {
        this(context, null);
    }

    public CommentListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommentListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
