package com.base.demo.common.pojo;

import java.io.Serializable;

/**
 * 通用响应类
 * @param <T>
 */
public class CommonResponse<T> implements Serializable{

    private String code;

    private String msg;

    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
