package com.xy.pattern.factory.abstr;

public class BlueAnimalFactory extends AnimalFactory {

	@Override
	public Cat newCat() {
		return new Cat("blue cat");
	}

	@Override
	public Dog newDog() {
		return new Dog("blue dog");
	}

}
