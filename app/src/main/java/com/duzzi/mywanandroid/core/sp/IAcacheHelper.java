package com.duzzi.mywanandroid.core.sp;

/**
 * @author quchao
 * @date 2017/11/27
 */

public interface IAcacheHelper {

    void setLoginAccount(String account);

    String getLoginAccount();

    String getLoginPassword();

    void setLoginStatus(boolean isLogin);

    boolean getLoginStatus();

    void setCookie(String domain, String cookie);

    String getCookie(String domain);

    boolean getNightModeState();

    void setNightModeState(boolean b);


}
