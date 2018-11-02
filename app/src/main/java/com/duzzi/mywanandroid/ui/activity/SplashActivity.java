package com.duzzi.mywanandroid.ui.activity;

import android.Manifest;
import android.os.Bundle;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.duzzi.mywanandroid.R;
import com.duzzi.mywanandroid.base.activity.BaseActivity;
import com.duzzi.mywanandroid.mvp.contract.SplashContract;
import com.duzzi.mywanandroid.mvp.presenter.SplashPresenter;
import com.duzzi.mywanandroid.util.DLog;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.BindString;
import io.reactivex.disposables.Disposable;


public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.ISplashView {
    @BindString(R.string.permission_deny)
    String mString;

    @Override
    protected void initInject() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ImmersionBar.with(this).transparentBar().statusBarDarkFont(true).init();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void getData() {
        DLog.d("mPresenter: " + mPresenter);
        Disposable subscribe = rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M
                        mPresenter.toMain();
                    } else {
                        ToastUtils.showShort(mString);
                    }
                });
    }


    @Override
    public void onToMain() {
        ActivityUtils.startActivity(this, MainActivity.class);
        finish();
    }
}
