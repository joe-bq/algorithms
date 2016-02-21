##############################################
# file:
#	BinaryTreePaths_main.py
# description:
#   the main function to test harness BinaryTrePaths
# ref:
#   https://leetcode.com/problems/binary-tree-paths/
# 
##############################################

from __future__ import print_function
from BinaryTreePaths import TreeNode, Solution
import pdb

if __name__ == "__main__":
	root = TreeNode(1)
	root.left = TreeNode(2)
	root.right = TreeNode(3)
	root.left.right = TreeNode(5)

	solution = Solution()
	# pdb.set_trace()
	res = solution.binaryTreePaths(root)
	print(res)
