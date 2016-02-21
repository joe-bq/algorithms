package numbers.kth;

/*
 * 
 * This is a exercise to find the kth largest numbers in two sorted array.
 * 
 * in this exercise, we will begin with the most common solution - merge the two arrays first, then count til k...
 * 
 */
public class KthNumbersInTwoArraysMergeInPlace {
	public static void main(String[] args) {
		KthNumbersInTwoArraysMergeInPlace solution = new KthNumbersInTwoArraysMergeInPlace();
		if (args.length < 1) { 
			System.out.println("Input error, expect at least one argument");
			return;
		}
		
		int k = Integer.parseInt(args[0]);
		
		int[] a = new int[] { 2, 3, 5, 6, 10, 16, 18};
		int[] b = new int[] { 1, 7, 12, 13, 2};
		
		assert k < a.length + b.length : "Input k invalid!";
		
		int kvalue = solution.kthElement(a, b, k);
		System.out.println("kth " + k + " is " + kvalue);
		
	}
	
	private int kthElement(int[] a, int[] b, int k) { 
		for (int i = 0, j = 0, p = 0; i < a.length && j < b.length && p <= k;) {
			if (p == k) {
				if (a[i] < b[j]) {
					return a[i];
				} else {
					return b[j];
				}
			}
			
			if (a[i] < b[j]) {
				i++;
				p++;
			} else { 
				j++;
				p++;
			}
		}
		
		return 0; // beter return some pre-defiend values.
	}
}
