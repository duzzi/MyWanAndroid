package com.duzzi.mywanandroid.mvp.contract;

import com.duzzi.mywanandroid.base.presenter.IBasePresenter;
import com.duzzi.mywanandroid.base.view.IBaseView;

/**
 * 文件名: BannerContract
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/25
 */
public class SplashContract {
    public interface ISplashView extends IBaseView {
        void onToMain();
    }

    public interface ISplashPresenter extends IBasePresenter<ISplashView> {
        void toMain();
    }
}
