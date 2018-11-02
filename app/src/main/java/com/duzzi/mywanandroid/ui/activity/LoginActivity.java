package com.duzzi.mywanandroid.ui.activity;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
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
import com.duzzi.mywanandroid.mvp.contract.LoginContract;
import com.duzzi.mywanandroid.mvp.presenter.LoginPresenter;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.ILoginView {


    @BindView(R.id.et_username)
    AutoCompleteTextView mEtUsername;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.btn_to_register)
    Button mBtnToRegister;
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
    @BindString(R.string.login)
    String mStringLogin;

    @Override
    protected void initInject() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mTvTitle.setText(mStringLogin);
        setClickBackListener(mVgBack);
        mEtPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void getData() {

    }

    @OnClick(R.id.btn_to_register)
    public void onClickRegster() {
        ActivityUtils.startActivity(this, RegisterActivity.class);
    }

    @OnClick(R.id.btn_login)
    public void attemptLogin() {
        showProgressDialog();
        mEtUsername.setError(null);
        mEtPassword.setError(null);
        mPresenter.login(mEtUsername.getText().toString(), mEtPassword.getText().toString());
    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);
        mEtUsername.setAdapter(adapter);
    }

    @Override
    public void onLoginSuccess(UserInfoBean userInfoBean) {
        dismissProgressDialog();
//        ActivityUtils.startActivity(this, MainActivity.class);
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

}

