package com.duzzi.mywanandroid.mvp.presenter;

import com.duzzi.mywanandroid.base.presenter.BasePresenter;
import com.duzzi.mywanandroid.core.DataManager;
import com.duzzi.mywanandroid.core.bean.data.HotKeyBean;
import com.duzzi.mywanandroid.mvp.contract.HotKeyContract;
import com.duzzi.mywanandroid.util.CollectionUtils;
import com.duzzi.mywanandroid.util.RxUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * 文件名: HotKeyPresenter
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/30
 */
public class HotKeyPresenter extends BasePresenter<HotKeyContract.IHotKeyView> implements
        HotKeyContract.IHotKeyPresenter {
    @Inject
    public HotKeyPresenter() {
    }

    @Override
    public void getHotKey() {
        addSubscribe(DataManager.getInstance().getHotKey()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult()).subscribe(hotKeyBeans -> {
                    if (isViewAttached()) {
                        if (CollectionUtils.isEmpty(hotKeyBeans)) {

                        } else {
                            getView().onGetHotKeySuccess(hotKeyBeans);
                        }
                    }
                }, throwable -> {
                    if (isViewAttached()) {
                        getView().showError();
                    }
                }));
    }
}
