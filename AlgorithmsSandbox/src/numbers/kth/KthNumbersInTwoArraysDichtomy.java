package numbers.kth;

/*
 * 
 * This is a exercise to find the kth largest numbers in two sorted array.
 * 
 * in this exercise, we will begin with the most common solution - merge the two arrays first, then count til k...
 * 
 * http://leetcode.com/2011/01/find-k-th-smallest-element-in-union-of.html
 * 
 */

public class KthNumbersInTwoArraysDichtomy {
	public static void main(String[] args) {

		KthNumbersInTwoArraysDichtomy solution = new KthNumbersInTwoArraysDichtomy();
		if (args.length < 1) { 
			System.out.println("Input error, expect at least one argument");
			return;
		}
		
		int k = Integer.parseInt(args[0]);
		
		int[] a = new int[] { 2, 3, 5, 6, 10, 16, 18};
		int[] b = new int[] { 1, 2, 7, 12, 13};
		
		assert k < a.length + b.length : "Input k invalid!";
		
		int kvalue = solution.kthElement(a, b, k, a.length/2, b.length/2);
		System.out.println("kth " + k + " is " + kvalue);
	}
	
	/**
	 * find the kth element from two sorted array.
	 * @param a the input array a
	 * @param b the input array b
	 * @param k the k
	 * @param m index of a under check
	 * @param n index of b under check
	 * @return the kth value of union of a and b
	 */
	private int kthElement(int[] a, int[] b, int k, int m, int n) {
		int mlow = 0, nlow = 0, mhigh = a.length, nhigh = b.length;
		
		while (m + n != k - 1) {
			if (m + n > k - 1) { 
				if (a[m] > b[n]) {
					if (mlow != m) { // pre-check before infinite loop.
						int temp = m;
						m = (mlow + m) / 2;
						mhigh = temp;	
					} else {
						int temp = n;
						n = (nlow + m) / 2;
						nhigh = temp;
					}
					
				} else {
					if (nlow != n) {
						int temp = n; 
						n = (nlow + n) / 2;
						nhigh = temp;
					} else {
						int temp = m;
						m = (mlow + m) / 2;
						mhigh = temp;
					}
				}
			} else if (m + n < k - 1) {
				if (a[m] > b[n]) { 
					if (nhigh != n) { 
						int temp = n;
						n = (n + nhigh + 1) / 2;
						nlow = temp;
					} else {
						int temp = m;
						m = (m + mhigh + 1) / 2;
						mlow = temp;	
					}
					
				} else {
					if (mhigh != m) {
						int temp = m;
						m = (m + mhigh + 1) / 2; // the reason for '+1' is when moving upward, 
						mlow = temp;	
					} else 
					{
						int temp = n;
						n = (n + nhigh + 1) / 2;
						nlow = temp;
					}
				}
			}
			
		} 
		
		return a[m] < b[n] ? a[m] : b[n];
	}
	
}
