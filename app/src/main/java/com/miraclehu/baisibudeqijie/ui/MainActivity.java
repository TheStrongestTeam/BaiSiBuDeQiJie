package com.miraclehu.baisibudeqijie.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.miraclehu.baisibudeqijie.R;
import com.miraclehu.baisibudeqijie.ui.fragment.AttentionFragment;
import com.miraclehu.baisibudeqijie.ui.fragment.essence.EssenceFragment;
import com.miraclehu.baisibudeqijie.ui.fragment.latest.LatestFragment;
import com.miraclehu.baisibudeqijie.ui.fragment.MyFragment;
import com.miraclehu.baisibudeqijie.util.PlayerManager;

import java.lang.reflect.InvocationTargetException;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.container)
    FrameLayout mContainer;
    @BindView(R.id.controller)
    RadioGroup mController;
    @BindView(R.id.controller_writepost)
    ImageView mWritepost;
    private Fragment mShowFragment;

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mController.setOnCheckedChangeListener(this);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        mShowFragment = new EssenceFragment();
        transaction.add(R.id.container,mShowFragment,EssenceFragment.TAG);
        transaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PlayerManager.release();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.controller_essence:
                switchPage(EssenceFragment.TAG,EssenceFragment.class);
                break;
            case R.id.controller_latest:
                switchPage(LatestFragment.TAG,LatestFragment.class);
                break;
            case R.id.controller_attention:
                switchPage(AttentionFragment.TAG,AttentionFragment.class);
                break;
            case R.id.controller_my:
                switchPage(MyFragment.TAG,MyFragment.class);
                break;
        }
    }

    public void switchPage(String tag, Class<? extends Fragment> cls) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.hide(mShowFragment);
        mShowFragment = fragmentManager.findFragmentByTag(tag);
        if (mShowFragment != null) {
            transaction.show(mShowFragment);
        } else {
            try {
                mShowFragment = cls.getConstructor().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            transaction.add(R.id.container, mShowFragment, tag);
        }
        transaction.commit();
    }

    @OnClick(R.id.controller_writepost)
    public void onClick() {
    }
}
