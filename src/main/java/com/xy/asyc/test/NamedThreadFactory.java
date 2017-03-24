package com.xy.asyc.test;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamedThreadFactory implements ThreadFactory{

	final AtomicInteger threadNumber = new AtomicInteger(1);
    private String namePrefix;

    public NamedThreadFactory(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    @Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r, namePrefix+threadNumber.getAndIncrement());
		thread.setDaemon(true);//设置为守护线程
        if (thread.getPriority() != Thread.NORM_PRIORITY) {
        	thread.setPriority(Thread.NORM_PRIORITY);
        }
        return thread;
	}

}
