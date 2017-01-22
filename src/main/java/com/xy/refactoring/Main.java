package com.xy.refactoring;

import com.xy.refactoring.model.Customer;
import com.xy.refactoring.model.Movie;
import com.xy.refactoring.model.Rental;
import com.xy.refactoring.price.impl.RegularPrice;
import com.xy.refactoring.print.IPrinter;
import com.xy.refactoring.print.impl.TxtPrinter;
/**
 * 面向对象设计Demo
 * 
 * 影片出租店管理系统
 * 
 * 出租影片（儿童类、普通类、新发行类）
 * 
 * 儿童类：三天及三天以内 1.5元，超出三天的 每天1.5元
 * 普通类：两天及两天以内2元，超出两天的每天1.5元
 * 新发行：每天3元
 * 
 * 用户积分
 * 		每次租赁增加1积分
 * 		租赁新片一天以上额外增加1积分
 * 
 * 打印报表
 * 		TXT
 * 		HTML
 */
public class Main {
	public static void main(String[] args) {
		//新到一个片
		Movie movie = new Movie("功夫熊猫2",new RegularPrice());
		
		//来了一个客人
		Customer customer = new Customer("休扬");
		
		//客人要租赁这个新片4天
		customer.addRentalRecords(new Rental(movie, 3));
		
		//初始化打印器
		IPrinter txtPrinter = new TxtPrinter();
		
		//打印报表 
		customer.printReport(txtPrinter);
		
	}
}
