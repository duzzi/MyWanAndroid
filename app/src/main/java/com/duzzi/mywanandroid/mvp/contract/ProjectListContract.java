package com.duzzi.mywanandroid.mvp.contract;

import com.duzzi.mywanandroid.base.presenter.IBasePresenter;
import com.duzzi.mywanandroid.base.view.IBaseView;
import com.duzzi.mywanandroid.core.bean.data.ArticleBean;

import java.util.List;

/**
 * 文件名: ProjectListContract
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/25
 */
public class ProjectListContract {
    public interface IProjectView extends IBaseView {
        void onGetProjectListSuccess(boolean hasMore, List<ArticleBean> projectBeans);

        void onLoadMore(boolean hasMore, List<ArticleBean> projectBeans);
    }

    public interface IProjectListPresenter extends IBasePresenter<IProjectView> {
        void getProjectList(int cid);

        void loadMore();
    }
}
