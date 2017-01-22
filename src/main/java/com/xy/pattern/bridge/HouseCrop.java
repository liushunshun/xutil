package com.xy.pattern.bridge;

/**
 * Created by XiuYang on 2016/9/26.
 */
public class HouseCrop extends Crop {

    public HouseCrop(Product product) {
        super(product);
    }

    @Override
    public void makeMoney() {
        super.makeMoney();
        System.out.println("房地产公司赚了很多钱...");
    }
}
