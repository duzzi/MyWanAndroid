package com.duzzi.mywanandroid.mvp.contract;

import com.duzzi.mywanandroid.base.presenter.IBasePresenter;
import com.duzzi.mywanandroid.base.view.IBaseView;
import com.duzzi.mywanandroid.core.bean.data.ArticleBean;
import com.duzzi.mywanandroid.core.bean.data.ArticleListBean;

import java.util.List;

/**
 * 文件名: ArticleListContract
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/25
 */
public class ArticleListContract {
    public interface IArticleListView extends IBaseView {
        void onGetArticleListSuccess(boolean hasMore, List<ArticleBean> articleBeanList);

        void onLoadMoreArticleListSuccess(boolean hasMore, List<ArticleBean> articleBeanList);
    }

    public interface IArticleListPresenter extends IBasePresenter<IArticleListView> {
        void getArticleList();
        void loadMoreArticle();
    }
}
