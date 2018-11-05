package com.duzzi.mywanandroid.mvp.presenter;

import com.duzzi.mywanandroid.base.presenter.BasePresenter;
import com.duzzi.mywanandroid.core.DataManager;
import com.duzzi.mywanandroid.core.bean.event.EventMessage;
import com.duzzi.mywanandroid.mvp.contract.LogoutContract;
import com.duzzi.mywanandroid.util.RxUtils;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

/**
 * 文件名: LogoutPresenter
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/11/5
 */
public class LogoutPresenter extends BasePresenter<LogoutContract.ILogoutView>
        implements LogoutContract.ILogoutPresenter {
    @Inject
    public LogoutPresenter() {
    }

    @Override
    public void logout() {
//        addSubscribe(DataManager.getInstance().logout()
//                .compose(RxUtils.rxSchedulerHelper())
//                .compose(RxUtils.handleResult()).subscribe());
        DataManager.getInstance().setLoginStatus(false);
        if (isViewAttached()) {
            getView().onLogoutSuccess();
        }
    }
}
