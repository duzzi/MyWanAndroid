package com.duzzi.mywanandroid.exception;

/**
 * 文件名: CommonException
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/11/1
 */
public class CommonException extends Throwable {
    private int errorCode;

    public CommonException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }
}
