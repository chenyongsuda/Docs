package com.sk.fj;

import java.util.concurrent.CountDownLatch;

public abstract class TFJTask implements Runnable{
	private volatile boolean isDone;
	private volatile int logTag;
	
	public int getLogTag() {
		return logTag;
	}

	public void setLogTag(int logTag) {
		this.logTag = logTag;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone() {
		this.isDone = true;
	}
	
	public void coInvoke(TFJTask l,TFJTask r){
		//1. put l
		TFJTaskWorker worker = (TFJTaskWorker)Thread.currentThread();
		worker.putItem(l);
		//2.do right
		r.run();
		r.setDone();
		//3.get l do l if not l so wait l and do other
		TFJTask task = null;
		
		
		while(!l.isDone()){
			task = worker.getItem();
			if(null != task){
				if(task == l){
					if(task.isDone()){
						break;
					}
					else{
						task.run();
						task.setDone();
					}
				}
				else{
					//not the right so we do the other calc in order to no waste time
					task.run();
					task.setDone();
				}
			}
		}
		//The following is the error logic before!!!
//		while((task = worker.getItem())!= null){
//			//if equal right finish 
//			if(task == r){
//				if(task.isDone()){
//					
//				}
//				else{
//					task.run();
//					task.setDone();
//				}
//				break;
//			}
//			else{
//				//not the right so we do the other calc in order to no waste time
//				task.run();
//				task.setDone();
//			}
//		}
	}
//	public void fork(){
//		TFJTaskWorker worker = (TFJTaskWorker)Thread.currentThread();
//		worker.putItem(this);
//	}
//	
//	public void join() throws InterruptedException{
//		
//	}
}
