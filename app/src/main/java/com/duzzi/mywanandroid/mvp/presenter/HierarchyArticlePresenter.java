package com.duzzi.mywanandroid.mvp.presenter;

import com.duzzi.mywanandroid.base.presenter.BasePresenter;
import com.duzzi.mywanandroid.core.DataManager;
import com.duzzi.mywanandroid.core.bean.data.HierarchyArticleListBean;
import com.duzzi.mywanandroid.mvp.contract.HierarchyArticleContract;
import com.duzzi.mywanandroid.util.RxUtils;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * 文件名: HierarchyArticlePresenter
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/29
 */
public class HierarchyArticlePresenter extends BasePresenter<HierarchyArticleContract.IHierarchyArticleView>
        implements HierarchyArticleContract.IHierarchyArticlePresenter {

    private int mCid;

    @Inject
    public HierarchyArticlePresenter() {
    }


    @Override
    public void getHierarchyArticle(int cid) {
        mCid = cid;
        getHierarchyArticleInternal(cid);

    }

    private void getHierarchyArticleInternal(int cid) {
        addSubscribe(DataManager.getInstance().getHierarchyArticle(getCurrentPage(),cid)
                .compose(RxUtils.rxSchedulerHelper())
        .compose(RxUtils.handleResult()).subscribe(new Consumer<HierarchyArticleListBean>() {
                    @Override
                    public void accept(HierarchyArticleListBean hierarchyArticleListBean) throws Exception {
                        if (isViewAttached()) {
                            boolean hasMore = hierarchyArticleListBean.getCurPage() < hierarchyArticleListBean.getPageCount();
                            getView().onGetHierarchyArticleSuccess(hasMore,hierarchyArticleListBean.getDatas());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (isViewAttached()) {
                            getView().showError();
                        }
                    }
                }));
    }

    @Override
    public void loadMoreHierarchyArticle() {
        getHierarchyArticleInternal(mCid);
    }
}
