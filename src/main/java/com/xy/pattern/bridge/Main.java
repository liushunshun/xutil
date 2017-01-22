package com.xy.pattern.bridge;

/**
 * Created by XiuYang on 2016/9/26.
 */
public class Main {
    /**
     * 将抽象部分与实现部分分离，使它们都可以独立的变化。
     */
    public static void main(String[] args) {
        Crop crop = new HouseCrop(new House());
        crop.makeMoney();
    }
}
