package com.xy.refactoring.model;

import java.util.Vector;

import com.xy.refactoring.print.IPrinter;

/**
 * 顾客
 * @author liuss
 *
 */
public class Customer {
	
	/**
	 * 姓名 
	 */
	private String name;
	
	/**
	 * 租赁记录
	 */
	private Vector<Rental> rentalRecords = new Vector<Rental>();
	
	public Customer(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public Vector<Rental> getRentalRecords(){
		return this.rentalRecords;
	}
	/**
	 * 添加租赁记录
	 * @param rental
	 * @return void
	 * @exception
	 * @createTime：2017年1月22日
	 * @author: liuss
	 */
	public void addRentalRecords(Rental rental){
		rentalRecords.add(rental);
	}
	
	/**
	 * 获取积分点
	 * @return
	 * @return int
	 * @exception
	 * @createTime：2017年1月22日
	 * @author: liuss
	 */
	public int getFrequentRenterPoints(){
		return rentalRecords.stream().mapToInt(rentalRecord -> rentalRecord.getMovie().getFrequentRenterPoints(rentalRecord.getDaysRented())).sum();
	}
	
	/**
	 * 获取总消费金额
	 * @return
	 * @return double
	 * @exception
	 * @createTime：2017年1月22日
	 * @author: liuss
	 */
	public double getTotalAmount(){
		return rentalRecords.stream().mapToDouble(rental -> rental.getMovie().getCharge(rental.getDaysRented())).sum();
	}
	
	/**
	 * 打印报表
	 * @param printer
	 * @return void
	 * @exception
	 * @createTime：2017年1月22日
	 * @author: liuss
	 */
	public void printReport(IPrinter printer){
		printer.printReport(this);
	}
}
