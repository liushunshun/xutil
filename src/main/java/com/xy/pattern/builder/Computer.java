package com.xy.pattern.builder;

public class Computer {

	private String cpu;
	
	private String memery;
	
	private String device;

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getMemery() {
		return memery;
	}

	public void setMemery(String memery) {
		this.memery = memery;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	@Override
	public String toString() {
		return "Computer [cpu=" + cpu + ", memery=" + memery + ", device=" + device + "]";
	}
	
}
