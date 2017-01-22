package com.xy.refactoring.print.impl;

import org.apache.commons.collections.CollectionUtils;

import com.xy.refactoring.model.Customer;
import com.xy.refactoring.model.Rental;
import com.xy.refactoring.print.IPrinter;

public class TxtPrinter implements IPrinter {

	@Override
	public void printReport(Customer customer) {
		if(customer == null){
			return ;
		}
		if(CollectionUtils.isNotEmpty(customer.getRentalRecords())){
			System.out.println("顾客："+customer.getName());
			
			System.out.println("");
			System.out.println("影片名称	租赁天数	金额");
			
			for(Rental rental : customer.getRentalRecords()){
				System.out.println(rental.getMovie().getName()+"	"+rental.getDaysRented()+"	"+rental.getMovie().getCharge(rental.getDaysRented()));
			}
			System.out.println("");
			System.out.println("总金额："+customer.getTotalAmount()+"，总积分："+customer.getFrequentRenterPoints());
		}else{
			System.out.println("顾客："+customer.getName()+",没有租赁记录！");
		}
		
	}

}
