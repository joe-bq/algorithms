#############################################
#
# balanced_parentheses_solutionII.py
#
#   this is a program to show you how to list all the possible balanced parentheses
#
#############################################

def association(seq, **kw):
	grouper = kw.get("grouper", lambda x, y: (x, y))
	lifter = kw.get("lifter", lambda x: x)
	if len(seq) == 1:
		yield lifter(seq[0])
	else:
		for i in range(len(seq)):
			left = seq[:i]
			right = seq[i:]
			for x in association(left, **kw):
				for y in association(right, **kw):
					yield grouper(x, y)

for x in association(['a', 'b', 'c', 'd']):
	print x