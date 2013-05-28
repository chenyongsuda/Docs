# Permutations II
# Given a collection of numbers that might contain duplicates, return all possible unique permutations.

# For example,
# [1,1,2] have the following unique permutations:
# [1,1,2], [1,2,1], and [2,1,1].


class Premu:
	dlist = [1,1,2]
	#dlist = [1,2,3]
	vlist = [0,0,0]
	rlist = []

	def __init__(self):
		pass
	def __del__(self):
		pass
	def per(self,level):
		size = len(self.dlist)
		if(level == size):
			print(self.rlist)
		pre = -1
		for index in range(0,size):
			if 0 == self.vlist[index] and pre != self.dlist[index]:
				self.vlist[index] = 1
				self.rlist.append(self.dlist[index])
				self.per(level + 1)
				self.rlist.pop(len(self.rlist) -1)
				self.vlist[index] = 0
				pre = self.dlist[index]


test = Premu()
test.per(0)
			