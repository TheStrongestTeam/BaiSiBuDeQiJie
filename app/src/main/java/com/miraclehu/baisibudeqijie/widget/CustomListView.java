package com.miraclehu.baisibudeqijie.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by hasee on 2016/9/22.
 */
public class CustomListView extends ListView {
    private static final String TAG = CustomListView.class.getSimpleName();

    public CustomListView(Context context) {
        super(context);
    }

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec, MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE / 1000, MeasureSpec.AT_MOST));
    }
}
