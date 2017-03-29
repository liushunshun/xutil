package com.xy.pattern.factory.simple;

/**
 * 简单工厂
 * @author liuss
 *
 */
public class SimpleFactory {
	
	public Product createProduct(int type){
		switch (type) {
		case 1:
			return new Product("alex");
		case 2:
			return new Product("justin");
		default:
			return null;
		}
	}

	public static void main(String[] args) {
		new SimpleFactory().createProduct(1);
	}	
}

class Product{
	
	private String name;

	public Product(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}