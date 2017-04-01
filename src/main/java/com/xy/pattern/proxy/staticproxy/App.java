package com.xy.pattern.proxy.staticproxy;

public class App {

	public static void main(String[] args) {
		IService service = new XyProxy(new ServiceImpl());
		
		System.out.println(service.sayHello("alex"));
	}
}
