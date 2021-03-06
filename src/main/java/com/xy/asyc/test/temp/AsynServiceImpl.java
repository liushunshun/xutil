package com.xy.asyc.test.temp;

import org.apache.commons.lang3.StringUtils;

public class AsynServiceImpl extends Asyn4jContext implements AsynService{

	@Override
	public void addJob(Object targetObject, String method, Class<?>[] paramTypes, Object[] paramsValues,
			AsynCallback asynCallback, JobPriority priority) throws Asyn4jException {
		
		if(targetObject == null || StringUtils.isBlank(method)){
			throw new Asyn4jException("target Object or method name is null");
		}
		if(targetObject instanceof Class){
			
		}
		AsynWork asynWork = null;
		
		//不用回调则创建一个默认的工作任务
		if(asynCallback == null){
			asynWork = new DefaultAsynWork(targetObject, method,paramTypes,paramsValues,priority);
		}
		
		addAsynWork(asynWork);
	}
	
	public static void main(String[] args) {
		Person person = new Person(1,"alex");
		
		try {
			new AsynServiceImpl().addJob(person, "getId", new Class<?>[]{int.class,}, new Object[]{2}, null, null);
		} catch (Asyn4jException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class Person{
	private int id;
	private String name;
	
	public Person(int id,String name){
		this.id = id;
		this.name = name;
	}
	public int getId(int id) {
		System.out.println("Persion.getId("+id+")");
		this.id=id+1;
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}