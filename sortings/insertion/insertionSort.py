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
	def move_next(a, length, pos):
		for i in range(length, pos, -1):
			a[i] = a[i - 1]

	def insert(a, length, value):
		for i in range(length):
			if value < a[i]:
				move_next(a, length, i)
				a[i] = value
				break

	for i in range(1, len(a)):
		insert(a, i, a[i])

def printArray(a):
	for x in range(len(a)):
		print a[x],
	print
if __name__ == "__main__":
	stock_prices = [5, 10, 12, 3, 8, 18, 1, 14]
	insertionSort(stock_prices)
	printArray(stock_prices)