package com.xy.pattern.proxy.dynamic;

public class App {
	public static void main(String[] args) {
        IService service = new ServiceImpl();
        MyInvocatioHandler handler = new MyInvocatioHandler(service);
        IService serviceProxy = (IService)handler.getProxy();
        serviceProxy.sayHello("alex");
        
        serviceProxy.sayByBy("huahua");
    }
}
