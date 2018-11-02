package com.duzzi.mywanandroid.mvp.contract;

import com.duzzi.mywanandroid.base.presenter.IBasePresenter;
import com.duzzi.mywanandroid.base.view.IBaseView;
import com.duzzi.mywanandroid.core.bean.data.BannerBean;

import java.util.List;

/**
 * 文件名: BannerContract
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/25
 */
public class BannerContract {
    public interface IBannerView extends IBaseView {
        void onGetBannerSuccess(List<BannerBean> bannerBeanList);
    }

    public interface IBannerPresenter extends IBasePresenter<IBannerView> {
        void getBanner();
    }
}
