#############################################
#
#  points_plane2.py.py
#
#   we will try to find the max points on a line with a little optimization to ignore finding tangent wich we have already considered.
#
# background:
#   Well, we will print out the max number of Points on a line
#############################################


class Point(object):
	def __init__(self, a = 0, b = 0):
		self.x = a
		self.y = b

# if p2.x == p.x and p2.y == p.y:
# 	for k in tangent.keys():
# 		tangent[k] += 1

class Solution(object):
	def maxPoints(self, points):
		"""
		:type points: List[Point]
		:rtype: int
		"""
		maxp = 0
		checked_tangent = {}
		for i in range(len(points)):
			p = points[i]
			tangent = {}
			vline = 1
			samepoint = 0
			for j in range(i+1, len(points)):
				p2 = points[j]
				if p2.x == p.x:
					vline += 1
					if p2.y == p.y:
						samepoint += 1
				else:
					tan = float(p2.y - p.y) / (p2.x - p.x) # float is built-in in python and double comes from numpy
					if not checked_tangent.has_key(tan):
						 tangent[tan] = tangent.get(tan, 1) + 1
			if samepoint > 0:
				for k in tangent.keys():
					tangent[k] += samepoint
			pmax = max(vline, samepoint + 1, max([0] + tangent.values()))
			if pmax > maxp:
				maxp = pmax
		return maxp

if __name__ == "__main__":
	solution = Solution();
	print solution.maxPoints(list([Point(0, 0), Point(1, 0)])) # 2
	
	print solution.maxPoints(list([Point(0, 0), Point(1, 1), Point(0, 0)])) # 3
	print solution.maxPoints(list([Point(0, 0), Point(1, 1), Point(0, -1)])) # 2
	print solution.maxPoints(list([Point(0, 0), Point(0, 0)])) # 2
	print solution.maxPoints(list([Point(1, 1), Point(1, 1), Point(2, 3)])) # 3
	print solution.maxPoints(list([Point(84, 250), Point(0, 0), Point(1, 0), Point(0, -70), Point(0, -70), Point(1, -1), Point(21, 10), Point(42, 90), Point(-42, -230)]))