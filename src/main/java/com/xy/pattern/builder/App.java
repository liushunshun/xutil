package com.xy.pattern.builder;

public class App {
	
	public static void main(String[] args) {
		Computer computer = new ComputerDirector().constructComputor(new XyComputerBuilder());
		System.out.println(computer);
	}
}
