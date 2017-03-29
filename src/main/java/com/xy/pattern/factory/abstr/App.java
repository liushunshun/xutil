package com.xy.pattern.factory.abstr;

/**
 * 抽象工厂：工厂不仅生产猫还生产狗
 * 
 * @author liuss
 *
 */
public class App {

	public static void main(String[] args) {
		AnimalFactory factory = new BlueAnimalFactory();
		
		System.out.println(factory.newCat().getName());
		System.out.println(factory.newDog().getName());
		
		factory = new RedCatFactory();
		
		System.out.println(factory.newDog().getName());
		System.out.println(factory.newDog().getName());
	}
}
