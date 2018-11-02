package com.duzzi.mywanandroid.mvp.presenter;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.Utils;
import com.duzzi.mywanandroid.R;
import com.duzzi.mywanandroid.base.presenter.BasePresenter;
import com.duzzi.mywanandroid.core.DataManager;
import com.duzzi.mywanandroid.core.bean.data.UserInfoBean;
import com.duzzi.mywanandroid.core.bean.event.EventMessage;
import com.duzzi.mywanandroid.core.constant.Constants;
import com.duzzi.mywanandroid.mvp.contract.LoginContract;
import com.duzzi.mywanandroid.util.ACache;
import com.duzzi.mywanandroid.util.RxUtils;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * 文件名: LoginPresenter
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/11/1
 */
public class LoginPresenter extends BasePresenter<LoginContract.ILoginView> implements LoginContract.ILoginPresenter {
    @Inject
    public LoginPresenter() {
    }

    @Override
    public void login(String username, String password) {
        if (isViewAttached()) {
            if (StringUtils.isTrimEmpty(username)||username.trim().length()<6) {
                getView().onUsernameError(Utils.getApp().getString(R.string.error_invalid_username));
                return;
            }
            if (StringUtils.isTrimEmpty(password)||password.trim().length()<6) {
                getView().onPasswordError(Utils.getApp().getString(R.string.error_invalid_password));
                return;
            }
        }
        addSubscribe(DataManager.getInstance().login(username,password)
        .compose(RxUtils.rxSchedulerHelper())
        .compose(RxUtils.handleResult()).subscribe(new Consumer<UserInfoBean>() {
                    @Override
                    public void accept(UserInfoBean userInfoBean) throws Exception {
                        if (isViewAttached()) {
                            DataManager.getInstance().setLoginAccount(userInfoBean.getUsername());
                            DataManager.getInstance().setLoginStatus(true);
                            EventBus.getDefault().post(new EventMessage(EventMessage.EVENT_REFRESH_USER_INFO));
                            getView().onLoginSuccess(userInfoBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (isViewAttached()) {
                            getView().showErrorMsg(throwable.getMessage());
                        }
                    }
                }));
    }


}
