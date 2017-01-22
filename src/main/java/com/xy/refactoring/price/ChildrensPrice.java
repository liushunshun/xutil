package com.xy.refactoring.price;

public class ChildrensPrice extends Price {
	@Override
	public double getCharge(int daysRented) {
		return daysRented > 3 ? 1.5 + (daysRented - 3) * 1.5 : 1.5;
	}

}
