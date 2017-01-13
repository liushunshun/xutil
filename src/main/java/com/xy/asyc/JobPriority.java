package com.xy.asyc;

public enum JobPriority {
	MIN(1),NORM(5),MAX(9);
	
	private int value;

	private JobPriority(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
