#############################################
#
# left_truncatable.py
#
#   left trancatable prime
#
# background:
#   left truncatable prim is the prime that if you take out each digit from left and the remaining number continue to be a prime number.
#############################################

prime_list = [2, ]
def get_prime_array(n):
	''' get the nth prime '''
	for x in range(n):
		if len(prime_list) > x:
			continue
		else:
			k = prime_list[-1]
			while True:
				k += 1
				isPrime = True
				for x in range(len(prime_list)):
					if k % prime_list[x] == 0:
						isPrime = False
						break

				if isPrime:
					prime_list.append(k)
					if len(prime_list) >= n:
						break;
	return prime_list

def is_left_truncatable_prime(n):
	''' n is a prime number, tell whether it is a left_truncatable prime number '''
	while prime_list[-1] < n:
		get_prim_array(len(prime_list))

	while str(n)[1:] != '' and n in prime_list:
		n = int(str(n)[1:])

	if n < 10 and n in prime_list:
		return True
	else:
		return False


def nth_truncatable_prim(n):
	i = j = 1

	truncatable_prime_list = []
	while True:
		get_prime_array(i)
		i += 1
		if is_left_truncatable_prime(prime_list[-1]):
			truncatable_prime_list.append(prime_list[-1])
			if j >= n:
				break
			j += 1
	return truncatable_prime_list[-1]


if __name__ == "__main__":
	print("100th of the left-truncatable prime is {0}".format(nth_truncatable_prim(100)))