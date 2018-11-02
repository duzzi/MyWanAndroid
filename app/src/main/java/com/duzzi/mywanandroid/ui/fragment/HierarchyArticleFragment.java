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
import com.duzzi.mywanandroid.mvp.contract.HierarchyArticleContract;
import com.duzzi.mywanandroid.mvp.presenter.HierarchyArticlePresenter;
import com.duzzi.mywanandroid.ui.activity.WebActivity;
import com.duzzi.mywanandroid.ui.adapter.rv.ArticleAdapter;
import com.duzzi.mywanandroid.ui.widget.SpaceItemDecoration;
import com.duzzi.mywanandroid.ui.widget.loadsir.ErrorCallback;
import com.duzzi.mywanandroid.util.DLog;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class HierarchyArticleFragment extends BaseFragment<HierarchyArticlePresenter>
        implements HierarchyArticleContract.IHierarchyArticleView {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Inject
    ArticleAdapter mArticleAdapter;
    private int mCid;

    public HierarchyArticleFragment() {
    }

    public static HierarchyArticleFragment newInstance(int cid) {
        HierarchyArticleFragment fragment = new HierarchyArticleFragment();
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
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                ArticleBean item = mArticleAdapter.getItem(position);
                if (item!=null) {
                    WebActivity.show(mContext,item.getLink());
                }
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(this::getData);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hierarchy_article;
    }

    @Override
    protected void getData() {
        if (!mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(true);
        }
        DLog.d("mPresenter " + mPresenter + " "+mCid);
        mPresenter.getHierarchyArticle(mCid);
    }


    @Override
    protected void initInject() {
        mFragmentComponent.inject(this);
    }

    @Override
    public void onGetHierarchyArticleSuccess(boolean hasMore, List<ArticleBean> hierarchyArticleBeans) {
        mBaseLoadService.showSuccess();
        mSwipeRefreshLayout.setRefreshing(false);
        mArticleAdapter.setNewData(hierarchyArticleBeans);
    }

    @Override
    public void onLoadMoreHierarchyArticleSuccess(boolean hasMore, List<ArticleBean> hierarchyArticleBeans) {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showError() {
        super.showError();
        mSwipeRefreshLayout.setRefreshing(false);
        mBaseLoadService.showCallback(ErrorCallback.class);
    }
}
