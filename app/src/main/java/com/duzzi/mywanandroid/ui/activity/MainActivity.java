package com.duzzi.mywanandroid.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.duzzi.mywanandroid.R;
import com.duzzi.mywanandroid.base.activity.BaseActivity;
import com.duzzi.mywanandroid.core.DataManager;
import com.duzzi.mywanandroid.core.bean.event.EventMessage;
import com.duzzi.mywanandroid.core.constant.Constants;
import com.duzzi.mywanandroid.mvp.presenter.EmptyPresenter;
import com.duzzi.mywanandroid.ui.fragment.HierarchyFragment;
import com.duzzi.mywanandroid.ui.fragment.HomeFragment;
import com.duzzi.mywanandroid.ui.fragment.NavigationFragment;
import com.duzzi.mywanandroid.ui.fragment.ProjectContainerFragment;
import com.duzzi.mywanandroid.util.ShareUtil;
import com.duzzi.uilib.bottombar.BottomBarLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity<EmptyPresenter>
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fl_container)
    FrameLayout mFlContainer;
    @BindView(R.id.bottom_bar_layout)
    BottomBarLayout mBottomBarLayout;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    private long mFirstTime;
    private FragmentManager mSupportFragmentManager;
    private ArrayList<Fragment> mFragments;
    private ImageView mIvAvatar;
    private TextView mTvAccount;


    @Override
    protected void initInject() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceive(EventMessage eventMessage) {
        if (eventMessage != null) {
            if (eventMessage.getEventType() == EventMessage.EVENT_REFRESH_USER_INFO) {
                mTvAccount.setText(DataManager.getInstance().getLoginAccount());
            }else if (eventMessage.getEventType()==EventMessage.EVENT_LOGOUT) {
                mTvAccount.setText(R.string.click_to_login);
            }
        }
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setSupportActionBar(mToolbar);
        initDrawer();
        initBottomBar();
    }

    private void initDrawer() {
        View headerView = mNavView.inflateHeaderView(R.layout.nav_header_main);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        mIvAvatar = headerView.findViewById(R.id.iv_avatar);
        mTvAccount = headerView.findViewById(R.id.tv_account);

        mIvAvatar.setOnClickListener(this);
        mTvAccount.setOnClickListener(this);
        if (DataManager.getInstance().getLoginStatus()) {
            mTvAccount.setText(DataManager.getInstance().getLoginAccount());
        }
        toggle.syncState();
        mNavView.setNavigationItemSelectedListener(this);
    }


    private void initBottomBar() {
        mSupportFragmentManager = getSupportFragmentManager();
        mFragments = new ArrayList<>(4);
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(NavigationFragment.newInstance());
        mFragments.add(HierarchyFragment.newInstance());
        mFragments.add(ProjectContainerFragment.newInstance());
        mBottomBarLayout.setOnItemSelectedListener(onItemSelectedListener);
        for (Fragment fragment : mFragments) {
            mSupportFragmentManager.beginTransaction().add(R.id.fl_container, fragment)
                    .commitAllowingStateLoss();
        }
        selectTab(0);
    }

    private void selectTab(int index) {
        FragmentTransaction fragmentTransaction = mSupportFragmentManager.beginTransaction();
        try {
            for (Fragment fragment : mFragments) {
                fragmentTransaction.hide(fragment);
            }
            fragmentTransaction.show(mFragments.get(index));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    BottomBarLayout.OnItemSelectedListener onItemSelectedListener = (bottomBarItem,
                                                                     previousPosition,
                                                                     currentPosition) -> {
        if (previousPosition == currentPosition) {
            ToastUtils.showShort("重复点击：" + currentPosition);
        } else {
            selectTab(currentPosition);
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void getData() {

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (System.currentTimeMillis() - mFirstTime > 2000) {
                ToastUtils.showShort("再按一次退出程序");
                mFirstTime = System.currentTimeMillis();
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_common_website:
                ActivityUtils.startActivity(this, CommonWebsiteActivity.class);
                break;
            case R.id.action_search:
                ActivityUtils.startActivity(this, SearchActivity.class);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_collect_list:
                Snackbar.make(mToolbar, R.string.todo,Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.nav_setting:
                ActivityUtils.startActivity(this, SettingsActivity.class);
                break;
            case R.id.nav_share:
                ShareUtil.shareText(this, Constants.APK_URL);
                break;
            case R.id.nav_about:
                ActivityUtils.startActivity(this, AboutActivity.class);
                break;
            default:
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_account:
            case R.id.iv_avatar:
                if (!DataManager.getInstance().getLoginStatus()) {
                    ActivityUtils.startActivity(this, LoginActivity.class);
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                }
                break;
            default:
                break;
        }
    }
}
