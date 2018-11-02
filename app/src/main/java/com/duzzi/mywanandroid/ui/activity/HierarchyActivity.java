package com.duzzi.mywanandroid.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.duzzi.mywanandroid.R;
import com.duzzi.mywanandroid.base.activity.BaseActivity;
import com.duzzi.mywanandroid.core.bean.data.HierarchyChildrenBean;
import com.duzzi.mywanandroid.core.constant.Constants;
import com.duzzi.mywanandroid.mvp.presenter.EmptyPresenter;
import com.duzzi.mywanandroid.ui.adapter.indicator.CommonIndicatorAdapter;
import com.duzzi.mywanandroid.ui.adapter.vp.HierarchyFragmentAdapter;
import com.duzzi.mywanandroid.ui.fragment.HierarchyArticleFragment;
import com.duzzi.mywanandroid.util.CollectionUtils;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;

import butterknife.BindView;

public class HierarchyActivity extends BaseActivity<EmptyPresenter> {

    @BindView(R.id.vg_back)
    RelativeLayout mVgBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.cl_header)
    ConstraintLayout mClHeader;
    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    private ArrayList<HierarchyChildrenBean> mHierarchyChildrenBeans;

    public static void show(Context context, ArrayList<HierarchyChildrenBean> hierarchyChildrenBeans) {
        Intent intent = new Intent(context, HierarchyActivity.class);
        intent.putParcelableArrayListExtra(Constants.IntentKey.HIERARCHY_CHILDREN_BEAN_LIST, hierarchyChildrenBeans);
        context.startActivity(intent);
    }

    @Override
    protected void getIntentData() {
        super.getIntentData();
        mHierarchyChildrenBeans = getIntent().getParcelableArrayListExtra(Constants.IntentKey.HIERARCHY_CHILDREN_BEAN_LIST);
    }

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
        setClickBackListener(mVgBack);
        initIndicatorAndViewpager();
    }

    private void initIndicatorAndViewpager() {
        if (CollectionUtils.isEmpty(mHierarchyChildrenBeans)) return;
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Integer> ids = new ArrayList<>();
        for (HierarchyChildrenBean hierarchyChildrenBean : mHierarchyChildrenBeans) {
            names.add(hierarchyChildrenBean.getName());
            ids.add(hierarchyChildrenBean.getId());
        }
        CommonNavigator commonNavigator = new CommonNavigator(this);
        CommonIndicatorAdapter indicatorAdapter = new CommonIndicatorAdapter(names, mViewPager);
        commonNavigator.setAdapter(indicatorAdapter);
        mMagicIndicator.setNavigator(commonNavigator);
        mViewPager.setAdapter(new HierarchyFragmentAdapter(getSupportFragmentManager(), ids));
        mViewPager.setOffscreenPageLimit(999);
        ViewPagerHelper.bind(mMagicIndicator, mViewPager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hierarchy;
    }

    @Override
    protected void getData() {
    }

    @Override
    public void showError() {
        super.showError();
    }

}
