package com.xy.pattern.factory.method;

public class App {

	public static void main(String[] args) {
		CatFactory factory = new BlueCatFactory();
		
		System.out.println(factory.newCat().getName());
		
		factory = new RedCatFactory();
		
		System.out.println(factory.newCat().getName());
	}
}
