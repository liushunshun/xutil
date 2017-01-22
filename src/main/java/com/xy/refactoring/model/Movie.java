package com.xy.refactoring.model;

import com.xy.refactoring.price.Price;

/**
 * 影片
 * @author liuss
 *
 */
public class Movie {

	private String name;
	
	private Price price;
	
	public Movie(String name,Price price){
		this.name = name;
		this.price = price;
	}
	
	public String getName(){
		return this.name;
	}
	
	public double getCharge(int daysRented){
		return price.getCharge(daysRented);
	}
	
	public int getFrequentRenterPoints(int daysRented){
		return price.getFrequentRenterPoints(daysRented);
	}
}
