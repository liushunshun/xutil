package com.xy.chat.auth;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by XiuYang on 2016/10/13.
 */
public class Worker extends Thread {

    private static final Logger logger = LogManager.getLogger(Worker.class);

    public static Worker[] workers;

    public volatile boolean stop = false;

    private final BlockingQueue<IMHandler> tasks = new LinkedBlockingDeque<>();

    public static void dispath(String userId,IMHandler handler){
        int workId = getWorkId(userId);
        if(handler==null){
            logger.error("handler is null");
            return;
        }
        workers[workId].tasks.offer(handler);
    }

    public static int getWorkId(String str){
        return str.hashCode() % AuthStarter.workNum;
    }

}
