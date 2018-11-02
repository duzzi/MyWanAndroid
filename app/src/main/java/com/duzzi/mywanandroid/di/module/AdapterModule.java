package com.duzzi.mywanandroid.di.module;

import com.duzzi.mywanandroid.ui.adapter.rv.ArticleAdapter;
import com.duzzi.mywanandroid.ui.adapter.rv.HierarchyAdapter;
import com.duzzi.mywanandroid.ui.adapter.rv.NavigationAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * 文件名: PresenterModule
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/25
 */
@Module
public class AdapterModule {
    @Provides
    ArticleAdapter provideHomeAdapter() {
        return new ArticleAdapter();
    }

    @Provides
    HierarchyAdapter provideHierarchyAdapter() {
        return new HierarchyAdapter();
    }

    @Provides
    NavigationAdapter provideNavigationAdapter() {
        return new NavigationAdapter();
    }

}
