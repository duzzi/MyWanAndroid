package com.duzzi.mywanandroid.ui.activity;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duzzi.mywanandroid.R;
import com.duzzi.mywanandroid.base.activity.BaseActivity;
import com.duzzi.mywanandroid.core.DataManager;
import com.duzzi.mywanandroid.core.bean.event.EventMessage;
import com.duzzi.mywanandroid.mvp.contract.LogoutContract;
import com.duzzi.mywanandroid.mvp.presenter.EmptyPresenter;
import com.duzzi.mywanandroid.mvp.presenter.LogoutPresenter;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends BaseActivity<LogoutPresenter> implements LogoutContract.ILogoutView{


    @BindView(R.id.tv_title_center)
    TextView mTvTitleCenter;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.tv_logout)
    TextView mTvLogout;
    @BindView(R.id.ll_parent)
    LinearLayout mLlParent;

    @Override
    protected void initInject() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initToolBar();
    }

    private void initToolBar() {
        setSupportActionBar(mToolBar);
        mToolBar.setNavigationOnClickListener(v -> onBackPressed());
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowTitleEnabled(false);
        }
    }
    @OnClick(R.id.tv_logout)
    public void onClickLogout(){
        mPresenter.logout();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void getData() {

    }

    @Override
    public void onLogoutSuccess() {
        finish();
        EventBus.getDefault().post(new EventMessage(EventMessage.EVENT_LOGOUT));
    }
}
