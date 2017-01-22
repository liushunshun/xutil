package com.xy.refactoring.price.impl;

import com.xy.refactoring.price.Price;

public class RegularPrice extends Price {

	@Override
	public double getCharge(int daysRented) {
		return daysRented > 2 ? 2 + (daysRented - 2) * 1.5 : 2;
	}

}
