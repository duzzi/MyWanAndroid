package com.duzzi.mywanandroid.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.duzzi.mywanandroid.R;
import com.duzzi.mywanandroid.base.fragment.BaseFragment;
import com.duzzi.mywanandroid.core.bean.data.ArticleBean;
import com.duzzi.mywanandroid.core.constant.Constants;
import com.duzzi.mywanandroid.mvp.contract.ProjectListContract;
import com.duzzi.mywanandroid.mvp.presenter.ProjectListPresenter;
import com.duzzi.mywanandroid.ui.activity.WebActivity;
import com.duzzi.mywanandroid.ui.adapter.rv.ArticleAdapter;
import com.duzzi.mywanandroid.ui.widget.SpaceItemDecoration;
import com.duzzi.mywanandroid.ui.widget.loadsir.EmptyCallback;
import com.duzzi.mywanandroid.ui.widget.loadsir.ErrorCallback;
import com.duzzi.mywanandroid.util.DLog;

import java.util.List;


import javax.inject.Inject;

import butterknife.BindView;

public class ProjectListFragment extends BaseFragment<ProjectListPresenter>
        implements ProjectListContract.IProjectView {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private int mCid;
    @Inject
    ArticleAdapter mArticleAdapter;

    public ProjectListFragment() {
    }

    public static ProjectListFragment newInstance(int cid) {
        ProjectListFragment fragment = new ProjectListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.IntentKey.CID, cid);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initVariables(Bundle bundle) {
        super.initVariables(bundle);
        if (bundle != null) {
            mCid = bundle.getInt(Constants.IntentKey.CID, -1);
        }
    }

    @Override
    protected void initPresenter() {

    }


    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.addItemDecoration(new SpaceItemDecoration());
        mRecyclerView.setAdapter(mArticleAdapter);
        mArticleAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.loadMore();
            }
        }, mRecyclerView);
        mArticleAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                ArticleBean item = mArticleAdapter.getItem(position);
                if (item != null) {
                    WebActivity.show(mContext, item.getLink());
                }
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(this::getData);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project_list;
    }

    @Override
    protected void getData() {
        DLog.d("mPresenter " + mPresenter + " " + mCid);
        mPresenter.getProjectList(mCid);
    }


    @Override
    protected void initInject() {
        mFragmentComponent.inject(this);
    }


    @Override
    public void showEmptyView() {
        super.showEmptyView();
        mBaseLoadService.showCallback(EmptyCallback.class);
    }

    @Override
    public void showError() {
        super.showError();
        mSwipeRefreshLayout.setRefreshing(false);
        mBaseLoadService.showCallback(ErrorCallback.class);
    }

    @Override
    public void onGetProjectListSuccess(boolean hasMore, List<ArticleBean> projectBeans) {
        mBaseLoadService.showSuccess();
        mSwipeRefreshLayout.setRefreshing(false);
        setData(true, hasMore, projectBeans);
    }

    @Override
    public void onLoadMore(boolean hasMore, List<ArticleBean> projectBeans) {
        setData(false, hasMore, projectBeans);
    }

    private void setData(boolean refresh, boolean hasMore, List<ArticleBean> articleBeanList) {
        if (refresh) {
            mArticleAdapter.setNewData(articleBeanList);
        } else {
            mArticleAdapter.addData(articleBeanList);
        }
        if (hasMore) {
            mArticleAdapter.loadMoreComplete();
        } else {
            mArticleAdapter.loadMoreEnd();
        }
    }
}
