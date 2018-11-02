package com.duzzi.mywanandroid.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.duzzi.mywanandroid.R;
import com.duzzi.mywanandroid.base.fragment.BaseFragment;
import com.duzzi.mywanandroid.core.bean.data.ArticleBean;
import com.duzzi.mywanandroid.core.bean.data.NavigationBean;
import com.duzzi.mywanandroid.mvp.contract.NavigationContract;
import com.duzzi.mywanandroid.mvp.presenter.NavigationPresenter;
import com.duzzi.mywanandroid.ui.activity.WebActivity;
import com.duzzi.mywanandroid.ui.adapter.rv.ArticleAdapter;
import com.duzzi.mywanandroid.ui.adapter.rv.NavigationAdapter;
import com.duzzi.mywanandroid.ui.widget.SpaceItemDecoration;
import com.duzzi.mywanandroid.ui.widget.loadsir.ErrorCallback;
import com.duzzi.mywanandroid.util.CollectionUtils;
import com.duzzi.mywanandroid.util.DLog;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

public class NavigationFragment extends BaseFragment<NavigationPresenter> implements NavigationContract.INavigationView {


    @BindView(R.id.vertical_tab_layout)
    VerticalTabLayout mVerticalTabLayout;
    @BindColor(R.color.textColor333)
    int mColorSelect;
    @BindColor(R.color.textColor999)
    int mColorNormal;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Inject
    NavigationAdapter mArticleAdapter;

    public NavigationFragment() {
    }

    public static NavigationFragment newInstance() {
        NavigationFragment fragment = new NavigationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext,2));
        mRecyclerView.addItemDecoration(new SpaceItemDecoration());
        mRecyclerView.setAdapter(mArticleAdapter);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                ArticleBean item = mArticleAdapter.getItem(position);
                if (item != null) {
                    WebActivity.show(mContext, item.getLink());
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation;
    }

    @Override
    protected void getData() {
        mPresenter.getNavigation();
    }


    @Override
    protected void initInject() {
        mFragmentComponent.inject(this);
    }

    @Override
    public void onGetNavigationSuccess(List<NavigationBean> navigationBeanList) {
        mBaseLoadService.showSuccess();
        if (CollectionUtils.isEmpty(navigationBeanList)) {
            return;
        }
        mVerticalTabLayout.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return navigationBeanList.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public ITabView.TabIcon getIcon(int position) {
                return null;
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {
                return new ITabView.TabTitle.Builder()
                        .setTextSize(12)
                        .setTextColor(mColorSelect, mColorNormal)
                        .setContent(navigationBeanList.get(position).getName())
                        .build();
            }

            @Override
            public int getBackground(int position) {
                return R.drawable.selector_vertical_tab;
            }
        });
        mVerticalTabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                DLog.d("position: " + position + " " + tab.isChecked());
                mArticleAdapter.setNewData(navigationBeanList.get(position).getArticles());
            }

            @Override
            public void onTabReselected(TabView tab, int position) {
                DLog.d("position: " + position);

            }
        });
        mVerticalTabLayout.setTabSelected(0);
        mArticleAdapter.setNewData(navigationBeanList.get(0).getArticles());

    }
    @Override
    public void showError() {
        super.showError();
        mBaseLoadService.showCallback(ErrorCallback.class);
    }

}

