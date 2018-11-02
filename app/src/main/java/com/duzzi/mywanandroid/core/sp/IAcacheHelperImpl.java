package com.duzzi.mywanandroid.core.sp;

import com.blankj.utilcode.util.Utils;
import com.duzzi.mywanandroid.core.constant.Constants;
import com.duzzi.mywanandroid.util.ACache;

import org.json.JSONArray;

/**
 * 文件名: IAcacheHelperImpl
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/11/2
 */
public class IAcacheHelperImpl implements IAcacheHelper {

    private static IAcacheHelperImpl sAcacheHelper;

    public static IAcacheHelperImpl getInstance() {
        if (sAcacheHelper == null) {
            synchronized (IAcacheHelperImpl.class) {
                if (sAcacheHelper == null) {
                    sAcacheHelper = new IAcacheHelperImpl();
                }
            }
        }
        return sAcacheHelper;
    }

    @Override
    public void setLoginAccount(String account) {
        ACache.get(Utils.getApp()).put(Constants.Key.USERNAME,account);
    }

    @Override
    public String getLoginAccount() {
        return ACache.get(Utils.getApp()).getAsString(Constants.Key.USERNAME);
    }

    @Override
    public String getLoginPassword() {
        return null;
    }

    @Override
    public void setLoginStatus(boolean isLogin) {
        ACache.get(Utils.getApp()).put(Constants.Key.LOGIN_STATUS, String.valueOf(isLogin));
    }

    @Override
    public boolean getLoginStatus() {
        return Boolean.valueOf(ACache.get(Utils.getApp()).getAsString(Constants.Key.LOGIN_STATUS));
    }

    @Override
    public void setCookie(String domain, String cookie) {

    }

    @Override
    public String getCookie(String domain) {
        return null;
    }

    @Override
    public boolean getNightModeState() {
        return false;
    }

    @Override
    public void setNightModeState(boolean b) {

    }
}
