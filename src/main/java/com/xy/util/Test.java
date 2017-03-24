package com.xy.util;

import javax.validation.constraints.NotNull;

public class Test {
	public static void main(String[] args) {
		new Test().log(null);
		
	}
	
	public void log(@NotNull String a){
		System.out.println(a);
	}
}
