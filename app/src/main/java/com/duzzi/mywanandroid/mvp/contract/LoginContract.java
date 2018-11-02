package com.duzzi.mywanandroid.mvp.contract;

import com.duzzi.mywanandroid.base.presenter.IBasePresenter;
import com.duzzi.mywanandroid.base.view.IBaseView;
import com.duzzi.mywanandroid.core.bean.data.UserInfoBean;

/**
 * 文件名: BannerContract
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/25
 */
public class LoginContract {
    public interface ILoginView extends IBaseView {
        void onLoginSuccess(UserInfoBean userInfoBean);

        void onUsernameError(String msg);
        void onPasswordError(String msg);
    }

    public interface ILoginPresenter extends IBasePresenter<ILoginView> {
        void login(String username, String password);
    }
}
