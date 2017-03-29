package com.xy.pattern.factory.abstr;

public class RedCatFactory extends AnimalFactory {

	@Override
	public Cat newCat() {
		return new Cat("red cat");
	}

	@Override
	public Dog newDog() {
		return new Dog("red dog");
	}

}
