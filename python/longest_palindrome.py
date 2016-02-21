#############################################
#
# longest_palindrome.py
#
#   this is the naive implementation of palindrome, palindrome is a a list/string which read the same forwards or backwards.
#
#############################################

def initialize(s):
	table = [[1 if x == y else 0 for x in range(len(s))] for y in range(len(s))]
	for x in range(len(s) - 1):
		if s[x] == s[x+1]:
			table[x][x+1] = 2
	return table

def printTable(table):
	for i in range(len(table)):
		for j in range(len(table[i])):
			print("{0},{1}: {2}".format(i, j, table[i][j]))

def palindrome(s):
	table = initialize(s)
	start = end = -1
	longest = 0
	for i in range(2, len(s)+1):
		for j in range(len(s) - i):
			k = i + j
			if s[j] == s[k] and table[j+1][k-1] != 0:
				print("Here")
				table[j][k] = i
				if longest < i:
					longest = i
					start = j
					end = k
	printTable(table)
	print("Longest palindrome in {0} is {1}, length = {2}, start = {3}".format(s, s[start: end], longest, start))

if __name__ == "__main__":
	palindrome("abffcycffca")


