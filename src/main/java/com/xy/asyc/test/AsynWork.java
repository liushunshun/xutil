package com.xy.asyc.test;

import java.util.concurrent.Callable;

public class AsynWork implements Callable<Integer>{

	private int num;
	
	public AsynWork(int num) {
		this.num = num;
	}
	@Override
	public Integer call() throws Exception {
		
		System.out.println("异步线程执行了："+num);
		
		return num++;
	}

}
