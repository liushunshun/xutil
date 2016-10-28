package com.xy.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

/**
 * Created by XiuYang on 2016/10/28.
 */
public class VertXTest {
    public static void main(String[] args) {
        VertxOptions options = new VertxOptions().setWorkerPoolSize(40);
        Vertx vertx = Vertx.vertx(options);

        vertx.setPeriodic(1000,id->{
            System.out.println("hello VertX :"+id);
        });
    }
}
