package com.duzzi.mywanandroid.mvp.presenter;

import com.duzzi.mywanandroid.base.presenter.BasePresenter;
import com.duzzi.mywanandroid.mvp.contract.SplashContract;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;


/**
 * 文件名: SplashPresenter
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/25
 */
public class SplashPresenter extends BasePresenter<SplashContract.ISplashView>
        implements SplashContract.ISplashPresenter {
    @Inject
    public SplashPresenter() {
    }

    @Override
    public void attachView(SplashContract.ISplashView view) {
        super.attachView(view);

    }

    @Override
    public void toMain() {
        addSubscribe(Observable.timer(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (isViewAttached()) {
                            getView().onToMain();
                        }
                    }
                }));
    }
}
