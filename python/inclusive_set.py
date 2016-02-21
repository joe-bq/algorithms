#############################################
#
# inclusive_set.py
#
#   Find all the inclusive set of numbers
#
# the original questions is as follow.
#    Return the set of all integers in the range [r1,r2] inclusive (r1,r2 > 0), which are multiples of m1,m2.
#    Example:   m1=4, m2=6 ; r1=3, r2=12 returns { 4, 6, 8, 12 }
#############################################

def return_inclusive_set(m1, m2, r1, r2):
	def iter_inclusive_set(m1, m2, r1, r2):
		i = j = 1
		while True:
			if i * m1 > r2 and j * m2 > r2: 
				break
			if i * m1 < j * m2:
				if i * m1 >= r1 and i * m1 <= r2:
					yield i * m1
				i += 1
			elif i * m1 == j * m2:
				if i * m1 >= r1 and i * m1 <= r2:
					yield i * m1
				i += 1
				j += 1
			else:
				if j * m1 >= r1 and j * m2 <= r2:
					yield j * m2
				j += 1
	return iter_inclusive_set(m1, m2, r1, r2)
if __name__ == "__main__":
	for x in return_inclusive_set(4, 6, 3, 12):
		print x