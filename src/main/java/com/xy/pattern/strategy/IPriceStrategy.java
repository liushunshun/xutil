package com.xy.pattern.strategy;

public interface IPriceStrategy {
	
	/**
	 * 计算折扣之后的价格
	 * @param price
	 * @return
	 * @return double
	 * @exception
	 * @createTime：2017年3月29日
	 * @author: liuss
	 */
	public double discount(double price);
}
