package com.xy.pattern.bridge;

/**
 * Created by XiuYang on 2016/9/26.
 */
public class House implements Product {

    @Override
    public void beProduced() {
        System.out.println("盖房子。。。");
    }

    @Override
    public void beCelled() {
        System.out.println("买房子...");
    }
}
