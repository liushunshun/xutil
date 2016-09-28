package com.xy.pattern.bridge;

/**
 * Created by XiuYang on 2016/9/26.
 */
public class Crop {

    private Product product;

    public Crop(Product product){
        this.product = product;
    }

    public void makeMoney(){
        this.product.beProduced();
        this.product.beCelled();
    }
}
