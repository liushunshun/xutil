package com.xy.asyc;

public interface AsynService {
	/**
     * 添加异步工作
     * 
     * @param targetObject －－ 目标对象（可以是 Class,Object）
     * @param method －－ 目标方法
     * @param paramTypes －－ 目标方法参数类型
     * @param paramsValues －－ 目标方法参数值
     */
    public void addJob(Object targetObject, String method, Class<?>[] paramTypes, Object[] paramsValues,
    		AsynCallback asynCallback, JobPriority priority) throws Asyn4jException;
}
