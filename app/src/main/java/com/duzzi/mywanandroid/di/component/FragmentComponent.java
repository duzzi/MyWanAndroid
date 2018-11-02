package com.duzzi.mywanandroid.di.component;

import com.duzzi.mywanandroid.di.module.AdapterModule;
import com.duzzi.mywanandroid.di.module.PresenterModule;
import com.duzzi.mywanandroid.di.scope.FragmentScope;
import com.duzzi.mywanandroid.ui.fragment.HierarchyArticleFragment;
import com.duzzi.mywanandroid.ui.fragment.HierarchyFragment;
import com.duzzi.mywanandroid.ui.fragment.HomeFragment;
import com.duzzi.mywanandroid.ui.fragment.NavigationFragment;
import com.duzzi.mywanandroid.ui.fragment.ProjectContainerFragment;
import com.duzzi.mywanandroid.ui.fragment.ProjectListFragment;

import dagger.Component;

/**
 * 文件名: ActivityComponent
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/23
 */
@FragmentScope
@Component(modules = {PresenterModule.class, AdapterModule.class})
public interface FragmentComponent {
    void inject(HomeFragment homeFragment);

    void inject(HierarchyFragment hierarchyFragment);

    void inject(HierarchyArticleFragment homeFragment);

    void inject(ProjectContainerFragment projectFragment);

    void inject(ProjectListFragment projectListFragment);

    void inject(NavigationFragment navigationFragment);

}
