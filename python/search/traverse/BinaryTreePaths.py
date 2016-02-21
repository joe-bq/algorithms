##############################################
# file:
#   BinaryTreePaths.py
# description:
#   print all the paths from root to leaves
# ref:
#   https://leetcode.com/problems/binary-tree-paths/
# 
##############################################



# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class TreeNode:
	def __init__(self, x):
		self.val = x
		self.left = None
		self.right = None

class Solution:
	# @param {TreeNode} root
	# @return {string[]}
	def __init__(self):
		self.crumbs = []

	def binaryTreePaths(self, root):
		def crumbsToString(bread_crumbs):
			return "->".join(map(lambda x: str(x), self.crumbs))
		def binaryTreePathWalker(root):
			if root is not None:
				self.crumbs.append(root.val)
				if root.left is None and root.right is None:
					yield crumbsToString(self.crumbs)
				if root.left is not None:
					for x in  binaryTreePathWalker(root.left):
						yield x
				if root.right is not None:
					for x in binaryTreePathWalker(root.right):
						yield x
				self.crumbs.pop()
		return list(binaryTreePathWalker(root))

	# what is wrong?
	#  reason: the recur function may terminate and yield on a early-return func may terminate the generator early
	#          besides the types are not compatible.
	# def binaryTreePaths(self, root):
	# 	def crumbsToString(bread_crumbs):
	# 		return "->".join(map(lambda x: str(x), self.crumbs))
	# 	if root is not None:
	# 		self.crumbs.append(root.val)
	# 		if root.left is None and root.right is None:
	# 			yield crumbsToString(self.crumbs)
	# 		if root.left is not None:
	# 			yield self.binaryTreePaths(root.left):
	# 		if root.right is not None:
	# 			yield self.binaryTreePaths(root.right):
	# 		self.crumbs.pop()
