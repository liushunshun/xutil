package com.xy.pattern.proxy.dynamic;

public class ServiceImpl implements IService{

	@Override
	public String sayHello(String name) {
		System.out.println("Hello," + name);
		return "Hello," + name;
	}

	@Override
	public String sayByBy(String name) {
		System.out.println("By by,"+name);
		return "By by,"+name;
	}

}
