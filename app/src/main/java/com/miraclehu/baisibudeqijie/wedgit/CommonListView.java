package com.miraclehu.baisibudeqijie.wedgit;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by user on 2016/9/21.
 */
public class CommonListView extends ListView {
    public CommonListView(Context context) {
        this(context,null);
    }

    public CommonListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CommonListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}
