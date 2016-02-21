#######################
# File:
#   bubbleSort_rampUp.py
# Author:
#   Administrator
# Description:
#   this program will show you how to write the insertion sort with the Python code.
#######################

import sys

def bubble_rampUp_dec(a):
	length = len(a)
	for i in range(length-1, -1,-1):
		for j in range(0, i):
			if a[j] < a[j+1]:
				temp = a[j]
				a[j] = a[j+1]
				a[j+1] = temp

def printArray(a):
	for x in range(len(a)):
		print a[x],
	print
if __name__ == "__main__":
	stock_prices = [5, 10, 12, 3, 8, 18, 1, 14]


	bubble_rampUp_dec(stock_prices)
	# a = stock_prices
	# temp = a[2]

	# a[2] = a[3]
	# a[3] = temp

	printArray(stock_prices)