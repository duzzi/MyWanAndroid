package com.duzzi.mywanandroid.util;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;


import com.duzzi.mywanandroid.R;

import java.util.Objects;

/**
 * 文件名: ShareUtil
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/11/5
 */
public class ShareUtil {

    public static void shareText(Context context, @NonNull String text) {
        Objects.requireNonNull(context);
        Objects.requireNonNull(text);
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        String appName = context.getResources().getString(R.string.app_name);
        sendIntent.putExtra(Intent.EXTRA_TEXT, String.format("【%s】：%s", appName, text));
        // 指定发送内容的类型
        sendIntent.setType("text/plain");
        context.startActivity(sendIntent);
    }
}
