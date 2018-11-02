package com.duzzi.mywanandroid.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.duzzi.mywanandroid.R;
import com.duzzi.mywanandroid.base.activity.BaseActivity;
import com.duzzi.mywanandroid.core.bean.data.ArticleBean;
import com.duzzi.mywanandroid.core.bean.data.HotKeyBean;
import com.duzzi.mywanandroid.mvp.contract.HotKeyContract;
import com.duzzi.mywanandroid.mvp.contract.SearchContract;
import com.duzzi.mywanandroid.mvp.presenter.HotKeyPresenter;
import com.duzzi.mywanandroid.mvp.presenter.SearchPresenter;
import com.duzzi.mywanandroid.ui.adapter.rv.ArticleAdapter;
import com.duzzi.mywanandroid.ui.widget.SpaceItemDecoration;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class SearchActivity extends BaseActivity<HotKeyPresenter> implements HotKeyContract.IHotKeyView, SearchContract.ISearchView {


    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.tag_flow_layout)
    TagFlowLayout mTagFlowLayout;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.tv_search)
    TextView mTvSearch;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private int padding = SizeUtils.dp2px(6);
    @Inject
    SearchPresenter mSearchPresenter;
    @Inject
    ArticleAdapter mSearchAdapter;

    @Override
    protected void initInject() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        mSearchPresenter.attachView(this);
    }

    @Override
    protected void detachView() {
        super.detachView();
        mSearchPresenter.detachView();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setStatusBarLightMode(true);
        initToolBar();
        initRecyclerView();

    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new SpaceItemDecoration());
        mRecyclerView.setAdapter(mSearchAdapter);
        mSearchAdapter.setEnableLoadMore(false);
        mSearchAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mSearchPresenter.loadMore();
            }
        }, mRecyclerView);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                ArticleBean item = mSearchAdapter.getItem(position);
                if (item != null) {
                    WebActivity.show(SearchActivity.this, item.getLink());
                }
            }
        });
    }

    private void initToolBar() {
        setSupportActionBar(mToolBar);
        mToolBar.setNavigationOnClickListener(v -> onBackPressed());
        mTvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchPresenter.search(mEtSearch.getText().toString());
            }
        });
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void getData() {
        mPresenter.getHotKey();
    }

    @Override
    public void onGetHotKeySuccess(List<HotKeyBean> hotKeyBeanList) {

        mTagFlowLayout.setAdapter(new TagAdapter<HotKeyBean>(hotKeyBeanList) {
            @Override
            public View getView(FlowLayout parent, int position, HotKeyBean hotKeyBean) {
                TextView textView = new TextView(SearchActivity.this);
                textView.setText(hotKeyBean.getName());
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textView.setTextColor(Color.BLACK);
                textView.setPadding(padding, padding, padding, padding);
                textView.setBackgroundColor(Color.parseColor("#33666666"));
                return textView;
            }
        });
        mTagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                mSearchPresenter.search(hotKeyBeanList.get(position).getName());
                return true;
            }
        });
    }

    @Override
    public void showError() {
        super.showError();

    }

    @Override
    public void onSearchSuccess(boolean hasMore, List<ArticleBean> searchResultBeans) {
        showSearchResult(true);
        setData(true, hasMore, searchResultBeans);
    }

    @Override
    public void onLoadMoreSuccess(boolean hasMore, List<ArticleBean> searchResultBeans) {
        setData(false, hasMore, searchResultBeans);
    }

    private void setData(boolean refresh, boolean hasMore, List<ArticleBean> datas) {
        if (refresh) {
            mSearchAdapter.setEnableLoadMore(true);
            mSearchAdapter.setNewData(datas);
        } else {
            mSearchAdapter.addData(datas);
        }
        if (hasMore) {
            mSearchAdapter.loadMoreComplete();
        } else {
            mSearchAdapter.loadMoreEnd();
        }
    }

    @Override
    public void onBackPressed() {
        if (mTagFlowLayout.getVisibility() == View.VISIBLE) {
            super.onBackPressed();
        } else {
            showSearchResult(false);
        }
    }

    private void showSearchResult(boolean showSearchResult) {
        if (showSearchResult) {
            mRecyclerView.setVisibility(View.VISIBLE);
            mTagFlowLayout.setVisibility(View.GONE);
        } else {
            mRecyclerView.setVisibility(View.GONE);
            mTagFlowLayout.setVisibility(View.VISIBLE);
        }
    }
}
