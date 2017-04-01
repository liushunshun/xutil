package com.xy.pattern.proxy.staticproxy;

public class XyProxy implements IService{

	IService service;

	
	public XyProxy(IService service) {
		super();
		this.service = service;
	}

	@Override
	public String sayHello(String name) {
		return service.sayHello(name);
	}
}
