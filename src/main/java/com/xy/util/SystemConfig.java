package com.xy.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by XiuYang on 2016/11/1.
 */
public class SystemConfig {
    /**
     * 获取JVM参数变量或操作系统环境变量
     * @param key
     * @return
     */
    public static String getValue(String key){
        String value = System.getProperty(key); //-D加参数
        if(StringUtils.isEmpty(value)){
            value = System.getenv(key);//操作系统环境变量
        }
        return value;
    }
    /*public static void main(String[] args) {
        Enumeration enumeration = System.getProperties().keys();
        while(enumeration.hasMoreElements()){
            String key = enumeration.nextElement().toString();
            System.out.println(key +"="+System.getProperty(key));
        }
        System.out.println(System.getenv("path"));
    }*/
}
