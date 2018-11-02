package com.duzzi.mywanandroid.mvp.presenter;

import com.duzzi.mywanandroid.base.presenter.BasePresenter;
import com.duzzi.mywanandroid.core.DataManager;
import com.duzzi.mywanandroid.core.bean.data.NavigationBean;
import com.duzzi.mywanandroid.mvp.contract.NavigationContract;
import com.duzzi.mywanandroid.util.RxUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * 文件名: NavigationPresenter
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/31
 */
public class NavigationPresenter extends BasePresenter<NavigationContract.INavigationView> implements NavigationContract.INavigationPresenter {
    @Inject
    public NavigationPresenter() {
    }

    @Override
    public void getNavigation() {
        addSubscribe(DataManager.getInstance().getNavigation()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult()).subscribe(new Consumer<List<NavigationBean>>() {
                    @Override
                    public void accept(List<NavigationBean> navigationBeans) throws Exception {
                        if (isViewAttached()) {
                            getView().onGetNavigationSuccess(navigationBeans);
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
}
