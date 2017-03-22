package org.logicalcobwebs.concurrent;

public class Test {
	public static void main(String[] args) {
		try {
			int groupSize = 4; // for example
			FJTaskRunnerGroup group = new FJTaskRunnerGroup(groupSize);
			Fib f = new Fib(1,8); // for example
			group.invoke(f);
//			Fib f1 = new Fib(1,8); // for example
//			group.invoke(f1);
//			Fib f2 = new Fib(1,8); // for example
//			group.invoke(f2);
			int result = f.getAnswer();
			System.out.println("Answer: " + result);
			
			
//			int result1 = f1.getAnswer();
//			System.out.println("Answer: " + result1);
//			int result2 = f2.getAnswer();
//			System.out.println("Answer: " + result2);
		} catch (InterruptedException ex) {
		}
	}
}
