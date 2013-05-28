# Permutations
# Given a collection of numbers, return all possible permutations.

# For example,
# [1,2,3] have the following permutations:
# [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].


class Premu:
	dlist = [1,2,3]
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
		for index in range(0,size):
			if 0 == self.vlist[index]:
				self.vlist[index] = 1
				self.rlist.append(self.dlist[index])
				self.per(level + 1)
				self.rlist.pop(len(self.rlist) -1)
				self.vlist[index] = 0


test = Premu()
test.per(0)
			