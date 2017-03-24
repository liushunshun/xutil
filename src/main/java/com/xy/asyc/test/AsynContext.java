package com.xy.asyc.test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AsynContext {

	private ExecutorService executorService;
	
	private static final int CPU_NUM = Runtime.getRuntime().availableProcessors();
	
	private static int THREAD_NUM = CPU_NUM /2 +1;
	
	private static final int QUEUE_CAPACITY = 10000;
	
	private BlockingQueue<Runnable> workQueue = null;
	
	private static final String THREAD_FACTORY_NAME = "xiuiyang-asyn-work";
	
	private Semaphore semaphore;
	
	
	public AsynContext() {
		init();
	}

	public void init(){
		
		workQueue = new PriorityBlockingQueue<Runnable>(QUEUE_CAPACITY);
		
		semaphore = new Semaphore(QUEUE_CAPACITY);
		
		executorService = new ThreadPoolExecutor(THREAD_NUM, THREAD_NUM, 0L, TimeUnit.MILLISECONDS, workQueue,
				new NamedThreadFactory(THREAD_FACTORY_NAME), new AsynWorkRejectedExecutionHandler(this));
	}

	public Semaphore getSemaphore() {
		return semaphore;
	}
	
	protected Future<Integer> submit(AsynWork work){
		return executorService.submit(work);
	}
	
}
