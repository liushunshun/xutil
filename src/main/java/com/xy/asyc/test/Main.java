package com.xy.asyc.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Main extends AsynContext{

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		AsynWork work = new AsynWork(1);
		
		AsynWork work2 = new AsynWork(2);
		
		List<Future<Integer>> list = new ArrayList<Future<Integer>>();
		
		Main main = new Main();
		list.add(main.submit(work));
		list.add(main.submit(work2));
		
		System.out.println("提交任务完成");
		
		for(Future<Integer> future : list){
			System.out.println(future.get());
		}
	}
	
	
}
