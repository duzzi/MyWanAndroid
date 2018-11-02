package com.duzzi.mywanandroid.mvp.presenter;

import com.duzzi.mywanandroid.base.presenter.BasePresenter;
import com.duzzi.mywanandroid.core.DataManager;
import com.duzzi.mywanandroid.core.bean.data.ProjectsBean;
import com.duzzi.mywanandroid.core.constant.Constants;
import com.duzzi.mywanandroid.mvp.contract.ProjectListContract;
import com.duzzi.mywanandroid.util.CollectionUtils;
import com.duzzi.mywanandroid.util.RxUtils;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * 文件名: ProjectListPresenter
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/30
 */
public class ProjectListPresenter extends BasePresenter<ProjectListContract.IProjectView> implements ProjectListContract.IProjectListPresenter {

    private int mCid;

    @Inject
    public ProjectListPresenter() {
    }

    @Override
    public void getProjectList(int cid) {
        mCid = cid;
        resetCurrentPage();
        getProjectListInternal(false, cid);
    }

    private void getProjectListInternal(boolean loadMore, int cid) {
        addSubscribe(DataManager.getInstance().getProjects(getCurrentPage() + 1, cid)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult()).subscribe(new Consumer<ProjectsBean>() {
                    @Override
                    public void accept(ProjectsBean projectsBean) throws Exception {
                        if (isViewAttached()) {
                            boolean hasMore = !projectsBean.isOver();
                            pageIncrease();
                            if (!loadMore) {
                                if (CollectionUtils.isEmpty(projectsBean.getDatas())) {
                                    getView().showEmptyView();
                                } else {
                                    getView().onGetProjectListSuccess(hasMore, projectsBean.getDatas());
                                }
                            } else {
                                getView().onLoadMore(hasMore, projectsBean.getDatas());
                            }

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
    public void loadMore() {
        getProjectListInternal(true, mCid);
    }
}
