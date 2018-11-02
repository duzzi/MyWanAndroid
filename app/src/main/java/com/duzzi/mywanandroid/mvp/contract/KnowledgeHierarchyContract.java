package com.duzzi.mywanandroid.mvp.contract;

import com.duzzi.mywanandroid.base.presenter.IBasePresenter;
import com.duzzi.mywanandroid.base.view.IBaseView;
import com.duzzi.mywanandroid.core.bean.data.KnowledgeHierarchyBean;

import java.util.List;

/**
 * 文件名: KnowledgeHierarchyContract
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/25
 */
public class KnowledgeHierarchyContract {
    public interface IKnowledgeHierarchyView extends IBaseView {
        void onGetKnowledgeHierarchySuccess(List<KnowledgeHierarchyBean> knowledgeHierarchyBeans);
    }

    public interface IKnowledgeHierarchyPresenter extends IBasePresenter<IKnowledgeHierarchyView> {
        void getKnowledgeHierarchy();
    }
}
