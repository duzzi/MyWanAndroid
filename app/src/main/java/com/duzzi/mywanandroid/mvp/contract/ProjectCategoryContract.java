package com.duzzi.mywanandroid.mvp.contract;

import com.duzzi.mywanandroid.base.presenter.IBasePresenter;
import com.duzzi.mywanandroid.base.view.IBaseView;
import com.duzzi.mywanandroid.core.bean.data.ProjectCategoryBean;

import java.util.List;

/**
 * 文件名: ProjectCategoryContract
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/25
 */
public class ProjectCategoryContract {
    public interface IProjectCategoryView extends IBaseView {
        void onGetProjectCategorySuccess(List<ProjectCategoryBean> projectCategoryBeans);
    }

    public interface IProjectCategoryPresenter extends IBasePresenter<IProjectCategoryView> {
        void getProjectCategory();
    }
}
