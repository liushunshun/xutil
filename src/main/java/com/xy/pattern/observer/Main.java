package com.xy.pattern.observer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Observer;

/**
 * Created by XiuYang on 2016/9/26.
 */
public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Observer observer1 = new LiSi();
        HanFeiZi hanFeiZi = new HanFeiZi();
        hanFeiZi.addObserver(observer1);
        hanFeiZi.haveBrekfast();
        hanFeiZi.haveHan();
    }
}
