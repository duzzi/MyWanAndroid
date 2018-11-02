package com.duzzi.mywanandroid.mvp.presenter;

import com.duzzi.mywanandroid.base.presenter.BasePresenter;
import com.duzzi.mywanandroid.core.DataManager;
import com.duzzi.mywanandroid.core.bean.data.BannerBean;
import com.duzzi.mywanandroid.core.bean.response.BannerResponse;
import com.duzzi.mywanandroid.mvp.contract.BannerContract;
import com.duzzi.mywanandroid.util.DLog;
import com.duzzi.mywanandroid.util.RxUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 文件名: BannerPresenter
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/25
 */
public class BannerPresenter extends BasePresenter<BannerContract.IBannerView> implements
        BannerContract.IBannerPresenter {
    @Inject
    public BannerPresenter() {
    }

    @Override
    public void getBanner() {
        addSubscribe(DataManager.getInstance().getBanner()
                .compose(RxUtils.rxSchedulerHelper())
                .subscribe(bannerResponse -> {
                    DLog.d("isViewAttached: " +isViewAttached());
                    if (isViewAttached()) {
                        getView().onGetBannerSuccess(bannerResponse.getData());
                    }
                }, throwable -> {
                    if (isViewAttached()) {
                        getView().showError();
                    }
                }));
    }

}
