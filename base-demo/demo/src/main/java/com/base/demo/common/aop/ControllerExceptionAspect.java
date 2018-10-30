package com.base.demo.common.aop;

import com.base.demo.common.exception.BusinessException;
import com.base.demo.common.exception.BusinessExceptionCode;
import com.base.demo.common.pojo.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * 控制层异常处理
 */
@ControllerAdvice(basePackages = "com.base.demo.controller")
public class ControllerExceptionAspect {
	private final static Logger log = LoggerFactory.getLogger(ControllerExceptionAspect.class);

	@ExceptionHandler(Throwable.class)
	@ResponseBody
	public Object exception(Throwable e){
		CommonResponse result = new CommonResponse();
		if(e instanceof BusinessException){
			BusinessException ex = (BusinessException)e;
			result.setCode(ex.errorCode);
		} else if(e instanceof MethodArgumentTypeMismatchException || e instanceof ConversionNotSupportedException || e instanceof BindException){
			result.setCode(BusinessExceptionCode.ILLEGALITY_PARAM.getCode());
		} else if(e instanceof MethodArgumentNotValidException){
			StringBuilder msg = new StringBuilder();
			((MethodArgumentNotValidException) e).getBindingResult().getAllErrors().forEach(value->msg.append(value.getDefaultMessage()));
			result.setCode(BusinessExceptionCode.ILLEGALITY_PARAM.getCode());
		} else if(e instanceof DataAccessException){
			result.setCode(BusinessExceptionCode.INTERFACE_INNER_EXCEPTION.getCode());
		} else {
			log.error(e.getMessage(),e);
			result.setCode(BusinessExceptionCode.INTERFACE_INNER_EXCEPTION.getCode());
		}
		return result;
	}
}
