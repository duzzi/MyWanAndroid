package com.duzzi.mywanandroid.base.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.duzzi.mywanandroid.R;
import com.duzzi.mywanandroid.base.presenter.BasePresenter;
import com.duzzi.mywanandroid.base.view.IBaseView;
import com.duzzi.mywanandroid.di.component.ActivityComponent;
import com.duzzi.mywanandroid.di.component.DaggerActivityComponent;
import com.gyf.barlibrary.ImmersionBar;
import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;
import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 文件名: BaseActivity
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/6/15
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {

    private boolean mIsWifiConnect = true;
    protected ProgressDialog mProgressDialog;
    @Inject
    protected P mPresenter;
    protected final RxPermissions rxPermissions = new RxPermissions(this);
    protected View mEmptyView;
    protected TextView mTvEmpty;
    private Unbinder mBind;
    protected ActivityComponent mActivityComponent;
    private SimpleArcDialog mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivityComponent();
        adaptScreen();
        setContentView(getLayoutId());
        mBind = ButterKnife.bind(this);
        init();

        getIntentData();
        initInject();
        initPresenter();
        checkPresenterIsNull();
        initView(savedInstanceState);
        onViewCreated();
        getData();
        registerNetReceiver();
    }

    protected abstract void initInject();


    private void initActivityComponent() {
        mActivityComponent = DaggerActivityComponent.create();
    }

    protected void onViewCreated() {
        if (mPresenter != null) mPresenter.attachView(this);
    }


    private void adaptScreen() {
        if (ScreenUtils.isPortrait()) {
            ScreenUtils.adaptScreen4VerticalSlide(this, 375);
        } else {
            ScreenUtils.adaptScreen4HorizontalSlide(this, 375);
        }
    }

    protected void inflateEmptyView(ViewGroup viewGroup) {
//        mEmptyView = getLayoutInflater().inflate(R.layout.layout_empty, viewGroup, false);
//        mTvEmpty = (TextView) mEmptyView.findViewById(R.id.tv_empty);
    }

    protected void getIntentData() {


    }

    protected void translucent() {
        ImmersionBar.with(this).transparentBar().init();
//        ImmersionBar.with(this)
//                .transparentStatusBar()  //透明状态栏，不写默认透明色
//                .transparentNavigationBar()  //透明导航栏，不写默认黑色(设置此方法，fullScreen()方法自动为true)
//                .transparentBar()             //透明状态栏和导航栏，不写默认状态栏为透明色，导航栏为黑色（设置此方法，fullScreen()方法自动为true）
//                .statusBarColor(R.color.colorPrimary)     //状态栏颜色，不写默认透明色
//                .navigationBarColor(R.color.colorPrimary) //导航栏颜色，不写默认黑色
//                .barColor(R.color.colorPrimary)  //同时自定义状态栏和导航栏颜色，不写默认状态栏为透明色，导航栏为黑色
//                .statusBarAlpha(0.3f)  //状态栏透明度，不写默认0.0f
//                .navigationBarAlpha(0.4f)  //导航栏透明度，不写默认0.0F
//                .barAlpha(0.3f)  //状态栏和导航栏透明度，不写默认0.0f
//                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
//                .flymeOSStatusBarFontColor(R.color.btn3)  //修改flyme OS状态栏字体颜色
//                .fullScreen(true)      //有导航栏的情况下，activity全屏显示，也就是activity最下面被导航栏覆盖，不写默认非全屏
//                .hideBar(BarHide.FLAG_HIDE_BAR)  //隐藏状态栏或导航栏或两者，不写默认不隐藏
//                .addViewSupportTransformColor(toolbar)  //设置支持view变色，可以添加多个view，不指定颜色，默认和状态栏同色，还有两个重载方法
//                .titleBar(view)    //解决状态栏和布局重叠问题，任选其一
//                .titleBarMarginTop(view)     //解决状态栏和布局重叠问题，任选其一
//                .statusBarView(view)  //解决状态栏和布局重叠问题，任选其一
//                .fitsSystemWindows(true)    //解决状态栏和布局重叠问题，任选其一，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色，还有一些重载方法
//                .supportActionBar(true) //支持ActionBar使用
//                .statusBarColorTransform(R.color.orange)  //状态栏变色后的颜色
//                .navigationBarColorTransform(R.color.orange) //导航栏变色后的颜色
//                .barColorTransform(R.color.orange)  //状态栏和导航栏变色后的颜色
//                .removeSupportView(toolbar)  //移除指定view支持
//                .removeSupportAllView() //移除全部view支持
//                .navigationBarEnable(true)   //是否可以修改导航栏颜色，默认为true
//                .navigationBarWithKitkatEnable(true)  //是否可以修改安卓4.4和emui3.1手机导航栏颜色，默认为true
//                .fixMarginAtBottom(true)   //已过时，当xml里使用android:fitsSystemWindows="true"属性时,解决4.4和emui3.1手机底部有时会出现多余空白的问题，默认为false，非必须
//                .addTag("tag")  //给以上设置的参数打标记
//                .getTag("tag")  //根据tag获得沉浸式参数
//                .reset()  //重置所以沉浸式参数
//                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题，默认为false，还有一个重载方法，可以指定软键盘mode
//                .keyboardMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)  //单独指定软键盘模式
//                .setOnKeyboardListener(new OnKeyboardListener() {    //软键盘监听回调
//                    @Override
//                    public void onKeyboardChange(boolean isPopup, int keyboardHeight) {
//                        LogUtils.e(isPopup);  //isPopup为true，软键盘弹出，为false，软键盘关闭
//                    }
//                })
//                .init();  //必须调用方可沉浸式
    }

    /**
     * 白底黑字
     */
    protected void setStatusBarLightMode(boolean barLightMode) {
        ImmersionBar.with(this)
                .statusBarColor(barLightMode ? R.color.white : R.color.black)
                .statusBarDarkFont(barLightMode)
                .fitsSystemWindows(true).init();

    }

    /**
     * 其他初始化
     */
    protected void init() {
//        ImmersionBar.with(this).init();
//        setStatusBarLightMode(false);
    }

    @Override
    protected void onDestroy() {
        ImmersionBar.with(this).destroy();
        ScreenUtils.cancelAdaptScreen(this);
        super.onDestroy();
        detachView();
        unRegisterNetReceiver();
        if (mBind != null && mBind != Unbinder.EMPTY) {
            mBind.unbind();
            mBind = null;
        }
    }

    protected void detachView() {
        if (mPresenter != null) mPresenter.detachView();
    }

    protected void registerNetReceiver() {

    }

    protected void unRegisterNetReceiver() {

    }


    //初始化Presenter
    protected abstract void initPresenter();


    protected abstract void initView(Bundle savedInstanceState);

    protected abstract int getLayoutId();

    protected abstract void getData();


    private void checkPresenterIsNull() {
//        if (mPresenter == null) {
//            throw new IllegalStateException("please init mPresenter in initPresenter() method ");
//        }
    }

    protected void showProgressDialog() {
//        if (mProgressDialog == null) {
//            mProgressDialog = new ProgressDialog(this);
//            mProgressDialog.setCancelable(false);
//            mProgressDialog.setCanceledOnTouchOutside(false);
//        }
//        mProgressDialog.show();
        if (mDialog == null) {
            mDialog = new SimpleArcDialog(this);
            ArcConfiguration arcConfiguration = new ArcConfiguration(this);
            arcConfiguration.setColors(getResources().getIntArray(R.array.load_colors));
            mDialog.setConfiguration(arcConfiguration);
        }
        mDialog.show();
    }

    protected void dismissProgressDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    protected void setClickBackListener(View view) {
        view.setOnClickListener(v -> onBackPressed());
    }

    //################################################################################
    @Override
    public void useNightMode(boolean isNight) {
        if (isNight) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
        }
        recreate();
    }

    @Override
    public void showErrorMsg(String errorMsg) {
    }

    @Override
    public void showNormal() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void reload() {

    }

    @Override
    public void showCollectSuccess() {

    }

    @Override
    public void showCancelCollectSuccess() {

    }

    @Override
    public void showLoginView() {

    }

    @Override
    public void showLogoutView() {

    }

    @Override
    public void showToast(String message) {
        ToastUtils.showShort(message);
    }

    @Override
    public void showSnackBar(String message) {
    }

    @Override
    public void showEmptyView() {
    }

}
