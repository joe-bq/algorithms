#######################
# File:
#   bubble_bruteForce_cutHalf.py
# Author:
#   Administrator
# Description:
#   well this is a program that will do a bubble sort with the bruteal force ...
#######################

import sys

def bubble_bruteForce_cutHalf(a):
	length = len(a)
	for i in range(length):
		for j in range(i+1, length):
			if a[i] < a[j]:
				temp = a[i]
				a[i] = a[j]
				a[j] = temp

def printArray(a):
	for x in a:
		print x,
	print

if __name__ == "__main__":
	stock_prices = [5, 10, 12, 3, 8, 18, 1, 14]
	bubble_bruteForce_cutHalf(stock_prices)
	printArray(stock_prices)