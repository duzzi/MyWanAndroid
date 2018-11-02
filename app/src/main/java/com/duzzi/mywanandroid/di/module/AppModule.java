package com.duzzi.mywanandroid.di.module;

import com.duzzi.mywanandroid.WanAndroidApp;

import dagger.Module;

/**
 * 文件名: AppModule
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/23
 */
@Module
public class AppModule {
    private final WanAndroidApp application;

    public AppModule(WanAndroidApp application) {
        this.application = application;
    }

//    @Provides
//    @Singleton
//    WanAndroidApp provideApplicationContext() {
//        return application;
//    }


//    @Provides
//    @Singleton
//    HttpHelper provideHttpHelper(HttpHelperImpl httpHelperImpl) {
//        return httpHelperImpl;
//    }
//
//    @Provides
//    @Singleton
//    DbHelper provideDBHelper(DbHelperImpl realmHelper) {
//        return realmHelper;
//    }
//
//    @Provides
//    @Singleton
//    IAcacheHelper providePreferencesHelper(PreferenceHelperImpl implPreferencesHelper) {
//        return implPreferencesHelper;
//    }
//
//    @Provides
//    @Singleton
//    DataManager provideDataManager(HttpHelper httpHelper, DbHelper dbhelper, IAcacheHelper preferencesHelper) {
//        return new DataManager(httpHelper, dbhelper, preferencesHelper);
//    }

}
