package com.xy.pattern.strategy;

public class App {

	/**
	 * 策略默认中客户端必须知道所有的策略
	 */
	public static void main(String[] args) {
		IPriceStrategy priceStrategy = new AdvancePriceStrategy();
		Price price = new Price(priceStrategy);
		System.out.println(price.getPrice(300));
	}
}
