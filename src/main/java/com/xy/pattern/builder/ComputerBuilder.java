package com.xy.pattern.builder;

public abstract class ComputerBuilder {

	public abstract void builderCpu();
	
	public abstract void builderMembery();
	
	public abstract void builderDevice();
	
	public abstract Computer build();
}
