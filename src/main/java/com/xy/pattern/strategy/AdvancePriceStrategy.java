package com.xy.pattern.strategy;

public class AdvancePriceStrategy implements IPriceStrategy{

	@Override
	public double discount(double price) {
		return price * 0.8;
	}

}
