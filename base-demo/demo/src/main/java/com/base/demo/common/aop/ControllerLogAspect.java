package com.base.demo.common.aop;

import com.alibaba.fastjson.JSONObject;
import com.base.demo.common.util.GenUUIDUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 统计接口耗时与访问日志AOP
 *
 * @author hwk
 * @date 2018-03-10
 */
@Aspect
@Component
public class ControllerLogAspect {
    private Logger log = LoggerFactory.getLogger(ControllerLogAspect.class);

    @Pointcut("execution(* com.base.demo.controller.*.*(..))" )
    private void doAction(){}

    @Around("doAction()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable{
        Object result;
        long current = System.currentTimeMillis();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getRemoteAddr();
        String sameReqNo = GenUUIDUtils.getSameReqNo();
        if(!request.getRequestURI().contains("/redirect/url")){
            String params = JSONObject.toJSONString(joinPoint.getArgs());
            String sb =sameReqNo+
                    "===>[Request]:" +
                    request.getMethod() +
                    "[" + ip + "|" +
                    request.getCharacterEncoding() +
                    "]" +
                    request.getRequestURI() +
                    "--" +
                    params;
            log.info(sb);
        }
        result = joinPoint.proceed();
        log.info(sameReqNo+"<===[Response](cost:"+(System.currentTimeMillis()-current)+"):"+JSONObject.toJSONString(result));
        return result;
    }
}
