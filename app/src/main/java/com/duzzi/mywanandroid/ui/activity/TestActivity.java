package com.duzzi.mywanandroid.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.duzzi.mywanandroid.R;
import com.duzzi.mywanandroid.base.activity.BaseActivity;
import com.duzzi.mywanandroid.mvp.presenter.EmptyPresenter;
import com.duzzi.mywanandroid.ui.fragment.BlankFragment;
import com.duzzi.mywanandroid.util.DLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestActivity extends BaseActivity<EmptyPresenter> {
    public static final String TAG = TestActivity.class.getSimpleName();

    @BindView(R.id.button)
    Button mButton;
    @BindView(R.id.button1)
    Button mButton1;
    @BindView(R.id.imageView)
    ImageView mImageView;


    @Override
    protected void initInject() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        getSupportFragmentManager().beginTransaction().add(R.id.fl_test, blankFragment).commitNow();
        Log.d(TAG, "initView: do something" );
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    BlankFragment blankFragment = BlankFragment.newInstance("sdfsd");

    @OnClick(R.id.button)
    public void onClickButton() {
        getSupportFragmentManager().beginTransaction().add(R.id.fl_test, blankFragment).commitNow();
    }

    boolean show;

    @OnClick(R.id.button1)
    public void onClickButton1() {
        if (show) {
            getSupportFragmentManager().beginTransaction().hide(blankFragment).commitNow();
        } else {
            getSupportFragmentManager().beginTransaction().show(blankFragment).commitNow();
        }
        show = !show;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected void getData() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

}
