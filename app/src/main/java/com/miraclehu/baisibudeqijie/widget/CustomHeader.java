package com.miraclehu.baisibudeqijie.widget;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.miraclehu.baisibudeqijie.R;

import cn.appsdream.nestrefresh.base.AbsRefreshLayout;

/**
 * Created by hasee on 2016/9/21.
 */
public class CustomHeader extends LinearLayout implements AbsRefreshLayout.LoaderDecor {


    private static final int STATE_NORMAL = 0;
    private static final int STATE_RELEASE = 1;
    private static final int STATE_REFRESHING = 2;

    /**
     * 一分钟的毫秒值，用于判断上次的更新时间
     */
    public static final long ONE_MINUTE = 60 * 1000;

    /**
     * 一小时的毫秒值，用于判断上次的更新时间
     */
    public static final long ONE_HOUR = 60 * ONE_MINUTE;

    /**
     * 一天的毫秒值，用于判断上次的更新时间
     */
    public static final long ONE_DAY = 24 * ONE_HOUR;

    /**
     * 一月的毫秒值，用于判断上次的更新时间
     */
    public static final long ONE_MONTH = 30 * ONE_DAY;

    /**
     * 一年的毫秒值，用于判断上次的更新时间
     */
    public static final long ONE_YEAR = 12 * ONE_MONTH;

    /**
     * 上次更新时间的字符串常量，用于作为SharedPreferences的键值
     */
    private static final String UPDATED_AT = "updated_at";
    private static final String TAG = CustomHeader.class.getSimpleName();


    /**
     * 用于存储上次更新时间
     */
    private SharedPreferences preferences;

    private ImageView mImageView;
    private TextView mTipText;
    private TextView mTimeText;
    private long lastUpdateTime;

    public CustomHeader(Context context) {
        this(context, null);
    }

    public CustomHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        View header = LayoutInflater.from(context).inflate(R.layout.header_layout, this, true);
        mImageView = ((ImageView) header.findViewById(R.id.header_layout_image));
        mTimeText = ((TextView) header.findViewById(R.id.header_layout_tv_time));
        mTipText = ((TextView) header.findViewById(R.id.header_layout_tv_tip));
        refreshUpdateTime();
    }

    private void refreshUpdateTime() {
        lastUpdateTime = preferences.getLong(UPDATED_AT, -1);
        long currentTimeMillis = System.currentTimeMillis();
        long timePassed = currentTimeMillis - lastUpdateTime;
        long timeIntoFormat;
        String updateAtValue;
        if (lastUpdateTime == -1) {
            updateAtValue = getResources().getString(R.string.not_updated_yet);
        } else if (timePassed < ONE_HOUR) {
            timeIntoFormat = timePassed / ONE_MINUTE;
            String value = timeIntoFormat + "分钟";
            updateAtValue = String.format(getResources().getString(R.string.update_at), value);
        } else if (timePassed < ONE_DAY) {
            timeIntoFormat = timePassed / ONE_HOUR;
            String value = timeIntoFormat + "小时";
            updateAtValue = String.format(getResources().getString(R.string.update_at), value);
        } else if (timePassed < ONE_MONTH) {
            timeIntoFormat = timePassed / ONE_DAY;
            String value = timeIntoFormat + "天";
            updateAtValue = String.format(getResources().getString(R.string.update_at), value);
        } else if (timePassed < ONE_YEAR) {
            timeIntoFormat = timePassed / ONE_DAY;
            String value = timeIntoFormat + "个月";
            updateAtValue = String.format(getResources().getString(R.string.update_at), value);
        } else {
            timeIntoFormat = timePassed / ONE_MONTH;
            String value = timeIntoFormat + "年";
            updateAtValue = String.format(getResources().getString(R.string.update_at), value);
        }
        mTimeText.setText(updateAtValue);
    }

    @Override
    public void scrollRate(int y) {
    }

    @Override
    public void setState(int state) {
        refreshUpdateTime();
        switch (state) {
            case STATE_NORMAL:
                mImageView.setImageResource(R.mipmap.list_view_pull);
                mTipText.setText(R.string.str_normal);
                break;
            case STATE_RELEASE:
                mImageView.setImageResource(R.mipmap.list_view_release);
                mTipText.setText(R.string.str_release);
                break;
            case STATE_REFRESHING:
                mImageView.setImageResource(R.drawable.image_anim);
                mTipText.setText(R.string.str_refreshing);
                AnimationDrawable anim = (AnimationDrawable) mImageView.getDrawable();
                anim.start();
                preferences.edit().putLong(UPDATED_AT, System.currentTimeMillis()).commit();
                break;
        }
    }
}
