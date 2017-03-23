package com.sk.fj;

import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;

public class TFJTaskWorker extends Thread{
	
	public LinkedBlockingDeque<TFJTask> innerDeq =  new LinkedBlockingDeque<TFJTask>();
	private TFJGroupPool pool;
	private int index;
	
	public TFJTaskWorker(TFJGroupPool pool, int index){
		this.pool = pool;
		this.index = index;
		setName("WorkingThread-"+index);
	}
	
//	public boolean isInterrupted(){
////		 boolean flag = currentThread().isInterrupted();
////		 return flag;
////		return false;
//	}
	
	public TFJTask stealling(){
		TFJTask task = null;
		if(innerDeq.size() > 0){
			try{
				task = innerDeq.poll();
			}catch(Exception ex){
				
			}
		}
		return task;
	}
	public void putItem(TFJTask task){
		Log(null,task,"PutToInnerLoop");
		innerDeq.push(task);
	}
	
	public TFJTask getItem(){
		TFJTask task = null;
		try{
			task = innerDeq.pop();
			Log(null,task,"GetFromInnerLoop");
		} catch(Exception ex){
			return null;
		}
		return task;
	}
	
	public boolean doStealling(){
		TFJTaskWorker[] arr = pool.getTaskGroup();
		int stIndex = new Random().nextInt(100)%arr.length;
		TFJTaskWorker work = null;
		TFJTask task =null;
		for(int i =0;i < arr.length;i++){
			if(stIndex >= arr.length){
				stIndex = 0;
			}
			work = arr[stIndex++];
			if(null != work && this != work){
				task = work.stealling();
				if(task != null){
					break;
				}
			}
		}
		
		if(null != task){
			Log(work,task,"Stealling");
			//Got Stealling Task
			task.run();
			task.setDone();
			return true;
		}
		else{
			return false;
		}
		
		
	}
	
	public void GotFromPool(){
		TFJTask task = pool.GetTask();
		if(null != task){
			System.out.println("ID["+task.getLogTag()+"]"+"Current["+Thread.currentThread().getName()+"]"+"-"+"Action["+"GetFromPool"+"]");
			task.run();
			task.setDone();
		}
		else{
			//no Task yield some time
			this.yield();
		}
	}
	
	@Override
	public void run() {
		TFJTask task = null;
		while(!isInterrupted()){
			//1. Got inner Tasks
			if((innerDeq.size()>0)&&(task = innerDeq.pop())!= null){
				if(null != task&&!task.isDone()){
					Log(null,task,"InnerGet");
					task.run();
					task.setDone();
				}
			}
			//2. steal works in other thread
			boolean stealFlag = doStealling();
			//3. Go task from public pool
			if(!stealFlag){
				GotFromPool();	
			}
		}
	}
	
	public void Log(TFJTaskWorker from,TFJTask task,String action){
		Fib fib = (Fib)task; 
		if(null == from){
			System.out.println("ID["+fib.getLogTag()+"]"+"Current["+Thread.currentThread().getName()+"]"+"-"+"Action["+action+"]"+"-"+"Range["+fib.s+","+fib.e+"]");
		}
		else{
			System.out.println("ID["+fib.getLogTag()+"]"+"From["+from.getName()+"]"+"To["+Thread.currentThread().getName()+"]"+"-"+"Action["+action+"]"+"-"+"Range["+fib.s+","+fib.e+"]");
		}
		
	}
	
}
