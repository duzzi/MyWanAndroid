package com.duzzi.mywanandroid.mvp.contract;

import com.duzzi.mywanandroid.base.presenter.IBasePresenter;
import com.duzzi.mywanandroid.base.view.IBaseView;
import com.duzzi.mywanandroid.core.bean.data.CommonWebsiteBean;

import java.util.List;

/**
 * 文件名: CommonWebsiteContract
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/30
 */
public class CommonWebsiteContract {
    public interface ICommonWebsiteView extends IBaseView {
        void onGetCommonWebsiteSuccess(List<CommonWebsiteBean> commonWebsiteBeans);
    }

    public interface ICommonWebsitePresenter extends IBasePresenter<CommonWebsiteContract.ICommonWebsiteView> {
        void getCommonWebsite();
    }
}
