#############################################
#
# eight_queen_revisit.py
#
#   This algorithm tries to revisit the eight queen problems
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

def check_m(num):
	k = 1
	a[k] = 1
	count = 0
	print("The possible configuration of N Queens are:\n")
	while k > 0:
		if k <= num and a[k] <= num:
			if not check(k):
				a[k] += 1
			else:
				k += 1
				a[k] = 1
		else:
			if k > num: # if we have reached one solution, print it 
				count += 1
				print("[{0:d}]".format(count))
				show(num) 

			k -= 1 # back trace
			a[k] += 1; # next try on putting the key
	print("the count is : {0:d}".format(count))

if __name__ == "__main__"	:
	while True:
		print("*****************N QUeens Problem******************")
		print("                1. Four Queens                     ")
		print("                2. Eight Queens                     ")
		print("                1. N Queens                     ")
		print("                1. Exit                     ")

		inp = raw_input();
		if inp == '1':
			check_m(4)
		elif inp == '2':
			check_m(8)
		elif inp == '3':
			n = raw_input()
			int_n = int(n)
			if int_n > 0 and int_n < 20:
				check_m(int_n)
			else:
				print("Input error, please input again\n")
		elif inp == '4':
			exit(0)
		else:
			print("wrong input, please input again\n")

	os.system("pause");
	exit(0)
