package com.leavessilent.mylibrary.utils;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Leavessilent on 2016/9/6.
 */
public class HttpUtils {
    private static final String TAG = HttpUtils.class.getSimpleName();
    private Handler mHandler;
    private static HttpUtils sHttpUtils;
    private OkHttpClient mOkHttpClient;

    private HttpUtils() {
        mOkHttpClient = new OkHttpClient();
        mHandler = new Handler(Looper.getMainLooper());
    }

    private static HttpUtils getInstance() {
        if (sHttpUtils == null) {
            synchronized (HttpUtils.class) {
                sHttpUtils = new HttpUtils();
            }
        }
        return sHttpUtils;
    }

    private void _getAsynString(String url, final ResultCallback callback) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                sendError(e, callback);
            }

            @Override
            public void onResponse(final Call call, final Response response) {
                sendSuccess(response, callback);
            }
        });
    }

    /**
     * 将map集合转换成okhttp需要的FormBody格式
     *
     * @param params
     * @return
     */
    private FormBody map2FormBody(HashMap<String, String> params) {
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : params.keySet()) {
            builder.add(key, params.get(key));
        }
        return builder.build();
    }

    private void _postAsynString(String url, HashMap<String, String> params, final ResultCallback callback) {

        Request request = new Request.Builder()
                .post(map2FormBody(params))
                .url(url)
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                sendError(e, callback);
            }

            @Override
            public void onResponse(final Call call, final Response response) throws IOException {
                sendSuccess(response, callback);
            }
        });
    }


    private void sendError(final Exception e, final ResultCallback callback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onFailure(e);
            }
        });
    }

    private void sendSuccess(final Response response, final ResultCallback callback) {
        if (response.isSuccessful()) {
            final String result;
            try {
                result = response.body().string();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onFinish(result);
                    }
                });
            } catch (final IOException e) {
                sendError(e, callback);
            }
        } else {
            sendError(null, callback);
        }
    }


    // --------------------对外界提供的方法-----------------------------------------

    /**
     * get方法获取字符串数据
     *
     * @param url
     * @param callback
     */
    public static void getAsynString(String url, final ResultCallback callback) {
        getInstance()._getAsynString(url, callback);
    }

    /**
     * post请求获取字符串类型数据
     *
     * @param url
     * @param params
     * @param callback
     */
    public static void postAsynString(String url, HashMap<String, String> params, ResultCallback callback) {
        getInstance()._postAsynString(url, params, callback);
    }

    /**
     * 自定义一个接口回调返回的结果。
     */
    public interface ResultCallback {
        void onFailure(Exception e);

        void onFinish(String result);
    }

}
