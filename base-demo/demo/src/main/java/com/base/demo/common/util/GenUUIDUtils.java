package com.base.demo.common.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GenUUIDUtils {

    /**
     * 获取请求标识对号
     *
     * @return
     */
    public static String getSameReqNo(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) +
                RandomStringUtils.random(4, false, true);
    }

    /**
     * 请求流水号 6位标识号+13位时间戳+7位随机数
     *
     * @param origin 渠道标识号
     * @return
     */
    public static String getBillNo(String origin){
        return  origin + System.currentTimeMillis() +
                RandomStringUtils.random(7, false, true);
    }
    
}
