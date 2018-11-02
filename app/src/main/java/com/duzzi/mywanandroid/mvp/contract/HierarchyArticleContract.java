package com.duzzi.mywanandroid.mvp.contract;

import com.duzzi.mywanandroid.base.presenter.IBasePresenter;
import com.duzzi.mywanandroid.base.view.IBaseView;
import com.duzzi.mywanandroid.core.bean.data.ArticleBean;

import java.util.List;

/**
 * 文件名: HierarchyArticleContract
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/25
 */
public class HierarchyArticleContract {
    public interface IHierarchyArticleView extends IBaseView {
        void onGetHierarchyArticleSuccess(boolean hasMore, List<ArticleBean> hierarchyArticleBeans);

        void onLoadMoreHierarchyArticleSuccess(boolean hasMore, List<ArticleBean> hierarchyArticleBeans);
    }

    public interface IHierarchyArticlePresenter extends IBasePresenter<IHierarchyArticleView> {
        void getHierarchyArticle(int cid);

        void loadMoreHierarchyArticle();
    }
}
