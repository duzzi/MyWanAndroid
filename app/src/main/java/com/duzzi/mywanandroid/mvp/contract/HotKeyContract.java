package com.duzzi.mywanandroid.mvp.contract;

import com.duzzi.mywanandroid.base.presenter.IBasePresenter;
import com.duzzi.mywanandroid.base.view.IBaseView;
import com.duzzi.mywanandroid.core.bean.data.HotKeyBean;

import java.util.List;

/**
 * 文件名: HotKeyContract
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/25
 */
public class HotKeyContract {
    public interface IHotKeyView extends IBaseView {
        void onGetHotKeySuccess(List<HotKeyBean> hotKeyBeanList);
    }

    public interface IHotKeyPresenter extends IBasePresenter<IHotKeyView> {
        void getHotKey();
    }
}
