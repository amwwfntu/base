package com.base.demo.common.exception;

public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 3382634379819296143L;

    public String errorCode = "0";

    public BusinessException(String errcode) {
        this.errorCode=errcode;
    }
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String message, String errcode) {
        super(message);
        this.errorCode=errcode;
    }
    public BusinessException(String message, Throwable cause, String errcode) {
        super(message, cause);
        this.errorCode=errcode;
    }
}
