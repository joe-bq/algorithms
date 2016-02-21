#############################################
#
# longest_Palindrome_nspaces.py
#
#   this program will show you how to get the longest palindrome sequence from a string with only n extra spaces. 
#
#############################################

def initialize(s, n):
	m = [[1, 0] for i in range(n)]
	for i in range(n - 1):
		if (s[i] == s[i+1]):
			m[i][1] = 2
	return m

def longest_palindrome(s):
	length = len(s)
	start = end = -1
	longest = -1
	m = initialize(s, length)
	# 
	# two groups:
	# 0, 2, 4, ... 
	# 1, 3, 5,...
	# given an example, if the current difference is 2, then it value is calculated 
	# based on s[i] === s[i+2] and m[i+1][0]; and if the current difference is 3, then the value is 
	# calcaulted based on the s[i] == s[i+2] and m[i+1][1]
	for i in range(2, length):
		for j in range(0, length - i):
			if s[j] == s[i+j] and m[j+1][j%2] == i - 1:
				m[j][(j+1)%2] = i + 1
				if m[j][(j+1)%2] > longest:
					longest = m[j][(j+1)%2]
					start = j
					end = i + j
	print("The longest palindrome for string {0} is {1}, start = {2}, length = {3}".format(s, s[start:end], start, end - start))


longest_palindrome("adjabhhehchehhba")
