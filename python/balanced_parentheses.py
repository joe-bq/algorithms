#############################################
#
# balanced_parentheses.py
#
#   this is a program to show you how to list all the possible balanced parentheses
#
#############################################

def parenthesized(exprs):
	if len(exprs) == 1:
		yield exprs[0]
	else:
		first_exprs = []
		last_exprs = list(exprs)
		while 1 < len(last_exprs):
			first_exprs.append(last_exprs.pop(0))
			for x in parenthesized(first_exprs):
				if 1 < len(first_exprs):
					x = '(%s)' % x
				for y in parenthesized(last_exprs):
					if 1 < len(last_exprs):
						y = '(%s)' % y
					yield '%s%s' % (x, y)

for x in parenthesized(list(['a', 'b', 'c', 'd'])):
	print x