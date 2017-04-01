package com.xy.pattern.builder;

public class ComputerDirector {
	public Computer constructComputor(ComputerBuilder builder) { 
		builder.builderCpu();
		builder.builderMembery();
		builder.builderDevice();
        return builder.build();  
   }  
}
