package com.xy.asyc.test.temp;

public class Job implements Runnable,Comparable<Job>{
	
	private AsynWork asynWork;
	
	@Override
	public void run() {
        if (asynWork.getThreadName() != null) {
        	Thread.currentThread().setName("Swift-Asyn4j-" + asynWork.getThreadName());
        }
	}
	
	
	@Override
	public int compareTo(Job o) {
		return o.asynWork.getPriority() - asynWork.getPriority();
	}
}
