package com.duzzi.mywanandroid.core.constant;

import com.duzzi.mywanandroid.R;

/**
 * 文件名: Constants
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/23
 */
public class Constants {
    public static final int DEFAULT_NEXT_PAGE = 1;
    public static final int DEFAULT_TIME_OUT = 10 * 1000;
    public static String APK_URL = "https://www.pgyer.com/fnvM";
    public static final String HOST_WANANDROID = "http:www.wanandroid.com/";
    public static int[] sColorArray = {
            R.color.red,
            R.color.colorAccent,
            R.color.purple,
            R.color.blue,
            R.color.green,
            R.color.orange
    };

    public static class IntentKey {
        public static final String LINK_URL = "LINK_URL";
        public static final String CID = "CID";
        public static final String HIERARCHY_CHILDREN_BEAN_LIST = "HIERARCHY_CHILDREN_BEAN_LIST";
    }

    public static class Key {
        public static final String USER_INFO = "USER_INFO";
        public static final String USERNAME = "USERNAME";
        public static final String LOGIN_STATUS = "LOGIN_STATUS";
        public static final String USERNAME_ARRAY = "USERNAME_ARRAY";
    }
}
