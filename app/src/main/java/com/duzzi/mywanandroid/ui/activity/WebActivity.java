package com.duzzi.mywanandroid.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duzzi.mywanandroid.R;
import com.duzzi.mywanandroid.base.activity.BaseActivity;
import com.duzzi.mywanandroid.core.constant.Constants;
import com.duzzi.mywanandroid.mvp.presenter.EmptyPresenter;
import com.duzzi.mywanandroid.util.ShareUtil;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.AgentWebConfig;

import butterknife.BindColor;
import butterknife.BindView;

public class WebActivity extends BaseActivity<EmptyPresenter> {

    @BindColor(R.color.colorAccent)
    int mColorIndicator;
    @BindView(R.id.ll_parent)
    LinearLayout mLlParent;
    @BindView(R.id.tv_title_center)
    TextView mTvTitleCenter;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    private String mLinkUrl;
    private AgentWeb mAgentWeb;

    public static void show(Context context, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(Constants.IntentKey.LINK_URL, url);
        context.startActivity(intent);
    }

    @Override
    protected void getIntentData() {
        super.getIntentData();
        mLinkUrl = getIntent().getStringExtra(Constants.IntentKey.LINK_URL);
    }

    @Override
    protected void initInject() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setStatusBarLightMode(true);
        initToolBar();
        AgentWebConfig.clearDiskCache(this);
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mLlParent,
                        new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(mColorIndicator)
                .createAgentWeb()
                .ready().go(mLinkUrl);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.web, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                ShareUtil.shareText(this, mLinkUrl);
                break;
            case R.id.action_open_in_the_browser:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(mLinkUrl)));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void getData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAgentWeb != null) mAgentWeb.getWebLifeCycle().onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAgentWeb != null) mAgentWeb.getWebLifeCycle().onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAgentWeb != null) mAgentWeb.getWebLifeCycle().onResume();
    }

}
