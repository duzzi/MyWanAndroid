package com.duzzi.mywanandroid.mvp.presenter;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.Utils;
import com.duzzi.mywanandroid.R;
import com.duzzi.mywanandroid.base.presenter.BasePresenter;
import com.duzzi.mywanandroid.core.DataManager;
import com.duzzi.mywanandroid.core.bean.data.SearchResultsBean;
import com.duzzi.mywanandroid.mvp.contract.SearchContract;
import com.duzzi.mywanandroid.util.CollectionUtils;
import com.duzzi.mywanandroid.util.RxUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * 文件名: SearchPresenter
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/30
 */
public class SearchPresenter extends BasePresenter<SearchContract.ISearchView> implements SearchContract.ISearchPresenter {

    private String mKey;

    @Inject
    public SearchPresenter() {
    }

    @Override
    public void search(String k) {
        if (StringUtils.isTrimEmpty(k)) {
            if (isViewAttached()) {
                getView().showToast(Utils.getApp().getString(R.string.please_input_search_key));
            }
            return;
        }
        mKey = k;
        resetCurrentPage();
        searchInternal(false, k);
    }

    private void searchInternal(boolean loadMore, String k) {
        addSubscribe(DataManager.getInstance().search(getCurrentPage(), k)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribe(new Consumer<SearchResultsBean>() {
                    @Override
                    public void accept(SearchResultsBean searchResultsBean) throws Exception {
                        if (isViewAttached()) {
                            boolean hasMore = !searchResultsBean.isOver();
                            pageIncrease();
                            if (loadMore) {
                                getView().onLoadMoreSuccess(hasMore, searchResultsBean.getDatas());
                            } else {
                                if (CollectionUtils.isEmpty(searchResultsBean.getDatas())) {
                                    getView().showEmptyView();
                                } else {
                                    getView().onSearchSuccess(hasMore, searchResultsBean.getDatas());
                                }
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
    public void loadMore() {
        searchInternal(true, mKey);
    }
}
