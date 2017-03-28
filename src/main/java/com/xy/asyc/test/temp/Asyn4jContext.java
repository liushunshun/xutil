package com.xy.asyc.test.temp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;


public class Asyn4jContext {

	protected final AtomicBoolean run = new AtomicBoolean(false);
	
	protected Semaphore semaphore;
	
	public static final long ADD_JOB_WAITTIME = 5L;
	
	protected ExecutorService workExecutor = null;
	
	protected final AtomicLong TASK_TOTAL = new AtomicLong(0);
	
	public void addAsynWork(AsynWork asynWork){
		if (!run.get()) {
            throw new Asyn4jException("asyn service is stop or no start!");
        }
        if (asynWork == null) {
            throw new Asyn4jException("asynWork is null");
        }
        
        try {
			if(semaphore.tryAcquire(ADD_JOB_WAITTIME, TimeUnit.MILLISECONDS)){
				
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
