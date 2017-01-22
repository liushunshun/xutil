package com.xy;

import org.apache.commons.lang3.AnnotationUtils;
import org.apache.commons.lang3.EnumUtils;

import java.util.Map;

/**
 * Created by XiuYang on 2016/11/1.
 */
@Xy
public class XyEnumUtils {
    public static void main(String[] args) {
        EnumUtils.getEnumList(Type.class).stream().forEach(System.out::println);

        Type type = EnumUtils.getEnum(Type.class,"TIANJIN");

        Map<String,Type> map = EnumUtils.getEnumMap(Type.class);
        for(String key : map.keySet()){
            System.out.println(map.get(key).name());
        }
        System.out.println(AnnotationUtils.isValidAnnotationMemberType(Xy.class));

    }

    enum Type{
        BEIJING,
        TIANJIN,
        SHANGHAI
    }
}
