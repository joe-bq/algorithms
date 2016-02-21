#######################
# File:
#   buyLowSellHigh.py
# Author:
#   Administrator
# 
#######################

import sys

def maxProfit(a):
	length = len(a)
	profit = 0;
	maxProfit = -sys.maxsize
	low = a[0]
	high = a[0]
	for x in xrange(1,length):
		if low > a[x]:
			low = a[x]
		profit = a[x] - low
		if profit > maxProfit:
			maxProfit = profit
	return maxProfit


if __name__ == "__main__":
	stock_prices = [5, 10, 12, 3, 8, 18, 1, 14]
	maxProfit = maxProfit(stock_prices)
	print("%d" % maxProfit)
