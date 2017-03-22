浅谈ForkJoin如何试下
1.ForkJoinPool FJ线程池          -------------维护一个队列
  ForkJoinWorkThread FJ工作线程  -------------每个工作线程维护一个双向队列,首先专注执行自己队列里的任务,如果没有了,去池里拿,池里没有去其他线程盗取 
  ForkJoinTask FJ任务           --------------每个任务
  
  
