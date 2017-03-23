package com.sk.fj;

class Fib extends TFJTask {
	static final int threshold = 2;
	volatile int number; // arg/result
	public int s;public int e;
	Fib(int s,int e) {
		this.s = s;
		this.e = e;
	}

	public int getAnswer() {
		return number;
	}

	public void run() {
	 int n = e-s;
	 if (n <= threshold) // granularity ctl
		 number = seqFib(s,e);
	 else {
		 int m = (s+e)/2;
		 Fib f1 = new Fib(s,m);
		 f1.setLogTag(getLogTag());
		 Fib f2 = new Fib(m+1,e);
		 f2.setLogTag(getLogTag());
		 coInvoke(f1, f2);
		 number = f1.number + f2.number;
	 }
 }

	int seqFib(int i,int j) {
		int sum = 0;
//		try {
//			Thread.sleep(1000);
			int xx =0;
			
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		for(int x=i;x<=j;x++){
			sum += x;
		}
		return sum;
	}
}