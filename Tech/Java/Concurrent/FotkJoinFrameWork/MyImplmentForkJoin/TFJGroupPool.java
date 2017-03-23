package com.sk.fj;

import java.util.concurrent.LinkedBlockingQueue;

public class TFJGroupPool {
	
	private LinkedBlockingQueue<TFJTask> pool_list = new LinkedBlockingQueue<TFJTask>();
	private TFJTaskWorker[] workerGroup;
	
	public TFJGroupPool(int size){
		this.workerGroup = new TFJTaskWorker[size];
		for(int i= 0; i < size;i++){
			this.workerGroup[i] = new TFJTaskWorker(this, i);
			this.workerGroup[i].start();
		}
	}
	
	public void execute(Runnable task){
		TSyncFJTask wapper = new TSyncFJTask(task);
		wapper.setLogTag(((TFJTask)task).getLogTag());
		//1. add task to pool queue 
		PutTask(wapper);
		//2. notify that we have Task in TODO
		//3. wait for the task finish
		wapper.WaitForEnd();
	}
	
	public TFJTask GetTask(){
		TFJTask task = pool_list.poll();
//		if(null != task){
//			System.out.println(Thread.currentThread().getName()+"-"+"GetFromPool"+"-"+"ID["+task.getLogTag()+"]");
//		}
		return task;
	}
	
	public void PutTask(TFJTask wapper){
		System.out.println("ID["+wapper.getLogTag()+"]"+"Current["+Thread.currentThread().getName()+"]"+"-"+"Action["+"PutIntoPool"+"]");
		pool_list.add(wapper);
	}
	
	public TFJTaskWorker[] getTaskGroup() {
		return workerGroup;
	}
	
	public void printInfos(){
		//PrintPool Size
		System.out.println("---pool size:"+pool_list.size());
		//innerSize
		for(int i = 0; i <workerGroup.length;i++){
			System.out.println("---size:"+workerGroup[i].innerDeq.size());
		}
	}
}
