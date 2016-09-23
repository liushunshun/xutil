package com.xy.util;

/**
 * Created by XiuYang on 2016/9/14.
 */
public class Args {

    public static void check(final boolean expression,final String message){
        if(!expression){
            throw new IllegalArgumentException(message);
        }
    }

    public static <T> T notNull(final T argument,final String name){
        if(argument==null){
            throw new IllegalArgumentException(name+" may not be null");
        }
        return argument;
    }
}
