package com.duzzi.mywanandroid.mvp.presenter;

import com.duzzi.mywanandroid.base.presenter.BasePresenter;
import com.duzzi.mywanandroid.core.DataManager;
import com.duzzi.mywanandroid.core.bean.data.KnowledgeHierarchyBean;
import com.duzzi.mywanandroid.mvp.contract.KnowledgeHierarchyContract;
import com.duzzi.mywanandroid.util.RxUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * 文件名: KnowledgeHierarchyPresenter
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/26
 */
public class KnowledgeHierarchyPresenter extends BasePresenter<KnowledgeHierarchyContract.
        IKnowledgeHierarchyView> implements KnowledgeHierarchyContract.IKnowledgeHierarchyPresenter {
    @Inject
    public KnowledgeHierarchyPresenter() {
    }

    @Override
    public void getKnowledgeHierarchy() {
        addSubscribe(DataManager.getInstance().getKnowledgeHierarchy()
                .compose(RxUtils.rxSchedulerHelper())
        .compose(RxUtils.handleResult()).subscribe(new Consumer<List<KnowledgeHierarchyBean>>() {
                    @Override
                    public void accept(List<KnowledgeHierarchyBean> knowledgeHierarchyBeans) throws Exception {
                        if (isViewAttached()) {
                            getView().onGetKnowledgeHierarchySuccess(knowledgeHierarchyBeans);
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
