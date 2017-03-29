package com.xy.pattern.factory.method;

public class BlueCatFactory extends CatFactory {

	@Override
	public Cat newCat() {
		return new Cat("blue");
	}

}
