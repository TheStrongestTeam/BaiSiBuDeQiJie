package com.miraclehu.baisibudeqijie.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by hasee on 2016/9/23.
 */
public class ToastUtil {
    private static Toast sToast;

    public static void makeText(Context context, CharSequence text) {
        if (sToast == null) {
            sToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
        sToast.setText(text);
        sToast.show();
    }
}
