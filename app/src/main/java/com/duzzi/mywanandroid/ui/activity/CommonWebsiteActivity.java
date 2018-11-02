package com.duzzi.mywanandroid.ui.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.duzzi.mywanandroid.R;
import com.duzzi.mywanandroid.base.activity.BaseActivity;
import com.duzzi.mywanandroid.core.bean.data.CommonWebsiteBean;
import com.duzzi.mywanandroid.mvp.contract.CommonWebsiteContract;
import com.duzzi.mywanandroid.mvp.presenter.CommonWebsitePresenter;
import com.duzzi.uilib.viewgroup.FlowLayout;

import java.util.List;

import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CommonWebsiteActivity extends BaseActivity<CommonWebsitePresenter> implements
        CommonWebsiteContract.ICommonWebsiteView {


    @BindView(R.id.flow_layout)
    FlowLayout mFlowLayout;
    @BindDrawable(R.drawable.shape_tag)
    Drawable mDrawableBg;
    @BindView(R.id.tv_title_center)
    TextView mTvTitleCenter;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindString(R.string.common_website)
    String mStringCommonWebsite;
    private int padding = SizeUtils.dp2px(6);

    @Override
    protected void initInject() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setStatusBarLightMode(true);
        initToolBar();
    }
    private void initToolBar() {
        setSupportActionBar(mToolBar);
        mTvTitleCenter.setText(mStringCommonWebsite);
        mToolBar.setNavigationOnClickListener(v -> onBackPressed());
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_common_website;
    }

    @Override
    protected void getData() {
        mPresenter.getCommonWebsite();
    }

    @Override
    public void onGetCommonWebsiteSuccess(List<CommonWebsiteBean> commonWebsiteBeans) {
        mFlowLayout.removeAllViews();
        for (CommonWebsiteBean commonWebsiteBean : commonWebsiteBeans) {
            TextView textView = new TextView(this);
            textView.setText(commonWebsiteBean.getName());
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            textView.setTextColor(Color.BLACK);
            textView.setPadding(padding, padding, padding, padding);
            textView.setBackgroundColor(Color.parseColor("#33666666"));
            textView.setOnClickListener(v -> WebActivity.show(CommonWebsiteActivity.this,
                    commonWebsiteBean.getLink()));
            mFlowLayout.addView(textView);
        }

    }

    @Override
    public void showEmptyView() {
        super.showEmptyView();
        ToastUtils.showShort("nothing");
    }

}
