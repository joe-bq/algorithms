#################################################
# file
#    eightqueens_revisit.py
# description:
#    revisit the eightqueens revisit - with backtrace solution 
#
# references:
#
################################################

from __future__ import print_function
import os
import pdb

Max = 20
#b = [[]] * Max
#a = [] * Max
b = [[0 for i in range(Max)] for j in range(Max)]
a = [0] * Max

# def check(k):
# 	for i in range(1, k+1):
# 		for j in range(1, i):
# 			if a[i] == a[j] or a[i] - a[j] == i - j or a[i] - a[j] == j - i:
# 				return False
# 	return True

def check(k):
	for i in range(1, k):
		if a[k] == a[i] or a[i] - a[k] == i - k or a[i] - a[k] == k - i:
			return False
	return True

def show(s):
	for i in range(1, s+1):
		for j in range(1, s+1):
			if b[i][j] == 1:
				print('*', end="")
			else:
				print('-', end="")

def check_m(num):
	pdb.set_trace()
	i = 1;
	a[i] = 1
	b[i][a[i]] = 1
	count = 1
	while i <= num and i > 0:
		if i <= num and a[i] <= num:
			if check(i):
				i += 1
				a[i] = 1
				b[i][a[i]] = 1
			else:
				b[i][a[i]] = 0
				a[i] += 1 # back trace, attemp next position
				b[i][a[i]] = 1
		else:
			if i > num:
				print("found solution {0:d}".format(count))
				count += 1
				show()
			b[i][a[i]] = 0
			i -= 1
			a[i] += 1
			
if __name__ == "__main__":
	check_m(4)



# for j in range(1, num+1):
# 	i += 1
# 	a[i] = j
# 	b[i][j] = 1