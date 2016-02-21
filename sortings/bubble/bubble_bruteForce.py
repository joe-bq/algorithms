#######################
# File:
#   bubble_bruteForce.py
# Author:
#   Administrator
# Description:
#   well this is a program that will do a bubble sort with the bruteal force ...
#######################

import sys


def bubble_bruteForce(a):
	length = len(a)
	for i in range(length):
		for j in range(length):
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
	bubble_bruteForce(stock_prices)
	printArray(stock_prices)