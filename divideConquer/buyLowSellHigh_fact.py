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
	wlow = a[0]

	for x in xrange(1,length):
		if low > a[x]:
			low = a[x]
		profit = a[x] - low
		if profit > maxProfit:
			high = a[x]
			wlow = low
			maxProfit = profit
	return (maxProfit, wlow, high)


if __name__ == "__main__":
	stock_prices = [5, 10, 12, 3, 8, 18, 1, 14]
	(maxProfit, low, high) = maxProfit(stock_prices)
	print("%d, low: %d, high: %d" % (maxProfit, low, high))
