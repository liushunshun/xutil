package com.xy.asyc;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 异步方法执行反射类
 */
public class WorkContext {
	
	private static final ConcurrentHashMap<String, Method> METHOD_CACHE = new ConcurrentHashMap<String, Method>();
	
	/**
	 * 反射执行方法
	 */
	public Object invoke(Object targetObject,String method,Class<?>[] paramTypes,Object[] paramValues) throws Exception{
		
		String methodKey = getMethodKey(targetObject.getClass(), method, paramTypes);
		
		Method methodObject = METHOD_CACHE.get(methodKey);
		
		if(methodObject == null){
			
			methodObject = targetObject.getClass().getMethod(method, paramTypes);
			
			if(methodObject != null){
				METHOD_CACHE.put(methodKey, methodObject);
			}
		}
		return methodObject.invoke(targetObject, paramValues);
	}
	
	/**
	 * 生成反射方法缓存key
	 */
	private  static String getMethodKey(Class<?> clazz, String method, Class<?>[] paramTypes){
		StringBuilder sb = new StringBuilder(clazz.getName()).append(".").append(method);
        if (paramTypes != null && paramTypes.length > 0) {
            for (Class<?> c : paramTypes) {
                sb.append("-").append(c.getName());
            }
        }
        return sb.toString();
	}
}
