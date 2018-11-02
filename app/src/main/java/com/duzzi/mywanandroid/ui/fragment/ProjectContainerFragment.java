package com.duzzi.mywanandroid.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.View;

import com.duzzi.mywanandroid.R;
import com.duzzi.mywanandroid.base.fragment.BaseFragment;
import com.duzzi.mywanandroid.core.bean.data.ProjectCategoryBean;
import com.duzzi.mywanandroid.mvp.contract.ProjectCategoryContract;
import com.duzzi.mywanandroid.mvp.presenter.ProjectCategoryPresenter;
import com.duzzi.mywanandroid.ui.adapter.indicator.CommonIndicatorAdapter;
import com.duzzi.mywanandroid.ui.adapter.vp.ProjectFragmentAdapter;
import com.duzzi.mywanandroid.ui.widget.loadsir.ErrorCallback;
import com.duzzi.mywanandroid.util.CollectionUtils;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ProjectContainerFragment extends BaseFragment<ProjectCategoryPresenter> implements ProjectCategoryContract.IProjectCategoryView {


    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    public ProjectContainerFragment() {
    }

    public static ProjectContainerFragment newInstance() {
        ProjectContainerFragment fragment = new ProjectContainerFragment();
        return fragment;
    }


    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    private void initIndicatorAndViewpager(List<ProjectCategoryBean> projectCategoryBeans) {
        if (CollectionUtils.isEmpty(projectCategoryBeans)) return;
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Integer> ids = new ArrayList<>();
        for (ProjectCategoryBean hierarchyChildrenBean : projectCategoryBeans) {
            names.add(Html.fromHtml(hierarchyChildrenBean.getName()).toString());
            ids.add(hierarchyChildrenBean.getId());
        }
        CommonNavigator commonNavigator = new CommonNavigator(mContext);
        CommonIndicatorAdapter indicatorAdapter = new CommonIndicatorAdapter(names, mViewPager);
        commonNavigator.setAdapter(indicatorAdapter);
        mMagicIndicator.setNavigator(commonNavigator);
        mViewPager.setAdapter(new ProjectFragmentAdapter(getChildFragmentManager(), ids));
        mViewPager.setOffscreenPageLimit(1);
        ViewPagerHelper.bind(mMagicIndicator, mViewPager);
        mViewPager.setCurrentItem(0);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project_container;
    }

    @Override
    protected void getData() {
        mPresenter.getProjectCategory();
    }


    @Override
    protected void initInject() {
        mFragmentComponent.inject(this);
    }

    @Override
    public void onGetProjectCategorySuccess(List<ProjectCategoryBean> projectCategoryBeans) {
        mBaseLoadService.showSuccess();
        initIndicatorAndViewpager(projectCategoryBeans);
    }
    @Override
    public void showError() {
        super.showError();
        mBaseLoadService.showCallback(ErrorCallback.class);
    }

}
