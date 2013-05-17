#!/usr/bin/python
#LeetCode Probelm Combination Sum
# Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

# The same repeated number may be chosen from C unlimited number of times.

# Note:

# All numbers (including target) will be positive integers.
# Elements in a combination (a1, a2, � , ak) must be in non-descending order. (ie, a1 ? a2 ? � ? ak).
# The solution set must not contain duplicate combinations.
# For example, given candidate set 2,3,6,7 and target 7, 
# A solution set is: 
# [7] 
# [2, 2, 3] 

# Solution 01
tmpList = []
resList = []
source = [2,2,3]
iSum = 0
iTarget = 7


def Solve(level,value):
	global iTarget
	global iSum
	global tmpList
	global source
	global resList

	if value > iTarget:
		return 

	if value == iTarget:
		print(tmpList)
		item = tmpList[:]
		resList.append(item)

	for x in range(0,len(source)):
		tmpList.append(source[x])
		iSum = iSum + source[x]
		Solve(level+1,iSum)
		iSum = iSum - source[x]
		tmpList.pop()


#Solve(0,0)

#print(resList[index])
# Result [[2, 2, 3], [2, 2, 3], [2, 3, 2], [2, 3, 2], [2, 2, 3], [2, 2, 3], [2, 3, 2], [2, 3, 2], [3, 2, 2], [3, 2, 2], [3, 2, 2], [3, 2, 2]]


#But The solution set must not contain duplicate combinations. Not Satisfied

# Solution 02
# May Be Map The Result BY # Solution 01


# Solution 03
# think of 0/1 package  but that is Solve the Most/least problem
#KEY Ascding, No Duplicate
def Solve1(level,index,value):
	global iTarget
	global iSum
	global tmpList
	global source
	global resList

	if value > iTarget:
		return 

	if value == iTarget:
		print(tmpList)
		item = tmpList[:]
		resList.append(item)

	for x in range(index,len(source)):
		tmpList.append(source[x])
		iSum = iSum + source[x]
		Solve1(level+1,x,iSum)
		iSum = iSum - source[x]
		tmpList.pop()


Solve1(0,0,0)
print(resList)

# Result [[2, 2, 3], [2, 2, 3], [2, 2, 3]]
# But Also Exist The Same Value MayBe We can Filter The source source = [2,2,3] -------> source = [2,3] that's Work

# Solution 04
# Thought :	题目的数据输入都没有重复 所以上面的所以方法都可以 其实题目意思是不重复元素多选子集



# 重复元素不多选子集
# Combination Sum II
# Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

# Each number in C may only be used once in the combination.

# Note:

# All numbers (including target) will be positive integers.
# Elements in a combination (a1, a2, � , ak) must be in non-descending order. (ie, a1 ? a2 ? � ? ak).
# The solution set must not contain duplicate combinations.
# For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
# A solution set is: 
# [1, 7] 
# [1, 2, 5] 
# [2, 6] 
# [1, 1, 6] 
# Solution 01
# 所有数字只能用一次
#Sort	Recurence condition change to Solve1(level+1,x+1,iSum) 难点不重复
#如何避免重复。如果两个数相同，我们先用前一个数，只有当前一个数用了，这个数才能使用。如 1 1 1 第一个1 用完了才能用第二个 第二个用了才能用第三个
#Reference http://www.xue5.com/Developer/Software/639875.html

def Solve2(level,index,value):
	global iTarget
	global iSum
	global tmpList
	global source
	global resList

	if level >= len(source):
		return 

	if value > iTarget:
		return 

	if value == iTarget:
		print(tmpList)
		item = tmpList[:]
		resList.append(item)
	pre = -1
	for x in range(index,len(source)):
		if pre != x:
			tmpList.append(source[x])
			iSum = iSum + source[x]
			Solve1(level+1,x + 1,iSum)
			iSum = iSum - source[x]
			tmpList.pop()
			pre = x
		


Solve2(0,0,0)
print(resList)



