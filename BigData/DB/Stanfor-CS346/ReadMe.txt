CS346如何实现一个数据库 参考实现下
CS346
https://web.stanford.edu/class/cs346/2015/#schedule
http://cs.stanford.edu/people/widom/cs346/

学习关系数据库实现的必读书之一：
stanford的课程主页
http://infolab.stanford.edu/~ullman/dbsi.html

《database system concept》，机械工业有中译，翻译水平一般
另外一本数据库实现方面写的不错书：
国内都出版过英文原版
Database Management Systems   By Raghu Ramakrishnan
Database System Implementation  By Hector Garcia-Molina, Jeff Ullman, and Jennifer Widom
后一本国内刚出了第二版

数据库有很多参考代码，APUE第21章有个700行的哈希数据库，还有Stanford的Redbase（CS346）。
有一定基础了可以去读谷歌的Leveldb，还有SQLite和redis（都看过但是并没有细看）
有一本书非常详细地介绍了数据库各个模块的具体实现细节，感觉非常适合用来作为实现数据库的参考书:
Transaction Processing: Concepts and Techniques》，作者是提出数据库锁粒度概念，图灵奖得主Jim Gray。


