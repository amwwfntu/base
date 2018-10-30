package com.base.demo.common.exception;


import org.apache.commons.lang3.StringUtils;

public enum BusinessExceptionCode {

    RPC_ERROR("-999998", " 远程调用异常"),
    SYSTEM_ERROR("-999999", " 系统异常"),
    SUCCESS("0", " 正常接收成功或处理完成"),
    ILLEGALITY_PARAM("E1000", " 请求参数非法"),
    REPET_REQUEST("E1001", " 重复请求"),
    NOT_SUPPORT("E1005", " 不支持的调用"),
    BEING_PROCESSED("E1006", " 正在处理中"),
    NO_AUTH("E3000", " 请求来源未经过授权"),
    INTERFACE_INNER_FAIL("F1000", " 接口内部处理失败"),
    INTERFACE_INNER_EXCEPTION("F1099", " 接口内部处理异常")
    ;

    private static final String EMPTY = "";
    private String code;
    private String text;

    private BusinessExceptionCode(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public static String getText(String code) {
        if (StringUtils.isEmpty(code)) {
            return "";
        }
        for (BusinessExceptionCode em : values()) {
            if (em.code.equals(code)) {
                return em.text;
            }
        }
        return "";
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
