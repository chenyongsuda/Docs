#Jump Game II
# Given an array of non-negative integers, you are initially positioned at the first index of the array.

# Each element in the array represents your maximum jump length at that position.

# Your goal is to reach the last index in the minimum number of jumps.

# For example:
# Given array A = [2,3,1,1,4]

# The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)...



class Jump2:
	jumpTable = [2,3,1,1,4];
	def __init__(self):
		pass
	def __del__(self):
		pass
	def Jump2Test(self,index):
		tSize = len(self.jumpTable)
		value = self.jumpTable[index]
		if(index + value >= tSize - 1):
			print("Result index: [%d] value [%d]" % (index,value))
			return True
		for i in range(1,value + 1):
			if(True == self.Jump2Test(index + i)):
				break
		return False

test = Jump2()
test.Jump2Test(0)