package com.leavessilent.mylibrary.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.leavessilent.mylibrary.MyLibrary;
import com.leavessilent.mylibrary.R;

/**
 * Created by Leavessilent on 2016/8/31.
 */
public class ImageHelper {

    public static void display(ImageView image, String url) {
        Glide.with(MyLibrary.getContext())
                .load(url)
                .placeholder(R.drawable.loading)
                .into(image);
    }

}
