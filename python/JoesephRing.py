#############################################
#
# josephRing.py
#
#   This is a program that show how to soolve the joseph Ring problem with help from Math
#
# description:
#   Joseph's ring is like this: n people sit in a circile, and count 1..k, while the kth people exit the ring
#   until one pass, restart again the counting....
#
#############################################


def solution(n):
	m = 3
	x = 0
	for i in range(1, n+1):
		x = (x + m) % i # it is not 'x = (x + m) % n', because in each pass, the total number is 
	return x+1
if __name__ == "__main__":
	for i in range(1, 100):
		s = solution(i)
		print("number: {0:d}, winner: {1:d}".format(i, s))