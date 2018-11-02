package com.duzzi.mywanandroid.core.http;

import com.duzzi.mywanandroid.core.constant.Constants;
import com.duzzi.mywanandroid.util.MyHttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 文件名: HttpHelper
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/25
 */
public class HttpHelper {
    private static HttpHelper sHttpHelper;
    private static WanAndroidService sWanAndroidService;
    private static OkHttpClient sHttpClient;


    private static MyHttpLoggingInterceptor httpLoggingInterceptor =
            new MyHttpLoggingInterceptor(HttpHelper.class.getSimpleName());
    private Retrofit mWanAndroidRetrofit;

    private HttpHelper() {
    }

    private static OkHttpClient getOkHttpClient() {
        if (sHttpClient == null) {
            synchronized (HttpHelper.class) {
                if (sHttpClient == null) {
                    httpLoggingInterceptor.setLevel(MyHttpLoggingInterceptor.Level.BODY);
                    sHttpClient = new OkHttpClient.Builder()
                            .addInterceptor(httpLoggingInterceptor)
                            .connectTimeout(Constants.DEFAULT_TIME_OUT, TimeUnit.MILLISECONDS)
                            .readTimeout(Constants.DEFAULT_TIME_OUT, TimeUnit.MILLISECONDS)
                            .writeTimeout(Constants.DEFAULT_TIME_OUT, TimeUnit.MILLISECONDS)
                            .build();
                }
            }
        }
        return sHttpClient;
    }

    public static HttpHelper getInstance() {
        if (sHttpHelper == null) {
            synchronized (HttpHelper.class) {
                if (sHttpHelper == null) {
                    sHttpHelper = new HttpHelper();
                }
            }
        }
        return sHttpHelper;
    }

    public WanAndroidService getWanAndroidService() {
        if (sWanAndroidService == null) {
            sWanAndroidService = getWanAndroidRetrofit().create(WanAndroidService.class);
        }
        return sWanAndroidService;
    }

    private Retrofit getWanAndroidRetrofit() {
        if (mWanAndroidRetrofit == null) {
            mWanAndroidRetrofit = new Retrofit.Builder().baseUrl(Constants.HOST_WANANDROID)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getOkHttpClient())
                    .build();
        }
        return mWanAndroidRetrofit;
    }
}
