package com.sk.fj;

import java.util.Date;

public class Test {
	public static void main(String[] args) {
		
		
		TFJGroupPool pool = new TFJGroupPool(4);
		Thread td = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
				pool.printInfos();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				}
			}
		});
//		td.start();
		System.out.println("---system start---"+new Date());
		int error_count = 0;
		for(int i = 0; i < 1000; i++){
			Fib fib = new Fib(1, 100);
			fib.setLogTag(i);
			pool.execute(fib);
			System.out.println(fib.getAnswer());
			if(fib.getAnswer() != 5050){
				error_count++;
			}
//			System.out.println("111system finish---"+"["+i+"]"+new Date()+"\n");
		}
		
		System.out.println("ERROR-COUNT:"+error_count);
//		Fib fib2 = new Fib(1, 8);
//		fib2.setLogTag(2);
//		pool.execute(fib2);
//		System.out.println(fib2.getAnswer());
//		System.out.println("222system finish02---"+new Date());
	}
}
