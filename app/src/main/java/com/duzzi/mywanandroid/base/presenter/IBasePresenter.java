package com.duzzi.mywanandroid.base.presenter;

import com.duzzi.mywanandroid.base.view.IBaseView;

import io.reactivex.disposables.Disposable;

/**
 * 文件名: IBasePresenter
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/23
 */
public interface IBasePresenter<T extends IBaseView> {

    /**
     * 注入View
     *
     * @param view view
     */
    void attachView(T view);

    /**
     * 回收View
     */
    void detachView();

    T getView();

    boolean isViewAttached();

    /**
     * Add rxBing subscribe manager
     *
     * @param disposable Disposable
     */
    void addRxBindingSubscribe(Disposable disposable);

    /**
     * Get night mode state
     *
     * @return if is night mode
     */
    boolean getNightModeState();

    /**
     * Set register status
     *
     * @param loginStatus register status
     */
    void setLoginStatus(boolean loginStatus);

    /**
     * Get register status
     *
     * @return if is register status
     */
    boolean getLoginStatus();

    /**
     * Get register account
     *
     * @return register account
     */
    String getLoginAccount();

    /**
     * Set register status
     *
     * @param account account
     */
    void setLoginAccount(String account);

    /**
     * Set register password
     *
     * @param password password
     */
    void setLoginPassword(String password);

    /**
     * Get current page
     *
     * @return current page
     */
    int getCurrentPage();

    void resetCurrentPage();

    void pageIncrease();
}
