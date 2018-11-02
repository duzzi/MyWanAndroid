package com.duzzi.mywanandroid.mvp.presenter;

import com.duzzi.mywanandroid.base.presenter.BasePresenter;
import com.duzzi.mywanandroid.core.DataManager;
import com.duzzi.mywanandroid.core.bean.data.CommonWebsiteBean;
import com.duzzi.mywanandroid.mvp.contract.CommonWebsiteContract;
import com.duzzi.mywanandroid.util.CollectionUtils;
import com.duzzi.mywanandroid.util.RxUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * 文件名: CommonWebsitePresenter
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/30
 */
public class CommonWebsitePresenter extends BasePresenter<CommonWebsiteContract.ICommonWebsiteView>
        implements CommonWebsiteContract.ICommonWebsitePresenter {
    @Inject
    public CommonWebsitePresenter() {
    }

    @Override
    public void getCommonWebsite() {
        addSubscribe(DataManager.getInstance().getCommonWebSite()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult()).subscribe(commonWebsiteBeans -> {
                    if (isViewAttached()) {
                        if (CollectionUtils.isEmpty(commonWebsiteBeans)) {
                            getView().showEmptyView();
                        } else {
                            getView().onGetCommonWebsiteSuccess(commonWebsiteBeans);

                        }
                    }
                }, throwable -> {
                    if (isViewAttached()) {
                        getView().showError();
                    }
                }));
    }
}
