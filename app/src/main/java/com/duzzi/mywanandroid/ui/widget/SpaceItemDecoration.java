package com.duzzi.mywanandroid.ui.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.SizeUtils;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int space;
    private int mSpaceTopBottom;
    private int mSpaceLeftRight;
    private boolean mShowTopSpace;

    public SpaceItemDecoration() {
        this(SizeUtils.dp2px(8)
                , SizeUtils.dp2px(12),true);
    }

    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    public SpaceItemDecoration(int spaceTopBottom, int spaceLeftRight) {
        this(spaceTopBottom, spaceLeftRight, true);
    }

    /**
     * @param spaceTopBottom
     * @param spaceLeftRight
     * @param showTopSpace   是否需要显示最顶部的间隔
     */
    public SpaceItemDecoration(int spaceTopBottom, int spaceLeftRight, boolean showTopSpace) {
        this.mSpaceTopBottom = spaceTopBottom;
        this.mSpaceLeftRight = spaceLeftRight;
        mShowTopSpace = showTopSpace;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = space == 0 ? (mShowTopSpace ? mSpaceTopBottom * 2 : 0) : space;
        } else {
            outRect.top = space == 0 ? mSpaceTopBottom :space;
        }
        outRect.bottom = space == 0 ? mSpaceTopBottom : space;
        outRect.left = space == 0 ? mSpaceLeftRight : space;
        outRect.right = space == 0 ? mSpaceLeftRight : space;
    }
}