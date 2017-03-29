package com.xy.pattern.factory.method;

public class RedCatFactory extends CatFactory {

	@Override
	public Cat newCat() {
		return new Cat("red");
	}

}
