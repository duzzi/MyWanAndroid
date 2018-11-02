package com.duzzi.mywanandroid.mvp.presenter;


import com.duzzi.mywanandroid.base.presenter.BasePresenter;
import com.duzzi.mywanandroid.core.DataManager;
import com.duzzi.mywanandroid.core.bean.data.ArticleListBean;
import com.duzzi.mywanandroid.mvp.contract.ArticleListContract;
import com.duzzi.mywanandroid.util.RxUtils;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * 文件名: ArticleListPresenter
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/26
 */
public class ArticleListPresenter extends BasePresenter<ArticleListContract.IArticleListView>
        implements ArticleListContract.IArticleListPresenter {

    @Inject
    public ArticleListPresenter() {
    }

    @Override
    public void getArticleList() {
        resetCurrentPage();
        getArticleListInternal(getCurrentPage());
    }

    private void getArticleListInternal(int pageIndex) {
        addSubscribe(DataManager.getInstance().getHomeArticle(pageIndex)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribe(new Consumer<ArticleListBean>() {
                    @Override
                    public void accept(ArticleListBean articleListBean) throws Exception {
                        if (isViewAttached()) {
                            boolean hasMore = !articleListBean.isOver();
                            pageIncrease();
                            if (pageIndex == 0) {
                                getView().onGetArticleListSuccess(hasMore,
                                        articleListBean.getDatas());
                            } else {
                                getView().onLoadMoreArticleListSuccess(hasMore,
                                        articleListBean.getDatas());
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (isViewAttached()) {
                            getView().showError();
                        }
                    }
                }));
    }

    @Override
    public void loadMoreArticle() {
        getArticleListInternal(getCurrentPage());
    }


}
