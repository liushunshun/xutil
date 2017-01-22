package com.xy.pattern.observer;

import java.util.Observable;
import java.util.Observer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by XiuYang on 2016/9/26.
 */
public class LiSi implements Observer {

    private static final Logger logger = LogManager.getLogger(LiSi.class);
    @Override
    public void update(Observable o, Object arg) {
        logger.debug(()->"李斯发现："+getString());
    }

    public String getString(){
        logger.info("lid asdkl;f");
        return "lisi";
    }
}
