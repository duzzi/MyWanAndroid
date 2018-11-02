package com.duzzi.mywanandroid.mvp.contract;

import com.duzzi.mywanandroid.base.presenter.IBasePresenter;
import com.duzzi.mywanandroid.base.view.IBaseView;
import com.duzzi.mywanandroid.core.bean.data.NavigationBean;

import java.util.List;

/**
 * 文件名: NavigationContract
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/25
 */
public class NavigationContract {
    public interface INavigationView extends IBaseView {
        void onGetNavigationSuccess(List<NavigationBean> navigationBeanList);
    }

    public interface INavigationPresenter extends IBasePresenter<INavigationView> {
        void getNavigation();
    }
}
