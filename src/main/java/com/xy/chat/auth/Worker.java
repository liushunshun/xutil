package com.xy.chat.auth;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

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

    @Override
    public void run() {
        while (!stop){
            IMHandler handler = null;
            try{
                handler = tasks.poll(600, TimeUnit.MILLISECONDS);
                if(handler==null){
                    continue;
                }
            }catch (InterruptedException e) {
                logger.error("Caught Exception");
            }
            try{
                assert handler != null;
                handler.jedis = AuthStarter.redisPoolManager.getJedis();
                handler.excute(this);
            } catch (Exception e) {
                logger.error("Caught Exception");
            } finally {
                AuthStarter.redisPoolManager.releaseJedis(handler.jedis);
                handler.jedis = null;
            }
        }
    }

    public static void  startWorker(int workNum) {
        workers = new Worker[workNum];
        for(int i = 0; i < workNum; i++) {
            workers[i] = new Worker();
            workers[i].start();
        }
    }

    public static void stopWorkers() {
        for(int i = 0; i < AuthStarter.workNum; i++) {
            workers[i].stop = true;
        }
    }
}
