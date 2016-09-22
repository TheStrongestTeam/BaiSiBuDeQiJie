package com.miraclehu.baisibudeqijie.ui.fragment.latest;


import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.leavessilent.mylibrary.MyLibrary;
import com.leavessilent.mylibrary.utils.HttpUtils;
import com.leavessilent.mylibrary.utils.ScreenHelper;
import com.miraclehu.baisibudeqijie.R;
import com.miraclehu.baisibudeqijie.adapter.latest.LastestAllAdapter;
import com.miraclehu.baisibudeqijie.common.HttpConstants;
import com.miraclehu.baisibudeqijie.model.VideoList;
import com.miraclehu.baisibudeqijie.model.VideoRoot;
import com.miraclehu.baisibudeqijie.ui.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.appsdream.nestrefresh.base.AbsRefreshLayout;
import cn.appsdream.nestrefresh.base.OnPullListener;
import cn.appsdream.nestrefresh.normalstyle.NestRefreshLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class LatestAllFragment extends BaseFragment implements OnPullListener, LastestAllAdapter.onItemClickListener, View.OnTouchListener {


    private static final String TAG = LatestAllFragment.class.getCanonicalName();
    @BindView(R.id.latest_all_lv)
    ListView mLatestAllLv;
    @BindView(R.id.latest_all_referesh)
    NestRefreshLayout mLatestAllReferesh;
    private LastestAllAdapter mAdapter;
    private long page = 0;
    private PopupWindow mPopupWindow;
    private View mVideoPopLayout;
    private ImageView mVideoPopThumbnail;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_lastest_all;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mLatestAllReferesh.setOnLoadingListener(this);
        mAdapter = new LastestAllAdapter(context, null, R.layout.lastest_all_item_1);
        mLatestAllLv.setAdapter(mAdapter);
        setupView(option.UP);
        mAdapter.setListener(this);
    }

    private void setupView(final option option) {
        HttpUtils.getAsynString(HttpConstants.LASTEST_ALL_BEGIN + page + HttpConstants.LASTEST_ALL_END, new HttpUtils.ResultCallback() {
            @Override
            public void onFailure(Exception e) {
            }

            @Override
            public void onFinish(String result) {
                Gson gson = new Gson();
                VideoRoot root = gson.fromJson(result, VideoRoot.class);
                page = root.getInfo().getNp();
                switch (option) {
                    case UP:
                        mAdapter.updateData(root.getList());
                        break;
                    case DOWM:
                        mAdapter.addData(root.getList());
                        break;
                }
                mLatestAllReferesh.onLoadFinished();
            }
        });
    }

    @Override
    public void onRefresh(AbsRefreshLayout listLoader) {
        page = 0;
        setupView(option.UP);
    }

    @Override
    public void onLoading(AbsRefreshLayout listLoader) {
        setupView(option.DOWM);
    }

    //--------------------接口回调函数---------------------------
    @Override
    public void onItemClick(View v, VideoList item) {
        switch (v.getId()) {
            case R.id.all_item_view_play:
                if (mPopupWindow == null) {
                    //弹出popupWindow
                    View pop = LayoutInflater.from(context).inflate(R.layout.video_pop, null);
                    mVideoPopLayout = pop.findViewById(R.id.video_pop_layout);
                    mVideoPopThumbnail = ((ImageView) pop.findViewById(R.id.video_pop_thumbnail));
                    this.mPopupWindow = new PopupWindow(pop);
                    DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                    int widthPixels = displayMetrics.widthPixels;
                    int heightPixels = displayMetrics.heightPixels;
                    mPopupWindow.setWidth(widthPixels);
                    mPopupWindow.setHeight(heightPixels);
                }
                popLoadData(v,item);
                mPopupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
                //设置点击监听
                mVideoPopLayout.setOnTouchListener(this);
                break;
        }
    }
//------------------啪啪啪加载数据--------------------------------------
    private void popLoadData(View view,VideoList item) {
        //获取view的宽和高 以及距离顶端的距离
        int height = view.getHeight();
        int width = view.getWidth();
        //获取view的坐标
        int [] location=new int[2];
        view.getLocationOnScreen(location);
        //获取屏幕高度
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        int height1 = display.getHeight();
        //获取状态栏高度
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        int  result = getResources().getDimensionPixelSize(resourceId);

        Log.e(TAG, "popLoadData: "+location[1]+"==="+height1+"==="+result);
        //设置要显示图片的view的宽和高
        ViewGroup.LayoutParams layoutParams = mVideoPopThumbnail.getLayoutParams();
        layoutParams.height=height;
        layoutParams.width=width;
        //设置顶端距离
        mVideoPopThumbnail.setY(location[1]-result);
//        ViewGroup.MarginLayoutParams mVideoPopThumbnailLayoutParams = (ViewGroup.MarginLayoutParams) mVideoPopThumbnail.getLayoutParams();
//        int px = ScreenHelper.dp2px(5);
//        mVideoPopThumbnailLayoutParams.setMargins(px,topMargin,px,bottomMargin);
        Glide.with(context).load(item.getVideo().getThumbnail().get(0)).into(mVideoPopThumbnail);
    }

    //-------------啪啪啪window点击监听回调--------------------------------
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.video_pop_layout:
                switch (event.getAction()) {
                    //滑动时关闭pupupWindow
                    case MotionEvent.ACTION_MOVE:
                        mPopupWindow.dismiss();
                        break;
                }
                break;
        }
        return false;
    }


    enum option {
        UP, DOWM
    }
}
