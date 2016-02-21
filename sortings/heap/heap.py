#####
# file: heap.py
# description: this file will show you operations, concept and implementation of the heap algorithms with heap
# author: Admnistrator
###

import sys

class Heap(object):
    def __initialize__(self, A):
    	if isinstance(A, int):
    		self.length = A
    	else if isinstance(A, list):
    		self.length = A.length
	def parent(self, i):
		return i // 2
	def left(self, i):
		return i * 2
	def right(self, i):
		return i * 2 + 1
	def max_heapify(self, A, i):
		if i < self.length:
			k = i
			x = A[i]
			if left(i) < self.length:
				l = A[left(i)]
			else:
				l = -sys.maxint
			if right(i) < self.length:
				r = A[right]
			else:
				r = -sys.maxint
			if x < l:
				k = left(i)
				x = l
			if x < r:
				k = right
			if k != i:
				temp = A[i]
				A[i] = A[k]
				A[k] = temp
				self.max_heapify(A, k)

	def build_heap(slef, A):
		for x in range(A.length // 2, 0, -1):
			self.max_heapify(A, x)
	def heap_sort(self, A):
		self.build_heap(A)
		while self.length > 0:
			temp = A[0]
			A[0] = A[self.length]
			A[self.length] = temp
			self.length -= 1
			self.max_heapify(A, 0)

	def shift_down(self, A, i):
		if i < self.length:
			k = i
			x = A[i]
			if left(i) < self.length:
				l = A[left(i)]
			else:
				l = -sys.maxint
			if right(i) < self.length:
				r = A[right]
			else:
				r = -sys.maxint
			if x < l:
				k = left(i)
				x = l
			if x < r:
				k = right
			if k != i:
				temp = A[i]
				A[i] = A[k]
				A[k] = temp
				self.shift_down(A, k)
	def shift_up(self, A, i): 
		while i < self.length and i > 0:
			k = parent(i)
			if A[i] < A[k]:
				temp = A[k]
				A[k] = A[i]
				A[i] = temp

	''''The following part deal with the priority queue'''
	def increase_key(self, A, i, key):
		if i < self.length and i > 0:
			A[i] = key
			self.shift_up(A, i)
	def extract_max(self, A):
		if self.length <= 0:
			raise "Underflow" 
		max = A[0]
		temp = A[0]
		A[0] = A[self.length]
		A[self.length] = temp
		self.max_heapify(A, 0)
		return max
	def insert(self, A, key):
		A[self.length] = -sys.maxint
		self.length += 1
		self.increase_key(A, self.length - 1, key)



if __name__ == "__main__":
	pass