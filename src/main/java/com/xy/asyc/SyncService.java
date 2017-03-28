package com.xy.asyc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.xy.util.ReflectUtil;

public class SyncService {
	
	private static final ExecutorService executorService = Executors.newFixedThreadPool(20);
	
	public static void submit(Object targetObject,String method, Class<?>[] paramTypes, Object[] paramsValues){
		executorService.submit(new SyncTask(new Work(targetObject, method, paramTypes, paramsValues)));
	}
}
class SyncTask implements Callable<String>{
	
	private static final Logger logger = LogManager.getLogger(SyncService.class);
	
	private Work work;
	
	public SyncTask(Work work){
		this.work = work;
	}
	@Override
	public String call() throws Exception {
		try{
			ReflectUtil.invoke(work.getTargetObject(), work.getMethod(), work.getParamTypes(), work.getParamsValues());
		}catch(Exception e){
			logger.error("SyncTask execute reflect invoke error:",e);
		}
		return "SUCCESS";
	}
	
}