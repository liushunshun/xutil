package com.xy.pattern.strategy;

public class Price {

	/**
	 * 价格策略
	 */
	private IPriceStrategy priceStrategy;

	public Price(IPriceStrategy priceStrategy) {
		super();
		this.priceStrategy = priceStrategy;
	}
	
	public double getPrice(double price){
		return priceStrategy.discount(price);
	}
}
