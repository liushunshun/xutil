package com.xy.refactoring.print;

import com.xy.refactoring.model.Customer;

public interface IPrinter {
	/**
	 * 打印顾客报表
	 * @param customer
	 * @return void
	 * @exception
	 * @createTime：2017年1月22日
	 * @author: liuss
	 */
	public void printReport(Customer customer);
}
