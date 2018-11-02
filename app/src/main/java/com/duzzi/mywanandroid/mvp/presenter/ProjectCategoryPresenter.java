package com.duzzi.mywanandroid.mvp.presenter;

import com.duzzi.mywanandroid.base.presenter.BasePresenter;
import com.duzzi.mywanandroid.core.DataManager;
import com.duzzi.mywanandroid.core.bean.data.ProjectCategoryBean;
import com.duzzi.mywanandroid.mvp.contract.ProjectCategoryContract;
import com.duzzi.mywanandroid.util.RxUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * 文件名: ProjectCategoryPresenter
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/30
 */
public class ProjectCategoryPresenter extends BasePresenter<ProjectCategoryContract.IProjectCategoryView> implements
        ProjectCategoryContract.IProjectCategoryPresenter {
    @Inject
    public ProjectCategoryPresenter() {

    }

    @Override
    public void getProjectCategory() {
        addSubscribe(DataManager.getInstance().getProjectCategory()
                .compose(RxUtils.rxSchedulerHelper())
        .compose(RxUtils.handleResult()).subscribe(new Consumer<List<ProjectCategoryBean>>() {
                    @Override
                    public void accept(List<ProjectCategoryBean> projectCategoryBeans) throws Exception {
                        if (isViewAttached()) {
                            getView().onGetProjectCategorySuccess(projectCategoryBeans);
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
}
