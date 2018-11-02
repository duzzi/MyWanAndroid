package com.duzzi.mywanandroid.ui.adapter.vp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.duzzi.mywanandroid.ui.fragment.BlankFragment;
import com.duzzi.mywanandroid.ui.fragment.HierarchyArticleFragment;
import com.duzzi.mywanandroid.ui.fragment.ProjectListFragment;
import com.duzzi.mywanandroid.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件名: HierarchyFragmentAdapter
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/30
 */
public class ProjectFragmentAdapter extends FragmentPagerAdapter {

    private final List<Integer> mIds = new ArrayList<>();

    public ProjectFragmentAdapter(FragmentManager fm, List<Integer> ids) {
        super(fm);
        if (!CollectionUtils.isEmpty(ids)) {
            mIds.addAll(ids);
        }
    }

    @Override
    public Fragment getItem(int position) {
        ProjectListFragment projectListFragment = ProjectListFragment
                .newInstance(mIds.get(position));
        if (position == 0) {
            projectListFragment.setForceLoad(true);
        }
        return projectListFragment;
//        return BlankFragment.newInstance(String.valueOf(position));
    }

    @Override
    public int getCount() {
        return mIds.size();
    }
}
