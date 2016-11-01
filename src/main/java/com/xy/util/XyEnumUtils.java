package com.xy.util;

import org.apache.commons.lang3.EnumUtils;

import java.util.Map;

/**
 * Created by XiuYang on 2016/11/1.
 */
public class XyEnumUtils {
    public static void main(String[] args) {
        EnumUtils.getEnumList(Type.class).stream().forEach(System.out::println);

        Type type = EnumUtils.getEnum(Type.class,"TIANJIN");

        Map<String,Type> map = EnumUtils.getEnumMap(Type.class);
        for(String key : map.keySet()){
            System.out.println(map.get(key).name());
        }
    }

    enum Type{
        BEIJING,
        TIANJIN,
        SHANGHAI
    }
}
