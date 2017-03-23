package com.sk.fj;

import java.util.concurrent.CountDownLatch;

public class TSyncFJTask extends TFJTask{
	private Runnable innerTask;
	private CountDownLatch lat = new CountDownLatch(1);
	public TSyncFJTask(Runnable outer){
		this.innerTask = outer;
	}
	
	@Override
	public void run() {
		try{
			innerTask.run();
		}finally{
//			notify();
			lat.countDown();
			System.out.println("!!!!!!!!!!!!!!Finish"+getLogTag()+"\n");
		}
	}
	
	public void WaitForEnd(){
		try {
			lat.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
