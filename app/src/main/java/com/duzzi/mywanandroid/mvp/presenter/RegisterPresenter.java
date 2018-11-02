package com.duzzi.mywanandroid.mvp.presenter;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.Utils;
import com.duzzi.mywanandroid.R;
import com.duzzi.mywanandroid.base.presenter.BasePresenter;
import com.duzzi.mywanandroid.core.DataManager;
import com.duzzi.mywanandroid.core.bean.data.UserInfoBean;
import com.duzzi.mywanandroid.mvp.contract.RegisterContract;
import com.duzzi.mywanandroid.util.RxUtils;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * 文件名: RegisterPresenter
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/11/1
 */
public class RegisterPresenter extends BasePresenter<RegisterContract.IRegisterView> implements RegisterContract.IRegisterPresenter {
    @Inject
    public RegisterPresenter() {
    }


    @Override
    public void register(String username, String password, String password2) {
        if (isViewAttached()) {
            if (StringUtils.isTrimEmpty(username)||username.trim().length()<6) {
                getView().onUsernameError(Utils.getApp().getString(R.string.error_invalid_username));
                return;
            }
            if (StringUtils.isTrimEmpty(password)||password.trim().length()<6) {
                getView().onPasswordError(Utils.getApp().getString(R.string.error_invalid_password));
                return;
            }
            if (StringUtils.isTrimEmpty(password2)||password2.trim().length()<6) {
                getView().onPasswordError(Utils.getApp().getString(R.string.error_invalid_password));
                return;
            }
        }
        addSubscribe(DataManager.getInstance().register(username,password,password2)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult()).subscribe(new Consumer<UserInfoBean>() {
                    @Override
                    public void accept(UserInfoBean userInfoBean) throws Exception {
                        if (isViewAttached()) {
                            getView().onRegisterSuccess(userInfoBean);
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
