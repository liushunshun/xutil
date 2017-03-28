package com.xy.asyc.test.temp;

public class Asyn4jException extends RuntimeException{

	private static final long serialVersionUID = -6049741189890499900L;

	public Asyn4jException(String message){
		super(message);
	}
	public Asyn4jException(String message,Throwable e){
		super(message, e);
	}
}
