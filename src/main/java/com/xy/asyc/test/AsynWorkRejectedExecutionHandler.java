package com.xy.asyc.test;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class AsynWorkRejectedExecutionHandler implements RejectedExecutionHandler{

	private AsynContext asynContext;
	
	public AsynWorkRejectedExecutionHandler(AsynContext asynContext) {
		this.asynContext = asynContext;
	}
	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		//释放信号量
		asynContext.getSemaphore().release();
		
		//TODO 处理失败的任务
		
	}

}
