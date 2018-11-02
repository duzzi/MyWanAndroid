package com.duzzi.mywanandroid.ui.adapter.rv;

import android.text.Html;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duzzi.mywanandroid.R;
import com.duzzi.mywanandroid.core.bean.data.ArticleBean;
import com.duzzi.mywanandroid.core.constant.Constants;
import com.duzzi.mywanandroid.util.GlideUtils;

import java.util.Random;

import javax.inject.Inject;

/**
 * 文件名: ArticleAdapter
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/26
 */
public class NavigationAdapter extends BaseQuickAdapter<ArticleBean, BaseViewHolder> {

    @Inject
    public NavigationAdapter() {
        super(R.layout.item_navigation);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleBean item) {
        helper.setText(R.id.tv_title, Html.fromHtml(item.getTitle()));
        helper.setTextColor(R.id.tv_title, Constants.sColorArray[new Random().nextInt(5)]);
    }
}
