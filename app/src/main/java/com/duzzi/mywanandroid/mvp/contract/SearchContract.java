package com.duzzi.mywanandroid.mvp.contract;

import com.duzzi.mywanandroid.base.presenter.IBasePresenter;
import com.duzzi.mywanandroid.base.view.IBaseView;
import com.duzzi.mywanandroid.core.bean.data.ArticleBean;

import java.util.List;

/**
 * 文件名: BannerContract
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/25
 */
public class SearchContract {
    public interface ISearchView extends IBaseView {
        void onSearchSuccess(boolean hasMore, List<ArticleBean> searchResultBeans);

        void onLoadMoreSuccess(boolean hasMore, List<ArticleBean> searchResultBeans);
    }

    public interface ISearchPresenter extends IBasePresenter<ISearchView> {
        void search(String k);

        void loadMore();
    }
}
