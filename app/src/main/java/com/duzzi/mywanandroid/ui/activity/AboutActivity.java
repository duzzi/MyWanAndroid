package com.duzzi.mywanandroid.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.duzzi.mywanandroid.R;
import com.duzzi.mywanandroid.base.activity.BaseActivity;
import com.duzzi.mywanandroid.mvp.presenter.EmptyPresenter;

import butterknife.BindView;

public class AboutActivity extends BaseActivity<EmptyPresenter> {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mToolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R.id.tv_about_content)
    TextView mTvAboutContent;
    @BindView(R.id.fab)
    FloatingActionButton mFab;

    @Override
    protected void initInject() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mTvAboutContent.setText(Html.fromHtml(getString(R.string.about_content)));
        mTvAboutContent.setMovementMethod(LinkMovementMethod.getInstance());
        initToolBar();
        mFab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }
    private void initToolBar() {
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(v -> onBackPressed());
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowTitleEnabled(true);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void getData() {

    }

}
