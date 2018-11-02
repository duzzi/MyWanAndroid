package com.duzzi.mywanandroid.ui.adapter.rv;

import android.text.Html;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duzzi.mywanandroid.R;
import com.duzzi.mywanandroid.core.bean.data.ArticleBean;
import com.duzzi.mywanandroid.util.GlideUtils;

import javax.inject.Inject;

/**
 * 文件名: ArticleAdapter
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/26
 */
public class ArticleAdapter extends BaseQuickAdapter<ArticleBean, BaseViewHolder> {
    @Inject
    public ArticleAdapter() {
        super(R.layout.item_article);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleBean item) {
        helper.setText(R.id.tv_title, Html.fromHtml(item.getTitle()))
                .setText(R.id.tv_author, item.getAuthor())
                .setText(R.id.tv_date, item.getNiceDate());
        if (StringUtils.isTrimEmpty(item.getDesc())) {
            helper.getView(R.id.tv_desc).setVisibility(View.GONE);
        }else {
            helper.setText(R.id.tv_desc, item.getDesc());
        }
        boolean isProject = item.getSuperChapterName().contains(mContext.getString(R.string.open_project));
        ImageView ivType = (ImageView) helper.getView(R.id.iv_article_type);
        if (isProject) {
            GlideUtils.loadImage(mContext, mContext.getResources().getDrawable(R.drawable.ic_type_project), ivType);
        } else {
            GlideUtils.loadImage(mContext, mContext.getResources().getDrawable(R.drawable.ic_article), ivType);
        }
        if (item.getNiceDate().contains(mContext.getString(R.string.minute))
                || item.getNiceDate().contains(mContext.getString(R.string.hour))
                || item.getNiceDate().contains(mContext.getString(R.string.one_day))) {
            helper.getView(R.id.tv_new).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.tv_new).setVisibility(View.GONE);
        }
        ImageView ivCover = (ImageView) helper.getView(R.id.iv_cover);
        if (!StringUtils.isEmpty(item.getEnvelopePic())) {
            ivCover.setVisibility(View.VISIBLE);
            GlideUtils.loadCover(mContext, item.getEnvelopePic(), ivCover);
        } else {
            ivCover.setVisibility(View.GONE);
        }
    }
}
