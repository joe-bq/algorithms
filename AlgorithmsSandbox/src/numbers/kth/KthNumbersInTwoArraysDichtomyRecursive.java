package numbers.kth;

/*
 * 
 * 
 * This is an excercise of finding the kth element from the union of two sorted arrays.
 * 
 *  the problem was originally posted here.
 *  
 *     http://leetcode.com/2011/01/find-k-th-smallest-element-in-union-of.html
 */

public class KthNumbersInTwoArraysDichtomyRecursive {
	public static void main(String[] args) {
		KthNumbersInTwoArraysDichtomyRecursive solution = new KthNumbersInTwoArraysDichtomyRecursive();
		if (args.length < 1) { 
			System.out.println("Input error, expect at least one argument");
			return;
		}
		
		int k = Integer.parseInt(args[0]);
		
		int[] a = new int[] { 2, 3, 5, 6, 10, 16, 18};
		int[] b = new int[] { 1, 2, 7, 12, 13};
		
		assert k < a.length + b.length : "Input k invalid!";
		
		int kvalue = solution.kthElement(a, b, a.length, b.length, 0, 0, k);
		System.out.println("kth " + k + " is " + kvalue);
	}
	
	
	private final int INT_MIN = Integer.MIN_VALUE;
	private final int INT_MAX = Integer.MAX_VALUE;
	
	/**
	 * find the kth elemnt from two sorted array, a and b.
	 * @param a the a array
	 * @param b the b array
	 * @param m the index to look at array a
	 * @param n the index to look at array b
	 * @param k the number k
	 * @return the number if found
	 * 
	 * for the following discussion, we are assuming the arrays are sorted in decreasing order.
	 * 
	 * the core of the algorithm is to keep the following invariance
	 * 
	 *  i + j = k - 1
	 * 
	 *  If Bj-1 < Ai < Bj, then Ai must be the k-th smallest,
	 *  or else if Ai-1 < Bj < Ai, then Bj must be the k-th smallest.
	 * 
	 * given i + j == k - 1, if we cannot find Ai or Bj that meet the condition, we will divide the arrays to seek when the conditions are meet.
	 * 
	 * The key problem is how to divide the arrays?
	 * 
	 *  when Ai < Bj-1, we can safely discard lower bound of a of size i, and the upper bound of array b (size n - j : well we don't really care about the upper bound).
	 *  when Bj < Ai-1, we can safely discard lower bound of b of size j, and the upper bound of array a (size m - i : well we don't really care about the upper bound).
	 *  
	 *  Given all this, we can come up with some code.
	 *  
	 *  For two sorted array in increasing order, we can apply similar solution.
	 */
	private int kthElement(int[] a, int[] b, int m, int n, int aoff, int boff, int k) {
		int i = (int)((double)m  * (k - 1) / (m + n));
		int j = k - 1 -i;
		
		assert(i >= 0);
		assert(j >= 0);
		assert(i <= m);
		assert(j <= n);
		
		int Ai_1 = ((i + aoff == 0) ? INT_MIN : a[i + aoff - 1]);
		int Bj_1 = ((j + boff == 0) ? INT_MIN : b[j + boff - 1]);
		int Ai =  ((i + aoff == m) ? INT_MAX : a[i + aoff]);
		int Bj = ((j + boff == n) ? INT_MAX : b[j + boff]);
		
		
		if (Bj_1 < Ai && Ai < Bj) {
			return Ai;
		} else if (Ai_1 < Bj && Bj < Ai) {
			return Bj;
		}
		
		assert((Ai > Bj && Ai_1 > Bj) || 
		         (Ai < Bj && Ai < Bj_1));
		
		// if none of hte cases above, then it is either 
		
		if (Ai < Bj) {
			// exclude Ai and below portion
		    // exclude Bj and above portion
			return kthElement(a, b, m - i - 1, j, aoff + i, boff,  k - i - 1);
		} else {
			// exclude Ai and above portion
		    // exclude Bj and below portion
			return kthElement(a, b, k - j - 1, n - j - 1, aoff, boff + j,  k - j - 1);
		}
	}
}
