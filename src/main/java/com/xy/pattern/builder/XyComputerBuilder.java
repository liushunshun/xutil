package com.xy.pattern.builder;

public class XyComputerBuilder extends ComputerBuilder {

	private Computer computer;
	
	public XyComputerBuilder() {
		computer = new Computer();
	}

	@Override
	public void builderCpu() {
		computer.setCpu("xy cpu");
	}

	@Override
	public void builderMembery() {
		computer.setMemery("xy memery");
	}

	@Override
	public void builderDevice() {
		computer.setDevice("xy device");
	}

	@Override
	public Computer build() {
		return computer;
	}
}
