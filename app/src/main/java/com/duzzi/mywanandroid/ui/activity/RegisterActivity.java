package com.duzzi.mywanandroid.ui.activity;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.duzzi.mywanandroid.R;
import com.duzzi.mywanandroid.base.activity.BaseActivity;
import com.duzzi.mywanandroid.core.bean.data.UserInfoBean;
import com.duzzi.mywanandroid.mvp.contract.RegisterContract;
import com.duzzi.mywanandroid.mvp.presenter.RegisterPresenter;


import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.IRegisterView {
    @BindView(R.id.et_username)
    AutoCompleteTextView mEtUsername;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.et_password_2)
    EditText mEtPassword2;
    @BindView(R.id.btn_register)
    Button mBtnRegister;
    @BindView(R.id.vg_back)
    RelativeLayout mVgBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_header_right)
    TextView mTvHeaderRight;
    @BindView(R.id.iv_menu)
    ImageView mIvMenu;
    @BindView(R.id.cl_header)
    ConstraintLayout mClHeader;
    @BindString(R.string.register)
    String mStringRegister;
    @BindString(R.string.register_success)
    String mStringRegisterSuccess;

    @Override
    protected void initInject() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mTvTitle.setText(mStringRegister);
        setClickBackListener(mVgBack);
    }

    @OnClick(R.id.btn_register)
    public void onClickRegister() {
        mPresenter.register(
                mEtUsername.getText().toString(),
                mEtPassword.getText().toString(),
                mEtPassword2.getText().toString()
        );
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void getData() {

    }

    @Override
    public void onRegisterSuccess(UserInfoBean userInfoBean) {
        ToastUtils.showShort(mStringRegisterSuccess);
        dismissProgressDialog();
        finish();
    }

    @Override
    public void onUsernameError(String msg) {
        setErrorAndFocus(mEtUsername, msg);
    }

    @Override
    public void onPasswordError(String msg) {
        setErrorAndFocus(mEtPassword, msg);
    }

    @Override
    public void onPassword2Error(String msg) {
        setErrorAndFocus(mEtPassword2, msg);
    }

    @Override
    public void showErrorMsg(String errorMsg) {
        super.showErrorMsg(errorMsg);
        setErrorAndFocus(mEtPassword, null);
        ToastUtils.showLong(errorMsg);
    }

    private void setErrorAndFocus(EditText etPassword, String msg) {
        dismissProgressDialog();
        etPassword.setError(msg);
        etPassword.requestFocus();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}

