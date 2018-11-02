package com.duzzi.mywanandroid.ui.adapter.rv;

import android.util.TypedValue;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duzzi.mywanandroid.R;
import com.duzzi.mywanandroid.core.bean.data.HierarchyChildrenBean;
import com.duzzi.mywanandroid.core.bean.data.KnowledgeHierarchyBean;
import com.duzzi.mywanandroid.util.CollectionUtils;
import com.duzzi.uilib.viewgroup.FlowLayout;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import static com.duzzi.mywanandroid.core.constant.Constants.sColorArray;

/**
 * 文件名: ArticleAdapter
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/26
 */
public class HierarchyAdapter extends BaseQuickAdapter<KnowledgeHierarchyBean, BaseViewHolder> {
    @Inject
    public HierarchyAdapter() {
        super(R.layout.item_hierarchy);
    }

    @Override
    protected void convert(BaseViewHolder helper, KnowledgeHierarchyBean item) {
        helper.setText(R.id.tv_parent_chapter, item.getName());
        FlowLayout flowLayout = (FlowLayout) helper.getView(R.id.flow_layout);
        List<HierarchyChildrenBean> children = item.getChildren();
        if (!CollectionUtils.isEmpty(children)) {
            flowLayout.removeAllViews();
            for (HierarchyChildrenBean hierarchyChildrenBean : children) {
                TextView textView = new TextView(mContext);
                textView.setText(hierarchyChildrenBean.getName());
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
                textView.setTextColor(mContext.getResources().getColor(sColorArray[new Random().nextInt(5)]));
                flowLayout.addView(textView);
            }
        }
    }
}
