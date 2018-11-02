package com.duzzi.mywanandroid.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.duzzi.mywanandroid.R;
import com.duzzi.mywanandroid.base.fragment.BaseFragment;
import com.duzzi.mywanandroid.core.bean.data.KnowledgeHierarchyBean;
import com.duzzi.mywanandroid.mvp.contract.KnowledgeHierarchyContract;
import com.duzzi.mywanandroid.mvp.presenter.KnowledgeHierarchyPresenter;
import com.duzzi.mywanandroid.ui.activity.HierarchyActivity;
import com.duzzi.mywanandroid.ui.adapter.rv.HierarchyAdapter;
import com.duzzi.mywanandroid.ui.widget.SpaceItemDecoration;
import com.duzzi.mywanandroid.ui.widget.loadsir.ErrorCallback;
import com.duzzi.mywanandroid.util.DLog;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class HierarchyFragment extends BaseFragment<KnowledgeHierarchyPresenter>
        implements KnowledgeHierarchyContract.IKnowledgeHierarchyView {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Inject
    HierarchyAdapter mHierarchyAdapter;
    public HierarchyFragment() {
    }

    public static HierarchyFragment newInstance() {
        HierarchyFragment fragment = new HierarchyFragment();
        return fragment;
    }


    @Override
    protected void initPresenter() {

    }


    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(SizeUtils.dp2px(8)
                , SizeUtils.dp2px(12)));
        mRecyclerView.setAdapter(mHierarchyAdapter);
        mHierarchyAdapter.setEnableLoadMore(true);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                KnowledgeHierarchyBean item = mHierarchyAdapter.getItem(position);
                if (item!=null) {
                    HierarchyActivity.show(mContext,item.getChildren());
                }
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(this::getData);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hierarchy;
    }

    @Override
    protected void getData() {
        mSwipeRefreshLayout.setRefreshing(true);
        mPresenter.getKnowledgeHierarchy();
    }

    @Override
    protected void initInject() {
        mFragmentComponent.inject(this);
    }

    @Override
    public void onGetKnowledgeHierarchySuccess(List<KnowledgeHierarchyBean> knowledgeHierarchyBeans) {
        DLog.d(knowledgeHierarchyBeans + "");
        mBaseLoadService.showSuccess();
        mSwipeRefreshLayout.setRefreshing(false);
        mHierarchyAdapter.setNewData(knowledgeHierarchyBeans);
        mHierarchyAdapter.loadMoreEnd();
    }

    @Override
    public void showError() {
        super.showError();
        mSwipeRefreshLayout.setRefreshing(false);
        mBaseLoadService.showCallback(ErrorCallback.class);
    }
}
