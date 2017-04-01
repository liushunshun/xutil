package com.xy.pattern.proxy.staticproxy;

public class ServiceImpl implements IService{

	@Override
	public String sayHello(String name) {
		return "Hello," + name;
	}

}
