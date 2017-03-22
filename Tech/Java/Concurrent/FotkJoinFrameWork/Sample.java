package org.logicalcobwebs.concurrent;

class Fib extends FJTask {
	static final int threshold = 2;
	volatile int number; // arg/result
	public int s;public int e;
	Fib(int s,int e) {
		this.s = s;
		this.e = e;
	}

	int getAnswer() {
		if (!isDone())
			throw new IllegalStateException();
		return number;
	}

	public void run() {
	 int n = e-s;
	 if (n <= threshold) // granularity ctl
		 number = seqFib(s,e);
	 else {
		 int m = (s+e)/2;
		 Fib f1 = new Fib(s,m);
		 Fib f2 = new Fib(m+1,e);
		 coInvoke(f1, f2); 
		 number = f1.number + f2.number;
	 }
 }

	int seqFib(int i,int j) {
		int sum = 0;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for(int x=i;x<=j;x++){
			sum += x;
		}
		return sum;
	}
}