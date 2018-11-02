package com.duzzi.mywanandroid.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.duzzi.mywanandroid.R;
import com.duzzi.mywanandroid.base.fragment.BaseFragment;
import com.duzzi.mywanandroid.core.bean.data.ArticleBean;
import com.duzzi.mywanandroid.core.bean.data.BannerBean;
import com.duzzi.mywanandroid.mvp.contract.ArticleListContract;
import com.duzzi.mywanandroid.mvp.contract.BannerContract;
import com.duzzi.mywanandroid.mvp.presenter.ArticleListPresenter;
import com.duzzi.mywanandroid.mvp.presenter.BannerPresenter;
import com.duzzi.mywanandroid.ui.activity.WebActivity;
import com.duzzi.mywanandroid.ui.adapter.rv.ArticleAdapter;
import com.duzzi.mywanandroid.ui.widget.SpaceItemDecoration;
import com.duzzi.mywanandroid.ui.widget.loadsir.ErrorCallback;
import com.duzzi.mywanandroid.ui.widget.loadsir.LoadingCallback;
import com.duzzi.mywanandroid.util.DLog;
import com.duzzi.mywanandroid.util.GlideUtils;
import com.kingja.loadsir.callback.SuccessCallback;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;

public class HomeFragment extends BaseFragment<BannerPresenter>
        implements BannerContract.IBannerView, ArticleListContract.IArticleListView {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Inject
    ArticleAdapter mAdapter;
    @Inject
    ArticleListPresenter mArticleListPresenter;
    private BGABanner mBgaBanner;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    protected void initPresenter() {
        mArticleListPresenter.attachView(this);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        initRecyclerView();
    }

    private void initRecyclerView() {
        mAdapter.setEnableLoadMore(false);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mAdapter.setOnLoadMoreListener(() -> mArticleListPresenter.loadMoreArticle(), mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(SizeUtils.dp2px(8)
                , SizeUtils.dp2px(12)));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                ArticleBean item = mAdapter.getItem(position);
                if (item != null) {
                    WebActivity.show(mContext, item.getLink());
                }
            }
        });
        mAdapter.addHeaderView(getBannerView());
        mSwipeRefreshLayout.setOnRefreshListener(this::getData);
    }

    private View getBannerView() {
        View view = getLayoutInflater().inflate(R.layout.hear_banner,
                ((ViewGroup) mRecyclerView.getParent()), false);
        mBgaBanner = (BGABanner) view.findViewById(R.id.banner_guide_content);
        mBgaBanner.setAdapter((BGABanner.Adapter<ImageView, BannerBean>)
                (banner, itemView, model, position) -> {
                    if (model != null) {
                        GlideUtils.loadBanner(mContext, model.getImagePath(), itemView);
                    }
                }
        );
        mBgaBanner.setDelegate((BGABanner.Delegate<ImageView, BannerBean>)
                (banner, itemView, model, position) -> {
                    if (model != null) {
                        WebActivity.show(mContext, model.getUrl());
                    }
                });
        return view;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void getData() {
        mPresenter.getBanner();
        mArticleListPresenter.getArticleList();
    }


    @Override
    protected void initInject() {
        mFragmentComponent.inject(this);
    }

    @Override
    public void onGetBannerSuccess(List<BannerBean> bannerBeanList) {
        mSwipeRefreshLayout.setRefreshing(false);
        DLog.d(bannerBeanList.toString());
        mBgaBanner.setData(bannerBeanList, null);
    }

    @Override
    public void showError() {
        super.showError();
        mSwipeRefreshLayout.setRefreshing(false);
        mBaseLoadService.showCallback(ErrorCallback.class);
    }

    @Override
    public void onGetArticleListSuccess(boolean hasMore, List<ArticleBean> articleBeanList) {
        mBaseLoadService.showSuccess();
        setData(true, hasMore, articleBeanList);
    }

    private void setData(boolean refresh, boolean hasMore, List<ArticleBean> articleBeanList) {
        if (refresh) {
            mAdapter.setNewData(articleBeanList);
        } else {
            mAdapter.addData(articleBeanList);
        }
        if (hasMore) {
            mAdapter.loadMoreComplete();
        } else {
            mAdapter.loadMoreEnd();
        }
    }

    @Override
    public void onLoadMoreArticleListSuccess(boolean hasMore, List<ArticleBean> articleBeanList) {
        setData(false, hasMore, articleBeanList);
    }
}
