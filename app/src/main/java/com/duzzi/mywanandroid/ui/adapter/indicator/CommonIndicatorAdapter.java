package com.duzzi.mywanandroid.ui.adapter.indicator;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.Utils;
import com.duzzi.mywanandroid.R;
import com.duzzi.mywanandroid.util.CollectionUtils;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件名: CommonIndicatorAdapter
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/30
 */
public class CommonIndicatorAdapter extends CommonNavigatorAdapter {

    private final ViewPager mViewPager;
    private List<String> mStrings = new ArrayList<>();

    public CommonIndicatorAdapter(List<String> strings, ViewPager viewPager) {
        if (!CollectionUtils.isEmpty(strings)) mStrings.addAll(strings);
        mViewPager = viewPager;
    }

    @Override
    public int getCount() {
        return mStrings.size();
    }

    @Override
    public IPagerTitleView getTitleView(Context context, int i) {
        ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);
        clipPagerTitleView.setText(mStrings.get(i));
        clipPagerTitleView.setTextSize(SizeUtils.sp2px(16));
        clipPagerTitleView.setTextColor(Utils.getApp().getResources().getColor(R.color.textColor333));
        clipPagerTitleView.setClipColor(Utils.getApp().getResources().getColor(R.color.colorAccent));
        clipPagerTitleView.setOnClickListener(v -> mViewPager.setCurrentItem(i));
        return clipPagerTitleView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        return null;
    }
}
