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
public class RegisterContract {
    public interface IRegisterView extends IBaseView {
        void onRegisterSuccess(UserInfoBean userInfoBean);

        void onUsernameError(String msg);
        void onPasswordError(String msg);
        void onPassword2Error(String msg);
    }

    public interface IRegisterPresenter extends IBasePresenter<IRegisterView> {
        void register(String username, String password,String password2);
    }
}
