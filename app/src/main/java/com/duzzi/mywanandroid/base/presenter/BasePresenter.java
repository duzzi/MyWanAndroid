package com.duzzi.mywanandroid.base.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.duzzi.mywanandroid.base.view.IBaseView;
import com.duzzi.mywanandroid.core.constant.Constants;
import com.duzzi.mywanandroid.exception.CommonException;
import com.duzzi.mywanandroid.util.DLog;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class BasePresenter<T extends IBaseView> implements IBasePresenter<T> {
    protected Context mContext;
    private WeakReference<T> mViews;
    protected int mNextPage = Constants.DEFAULT_NEXT_PAGE;
    private CompositeDisposable mCompositeDisposable;
    protected int mCurrentPage = 0;

    public BasePresenter(Context context, T view) {
        mContext = context;
        mViews = new WeakReference<T>(view);
    }


    public BasePresenter() {
    }

    public long getCurrentTime() {
        java.util.Date date = new java.util.Date();
        long datetime = date.getTime();
        return datetime;
    }

    protected boolean isNetError(String msg) {
        return !TextUtils.isEmpty(msg) && (msg.contains("UnknownHostException")
                || msg.contains("ConnectException")
                || msg.contains("java.net.TimeoutException"));
    }

    @Override
    public void attachView(T view) {
        mViews = new WeakReference<T>(view);
    }

    @Override
    public T getView() {
        return isViewAttached() ? mViews.get() : null;
    }

    @Override
    public boolean isViewAttached() {
        return null != mViews && null != mViews.get();
    }

    @Override
    public void detachView() {
        if (null != mViews) {
            mViews.clear();
            mViews = null;
        }
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    @Override
    public void addRxBindingSubscribe(Disposable disposable) {

    }

    @Override
    public boolean getNightModeState() {
        return false;
    }

    @Override
    public void setLoginStatus(boolean loginStatus) {

    }

    @Override
    public boolean getLoginStatus() {
        return false;
    }

    @Override
    public String getLoginAccount() {
        return null;
    }

    @Override
    public void setLoginAccount(String account) {

    }

    @Override
    public void setLoginPassword(String password) {

    }

    /**
     * 由于早期开放的一些API页码为0开始，后期接口修改为从1开始，为了兼顾之前的开放API，故无法统一
     * @return
     */
    @Override
    public int getCurrentPage() {
        return mCurrentPage;
    }

    @Override
    public void resetCurrentPage() {
        mCurrentPage = 0;
    }

    @Override
    public void pageIncrease() {
        mCurrentPage++;
    }

    public int getNextPageIndex() {
        return mNextPage;
    }

    protected void addSubscribe(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    protected String getErrorMessage(Throwable throwable){
        if (throwable instanceof CommonException) {
            return throwable.getMessage();
        }
        return throwable.getMessage();
    }


}