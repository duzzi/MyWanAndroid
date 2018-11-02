package com.duzzi.mywanandroid.di.component;

import com.duzzi.mywanandroid.WanAndroidApp;
import com.duzzi.mywanandroid.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 文件名: AppComponent
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/23
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    /**
     * 注入WanAndroidApp实例
     *
     * @param wanAndroidApp WanAndroidApp
     */
    void inject(WanAndroidApp wanAndroidApp);

//    /**
//     * 提供App的Context
//     *
//     * @return GeeksApp context
//     */
//    WanAndroidApp getContext();
//
//    /**
//     * 数据中心
//     *
//     * @return DataManager
//     */
//    DataManager getDataManager();
}
