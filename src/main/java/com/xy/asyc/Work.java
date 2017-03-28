package com.xy.asyc;

public class Work {
	private Object targetObject;
	
	private String method;
	
	private Class<?>[] paramTypes;

    private Object[] paramsValues;

	public Work(Object targetObject,String method, Class<?>[] paramTypes, Object[] paramsValues) {
		super();
		this.targetObject = targetObject;
		this.method = method;
		this.paramTypes = paramTypes;
		this.paramsValues = paramsValues;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Class<?>[] getParamTypes() {
		return paramTypes;
	}

	public void setParamTypes(Class<?>[] paramTypes) {
		this.paramTypes = paramTypes;
	}

	public Object[] getParamsValues() {
		return paramsValues;
	}

	public void setParamsValues(Object[] paramsValues) {
		this.paramsValues = paramsValues;
	}

	public Object getTargetObject() {
		return targetObject;
	}

	public void setTargetObject(Object targetObject) {
		this.targetObject = targetObject;
	}
    
}
