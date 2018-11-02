package com.duzzi.mywanandroid.core.bean.base;

/**
 * 文件名: BaseResponse
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/24
 */
public class BaseResponse<T> {

    public static final int SUCCESS = 0;
    /**
     * errorCode : 0
     * errorMsg :
     */
    private T data;

    private int errorCode;
    private String errorMsg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
