package com.xy.asyc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.xy.util.ReflectUtil;

public class SyncService {
	
	private static final ExecutorService executorService = Executors.newFixedThreadPool(20);
	
	public static void submit(Object targetObject,String method, Class<?>[] paramTypes, Object[] paramsValues){
		executorService.submit(new SyncTask(new Work(targetObject, method, paramTypes, paramsValues)));
	}
}
class SyncTask implements Callable<String>{
	
	private Work work;
	
	public SyncTask(Work work){
		this.work = work;
	}
	@Override
	public String call() throws Exception {
		ReflectUtil.invoke(work.getTargetObject(), work.getMethod(), work.getParamTypes(), work.getParamsValues());
		return "SUCCESS";
	}
	
}