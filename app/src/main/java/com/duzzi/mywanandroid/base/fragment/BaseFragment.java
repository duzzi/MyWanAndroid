package com.duzzi.mywanandroid.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.duzzi.mywanandroid.R;
import com.duzzi.mywanandroid.base.presenter.BasePresenter;
import com.duzzi.mywanandroid.base.view.IBaseView;
import com.duzzi.mywanandroid.di.component.DaggerFragmentComponent;
import com.duzzi.mywanandroid.di.component.FragmentComponent;
import com.duzzi.mywanandroid.ui.widget.loadsir.LoadingCallback;
import com.duzzi.mywanandroid.util.DLog;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 文件名: BaseFragment
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/6/6
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IBaseView {
    @Inject
    protected P mPresenter;
    private Unbinder mBind;
    protected Context mContext;
    public View mEmptyView;
    public TextView mTvEmpty;
    public ImageView mIvEmpty;
    protected FragmentComponent mFragmentComponent;
    protected LoadService mBaseLoadService;
    private View mRootView;
    private boolean reuseView = true;
    private boolean isFragmentVisible = false;
    private boolean isFirstLoad = true;
    private boolean forceLoad = false;
    private boolean isPrepared;

    public boolean isReuseView() {
        return reuseView;
    }

    public void setReuseView(boolean reuseView) {
        this.reuseView = reuseView;
    }

    protected abstract void initPresenter();

    protected abstract void initView(View view, Bundle savedInstanceState);

    protected abstract int getLayoutId();

    protected abstract void getData();

    protected abstract void initInject();

    protected void reloadData() {
        mBaseLoadService.showCallback(LoadingCallback.class);
        getData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initFragmentComponent();
        initInject();
        mContext = context;
        if (mPresenter != null) {
            DLog.d("onAttach: " + mPresenter + " " + this.hashCode());
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DLog.d("" + this.hashCode());
        Bundle bundle = getArguments();
        if (bundle != null && bundle.size() > 0) {
            initVariables(bundle);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DLog.d(" " + this.hashCode());
        if (reuseView) {
            if (mRootView == null) {
                onCreateViewInternal(savedInstanceState);
            } else {
                ViewGroup parent = (ViewGroup) mRootView.getParent();
                if (parent != null) {
                    parent.removeView(mRootView);
                }
            }
        } else {
            onCreateViewInternal(savedInstanceState);
        }
        return mRootView;
    }

    private void onCreateViewInternal(Bundle savedInstanceState) {
        isFirstLoad = true;
        View dataView = View.inflate(getActivity(), getLayoutId(), null);
        mBaseLoadService = LoadSir.getDefault().register(dataView, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                reloadData();
            }
        });
        mRootView = mBaseLoadService.getLoadLayout();
        mBind = ButterKnife.bind(this, dataView);
        initPresenter();
        checkPresenterIsNull();
        initView(mRootView, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DLog.d("" + this.hashCode());
        DLog.d(this.hashCode() +" isFragmentVisible "+isFragmentVisible+ " getUserVisibleHint(): " + getUserVisibleHint() + " forceLoad " + forceLoad + "--------------------isFirstLoad " + isFirstLoad);
        isPrepared = true;
        if (forceLoad) {
            if (isFirstLoad)
                getData();
        } else {
            lazyLoad();
        }
    }

    /**
     * viewpager回调该方法
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        DLog.d("isVisibleToUser: " + isVisibleToUser + " " + this.hashCode());
        if (isVisibleToUser) {
            onVisible();
        } else {
            onInvisible();
        }
    }

    /**
     * 如果是通过FragmentTransaction的show和hide的方法来控制显示，调用的是onHiddenChanged.
     * 若是初始就show的Fragment 为了触发该事件 需要先hide再show
     *
     * @param hidden hidden True if the fragment is now hidden, false if it is not
     *               visible.
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        DLog.d("isHidden: " + hidden + " " + this.hashCode());
        if (!hidden) {
            onVisible();
        } else {
            onInvisible();
        }
    }

    protected void onVisible() {
        isFragmentVisible = true;
        if (!forceLoad) lazyLoad();
    }

    protected void onInvisible() {
        isFragmentVisible = false;
    }

    protected void lazyLoad() {
        if (isPrepared && isFragmentVisible) {
            if (isFirstLoad) {
                isFirstLoad = false;
                DLog.d(this.hashCode() + "================lazyLoad=====================");
                getData();
            }
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        DLog.d("" + this.hashCode());
    }

    @Override
    public void onPause() {
        super.onPause();
        DLog.d("" + this.hashCode());
    }

    @Override
    public void onStop() {
        super.onStop();
        DLog.d("" + this.hashCode());
    }

    @Override
    public void onResume() {
        super.onResume();
        DLog.d("" + this.hashCode());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        DLog.d("" + this.hashCode());
        //不复用的Fragment
        if (!reuseView) {
            unBind();
        }
    }

    //ViewPager缓存机制，被销毁的Fragment只会走到onDestroyView
    @Override
    public void onDestroy() {
        super.onDestroy();
        DLog.d("" + this.hashCode());
        unBind();
    }

    private void unBind() {
        isPrepared = false;
        if (mBind != null && mBind != Unbinder.EMPTY) {
            mBind.unbind();
            mBind = null;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        DLog.d("detachView " + mPresenter + " " + this.hashCode());
        if (mPresenter != null) mPresenter.detachView();
    }

    protected void initStatusBarPlaceHeight(View view) {
//        ViewGroup.LayoutParams params = view.getLayoutParams();
//        params.height = StatusBarUtil.getStatusBarHeight(Utils.getContext());
//        view.setLayoutParams(params);
    }

    protected void inflateEmptyView(ViewGroup viewGroup) {
//        mEmptyView = getLayoutInflater().inflate(R.layout.layout_empty, viewGroup, false);
//        mIvEmpty = (ImageView) mEmptyView.findViewById(R.id.iv_empty);
//        mTvEmpty = (TextView) mEmptyView.findViewById(R.id.tv_empty);
    }

    public void refresh() {

    }

    private void checkPresenterIsNull() {
//        if (mPresenter == null) {
//            throw new IllegalStateException("please init mPresenter in initPresenter() method ");
//        }
    }

    private void initFragmentComponent() {
        mFragmentComponent = DaggerFragmentComponent.create();
    }


    public void initVariables(Bundle bundle) {
    }


    public void setForceLoad(boolean forceLoad) {
        this.forceLoad = forceLoad;
    }


    //################################################################################
    @Override
    public void useNightMode(boolean isNight) {

    }

    @Override
    public void showErrorMsg(String errorMsg) {
    }

    @Override
    public void showNormal() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void reload() {

    }

    @Override
    public void showCollectSuccess() {

    }

    @Override
    public void showCancelCollectSuccess() {

    }

    @Override
    public void showLoginView() {

    }

    @Override
    public void showLogoutView() {

    }

    @Override
    public void showToast(String message) {
    }

    @Override
    public void showSnackBar(String message) {
    }

    @Override
    public void showEmptyView() {
    }

}
