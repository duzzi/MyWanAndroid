package com.duzzi.mywanandroid.di.module;

import com.duzzi.mywanandroid.mvp.presenter.ArticleListPresenter;
import com.duzzi.mywanandroid.mvp.presenter.BannerPresenter;
import com.duzzi.mywanandroid.mvp.presenter.CommonWebsitePresenter;
import com.duzzi.mywanandroid.mvp.presenter.HierarchyArticlePresenter;
import com.duzzi.mywanandroid.mvp.presenter.HotKeyPresenter;
import com.duzzi.mywanandroid.mvp.presenter.KnowledgeHierarchyPresenter;
import com.duzzi.mywanandroid.mvp.presenter.LoginPresenter;
import com.duzzi.mywanandroid.mvp.presenter.NavigationPresenter;
import com.duzzi.mywanandroid.mvp.presenter.ProjectCategoryPresenter;
import com.duzzi.mywanandroid.mvp.presenter.ProjectListPresenter;
import com.duzzi.mywanandroid.mvp.presenter.RegisterPresenter;
import com.duzzi.mywanandroid.mvp.presenter.SearchPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * 文件名: PresenterModule
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/25
 */
@Module
public class PresenterModule {
    @Provides
    BannerPresenter provideBannerPresenter() {
        return new BannerPresenter();
    }

    @Provides
    ArticleListPresenter provideArticleListPresenter() {
        return new ArticleListPresenter();
    }

    @Provides
    KnowledgeHierarchyPresenter provideKnowledgeHierarchyPresenter() {
        return new KnowledgeHierarchyPresenter();
    }

    @Provides
    HierarchyArticlePresenter provideHierarchyArticlePresenter() {
        return new HierarchyArticlePresenter();
    }

    @Provides
    CommonWebsitePresenter provideCommonWebsitePresenter() {
        return new CommonWebsitePresenter();
    }

    @Provides
    HotKeyPresenter provideHotKeyPresenter() {
        return new HotKeyPresenter();
    }

    @Provides
    SearchPresenter provideSearchPresenter() {
        return new SearchPresenter();
    }

    @Provides
    ProjectCategoryPresenter provideProjectCategoryPresenter() {
        return new ProjectCategoryPresenter();
    }

    @Provides
    ProjectListPresenter provideProjectPresenter() {
        return new ProjectListPresenter();
    }

    @Provides
    NavigationPresenter provideNavigationPresenter() {
        return new NavigationPresenter();
    }

    @Provides
    LoginPresenter provideLoginPresenter() {
        return new LoginPresenter();
    }

    @Provides
    RegisterPresenter provideRegisterPresenter() {
        return new RegisterPresenter();
    }
}
