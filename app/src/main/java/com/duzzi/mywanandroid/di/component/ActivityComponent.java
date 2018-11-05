package com.duzzi.mywanandroid.di.component;

import com.duzzi.mywanandroid.di.module.ActivityModule;
import com.duzzi.mywanandroid.di.module.PresenterModule;
import com.duzzi.mywanandroid.di.scope.ActivityScope;
import com.duzzi.mywanandroid.ui.activity.CommonWebsiteActivity;
import com.duzzi.mywanandroid.ui.activity.HierarchyActivity;
import com.duzzi.mywanandroid.ui.activity.LoginActivity;
import com.duzzi.mywanandroid.ui.activity.RegisterActivity;
import com.duzzi.mywanandroid.ui.activity.SearchActivity;
import com.duzzi.mywanandroid.ui.activity.SettingsActivity;
import com.duzzi.mywanandroid.ui.activity.SplashActivity;

import dagger.Component;

/**
 * 文件名: ActivityComponent
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/23
 */
@ActivityScope
@Component(modules = {PresenterModule.class, ActivityModule.class
        /*, AndroidSupportInjectionModule.class*/})
public interface ActivityComponent {
    void inject(SplashActivity activity);

    void inject(HierarchyActivity activity);

    void inject(CommonWebsiteActivity commonWebsiteActivity);

    void inject(SearchActivity searchActivity);

    void inject(LoginActivity loginActivity);

    void inject(RegisterActivity registerActivity);

    void inject(SettingsActivity settingsActivity);
}
