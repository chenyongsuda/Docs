#!usr/bin/python
# First Missing Positive
# Given an unsorted integer array, find the first missing positive integer.

# For example,
# Given [1,2,0] return 3,
# and [3,4,-1,1] return 2.

# Your algorithm should run in O(n) time and uses constant space.


#Soulution 01
# Sort then Liner Search
# nlogn + n
# capacity 1

#Soulution 02
# if <= 0 Drop
# if > len Drop
# linner set if (0,len] set if the BeSetted Coloum is  (0,len] also set to Property Column

#tmplist = [3,4,-1,1]
tmplist = [0,1,2,3,4,5]

def GetResult():
	res = -1;
	global tmptmplist
	for index in range(0,len(tmplist)):
		#print(index)
		#print("		Move Index %d" % (index))
		Move(tmplist,index)
		#print(tmplist)
	for index in range(0,len(tmplist)):
		if(index != tmplist[index] - 1):
			res = index + 1
	if(-1 == res):
		res = len(tmplist) + 1
	return res

	

def Move(tmplist,index):
	length = len(tmplist)
	if ((index + 1) == tmplist[index]):
		return
	if (tmplist[index] > length):
		return 
	if (tmplist[index] <= 0):
		return 
	#Get Value
	value = tmplist[index]
	#Set to Prop Column
	#Judge The Column Value 
	#print("		Move Index %d" % (value-1))
	Move(tmplist,value - 1)
	#Set Value
	tmplist[value - 1] = value

print(GetResult())
