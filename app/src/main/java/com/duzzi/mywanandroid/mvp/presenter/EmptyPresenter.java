package com.duzzi.mywanandroid.mvp.presenter;

import com.duzzi.mywanandroid.base.presenter.BasePresenter;
import com.duzzi.mywanandroid.util.DLog;

import javax.inject.Inject;

/**
 * 文件名: EmptyPresenter
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/23
 */
public class EmptyPresenter extends BasePresenter {
    @Inject
    public EmptyPresenter() {
    }

    public void test() {
        DLog.d("###############test############");
    }
}
