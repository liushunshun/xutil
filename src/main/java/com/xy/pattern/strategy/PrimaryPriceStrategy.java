package com.xy.pattern.strategy;

public class PrimaryPriceStrategy implements IPriceStrategy{

	@Override
	public double discount(double price) {
		return price * 0.9;
	}

}
