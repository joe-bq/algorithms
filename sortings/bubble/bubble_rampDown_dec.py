#######################
# File:
#   bubbleSort_rampDown_dec.py
# Author:
#   Administrator
# Description:
#   this program will show you how to write the insertion sort with the Python code.
#######################

import sys

def bubble_rampDown_dec(a):
	length = len(a)
	for i in range(length):
		for j in range(length-1, i, -1):
			if a[j] > a[j-1]:
				temp = a[j]
				a[j] = a[j-1]
				a[j-1] = temp


def printArray(a):
	for x in range(len(a)):
		print a[x],
	print
if __name__ == "__main__":
	stock_prices = [5, 10, 12, 3, 8, 18, 1, 14]
	bubble_rampDown_dec(stock_prices)
	printArray(stock_prices)