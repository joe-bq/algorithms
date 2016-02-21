package numbers.kth;

/*
 * 
 * This is a exercise to find the kth largest numbers in two sorted array.
 * 
 * in this exercise, we will begin with the most common solution - merge the two arrays first, then count til k...
 * 
 */
public class KthNumbersInTwoArraysMerge {
	public static void main(String[] args)	{
		// TODO auto-generated method stub
		
		KthNumbersInTwoArraysMerge solution = new KthNumbersInTwoArraysMerge();
		
		if (args.length < 1) {
			System.out.println("Input error, expect at least one argument");
			return;
		}
		
		int k = Integer.parseInt(args[0]);
		
		int[] a = new int[] { 2, 3, 5, 6, 10, 16, 18};
		int[] b = new int[] { 1, 7, 12, 13, 2};
		
		int[] c = solution.merge(a, b);

		assert k < c.length : "input k is false";
		
		
		System.out.println("kth " + k + " is " + c[k-1]);
		
	}
	
	
	private int[] merge(int[] a, int[] b) {
		int[] c = new int[a.length + b.length];	
		for (int i = 0, j = 0, k = 0; i < a.length && j < b.length;) {
			if (a[i] < b[j]) {
				c[k++] = a[i++];
			} else {
				c[k++] = b[j++];
			}
			
			if (i == a.length) {
				for (;j < b.length; j++) {
					c[k++] = b[j++];
				}
			}
			
			if (j == b.length) { 
				for (;i < a.length; i++) { 
					c[k++] = a[i++];
				}
			}
		}
		
		return c;
	}

	 
}