package com.duzzi.mywanandroid;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.duzzi.mywanandroid.di.component.AppComponent;
import com.duzzi.mywanandroid.di.component.DaggerAppComponent;
import com.duzzi.mywanandroid.di.module.AppModule;
import com.duzzi.mywanandroid.ui.widget.loadsir.CustomCallback;
import com.duzzi.mywanandroid.ui.widget.loadsir.EmptyCallback;
import com.duzzi.mywanandroid.ui.widget.loadsir.ErrorCallback;
import com.duzzi.mywanandroid.ui.widget.loadsir.LoadingCallback;
import com.duzzi.mywanandroid.ui.widget.loadsir.TimeoutCallback;
import com.duzzi.mywanandroid.util.DLog;
import com.kingja.loadsir.core.LoadSir;


/**
 * 文件名: WanAndroidApp
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/22
 */
public class WanAndroidApp extends Application {
    private static WanAndroidApp instance;
    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        DLog.setDebuggable(BuildConfig.DEBUG);
        intLoadSir();
        instance = this;
        sAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();

    }

    private void intLoadSir() {
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();
    }

    public static synchronized WanAndroidApp getInstance() {
        return instance;
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }
}
