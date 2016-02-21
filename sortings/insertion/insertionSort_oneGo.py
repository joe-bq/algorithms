#######################
# File:
#   insertionSort.py
# Author:
#   Administrator
# Description:
#   this program will show you how to write the insertion sort with the Python code.
#######################

import sys

def insertionSort(a):
	for i in range(1, len(a)):
		value = a[i]
		for j in range(i):
			if value < a[j]:
				for k in range(i, j, -1):
					a[k] = a[k-1]
				a[j] = value
				break

def printArray(a):
	for x in range(len(a)):
		print a[x],
	print
if __name__ == "__main__":
	stock_prices = [5, 10, 12, 3, 8, 18, 1, 14]
	insertionSort(stock_prices)
	printArray(stock_prices)