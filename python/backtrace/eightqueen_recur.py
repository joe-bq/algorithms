#############################################
#
# eightqueen_recur.py
#
#   This is a slight modified version to the eightqueen_revisit.py, with recursion 
# Ref
#   
#	http://blog.csdn.net/hackbuteer1/article/details/6657109
#
#############################################

# has to be the first line in the script
# check 
#   http://stackoverflow.com/questions/493386/how-to-print-in-python-without-newline-or-space
from __future__ import print_function # needs to be first statement in file

import os


Max = 20
a = [0] * Max # can do [0 for i in range(20)]

def show(s):
	b = [[0 for i in range(Max)] for j in range(Max)]

	for i in range(1, s+1):
		b[i][a[i]] = 1
		print("({0:d},{1:d})".format(i, a[i]))
	print("\n")
	for p in range(1, s+1):
		for q in range(1, s+1):
			if b[p][q] == 1:
				print('*', end='') # not recognized. solid dot
			else:
				print('-', end='') # not recognized. hallow dot
		print("\n")

def check(k):
	for i in range(1, k):
		if a[i] == a[k] or a[i] - a[k] == i - k or a[k] - a[i] == i - k:
			return False
	return True


## the following algorithm not necessary wrong, but the stack is not going to hold so much long recur...

# count = 1
# a[1] = 1
# def check_m(k, num):
# 	global count
# 	if k == 0: # terminate
# 		return
	
# 	if k <= num and a[k] <= num:
# 		if check(k):
# 			k += 1
# 			a[k] = 1
# 		else:
# 			a[k] += 1
# 		check_m(k, num)
# 	else:
# 		if k > num:
# 			print("found {0:d} solution\n".format(count))
# 			count += 1
# 			show(num)
# 		k -= 1
# 		a[k] += 1
# 		check_m(k, num)

count = 1
def check_m(k, num):
	global count
	if k > num:
		print("found {0:d} solution\n".format(count))
		count += 1
		show(num)
	else:
		for i in range(1, num+1):
			a[k] = i
			if check(k):
				check_m(k+1, num)


if __name__ == "__main__"	:
	while True:
		print("*****************N QUeens Problem******************")
		print("                1. Four Queens                     ")
		print("                2. Eight Queens                     ")
		print("                1. N Queens                     ")
		print("                1. Exit                     ")

		inp = raw_input();
		if inp == '1':
			# a[1] = 1
			count = 1
			check_m(1, 4)
		elif inp == '2':
			# a[1] = 1
			count = 1
			check_m(1, 8)
		elif inp == '3':
			n = raw_input()
			int_n = int(n)
			if int_n > 0 and int_n < 20:
				count = 1
				check_m(1, int_n)
			else:
				print("Input error, please input again\n")
		elif inp == '4':
			exit(0)
		else:
			print("wrong input, please input again\n")

	os.system("pause");
	exit(0)
