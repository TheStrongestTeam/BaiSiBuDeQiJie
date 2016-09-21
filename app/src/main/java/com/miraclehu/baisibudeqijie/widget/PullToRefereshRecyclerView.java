package com.miraclehu.baisibudeqijie.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.miraclehu.baisibudeqijie.R;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class PullToRefereshRecyclerView extends PullToRefreshBase<RecyclerView> {
    public PullToRefereshRecyclerView(Context context) {
        super(context);
    }

    public PullToRefereshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToRefereshRecyclerView(Context context, Mode mode) {
        super(context, mode);
    }

    public PullToRefereshRecyclerView(Context context, Mode mode, AnimationStyle animStyle) {
        super(context, mode, animStyle);
    }

    @Override
    public Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    @Override
    protected RecyclerView createRefreshableView(Context context, AttributeSet attrs) {
        RecyclerView recyclerView = new RecyclerView(context, attrs);
        recyclerView.setId(R.id.recycler);
        return recyclerView;
    }
    @Override
    protected boolean isReadyForPullStart() {
//        RecyclerView refreshableView = getRefreshableView();
//        View child = refreshableView.getChildAt(0);
//        int paddingTop = refreshableView.getPaddingTop();
//        MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
//        int topMargin = layoutParams.topMargin;
//        int top = child.getTop();
        View view = getRefreshableView().getChildAt(0);
        if (view!=null) {
            return getRefreshableView().getTop()>=view.getTop();
        }
        return false;
    }
    @Override
    protected boolean isReadyForPullEnd() {
//        RecyclerView refreshableView = getRefreshableView();
//        int childCount = refreshableView.getChildCount();
//        View child = refreshableView.getChildAt(childCount - 1);
//        int height = refreshableView.getHeight();
//        int paddingBottom = refreshableView.getPaddingBottom();
//        MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
//        int bottomMargin = layoutParams.bottomMargin;
//        int childBottom = child.getBottom();
//
//        int itemCount = refreshableView.getAdapter().getItemCount();
//        int childAdapterPosition = refreshableView.getChildAdapterPosition(child);
//        return height==paddingBottom+childBottom+bottomMargin&&itemCount==childAdapterPosition+1;
        View view = getRefreshableView().getChildAt(getRefreshableView().getChildCount() - 1);
        if (view!=null) {
            return getRefreshableView().getBottom()>=view.getBottom();
        }
        return  false;
    }

    
}
