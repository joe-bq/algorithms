package dynamic;

/**
 * rod cutting is a typical dynamic programming problem. the dynamic algorithm depends on two factors. 
 * 1) Optimal substructure 
 * 2) overlapping subproblems
 * 3) Reconstructing an optimal solution
 * 4) Memoization
 * 
 * 
 * 1) defines a recursive structure where we can divide an optimal solution into sub-optimal solutions
 * 2) meaning that the sub-problem is exhaustive, if it is non-exhaustive, then there would not exist a predictable solution that yeild a asymptotic solution which has a bound
 * 3) meaning that we can construct the optimal solution from sub-optimal solutions.
 * 4) Memoization helps to bound the running time within an upper limit
 * 
 * @author joe
 *
 */
public class RodCuttingExample {
	public static void main(String[] args) {
		int[] p = new int[] {1, 5, 8, 9, 10, 17, 17, 20, 24, 30 };
		RodCuttingExample example = new RodCuttingExample();
		
		for (int i = 1; i < p.length + 1; i++) { 
			int result = example.cut_rod_recursive(p, i);
			System.out.println("number " + i + ": " + result);
		}
		
		for (int i = 1; i < p.length + 1; i++) { 
			int result = example.cut_rod_memoized(p, i);
			System.out.println("number " + i + ": " + result);
		}
	}
	
	
	private final int NEGATIVE_INFINITY = Integer.MIN_VALUE;
	private final int POSITIVE_INFINITY = Integer.MAX_VALUE;
	
	private int cut_rod(int[] p, int n) { 
		
		if (n == 0) {
			return 0;
		}
		
		int q = NEGATIVE_INFINITY;
		for (int i = 0; i < n; i++) {
			q = Integer.max(q, p[i] + cut_rod(p, n - i - 1));
		}
		return q;
	}
	
	
	
	/** this is the original version of the cut_rod_recursively call */
	private int cut_rod_recursive(int[] p, int n) {
		if (n == 0) return 0;
		
		int q = NEGATIVE_INFINITY;
		for (int i = 0; i< n; i++) {
			q = Math.max(q, p[i] + cut_rod_recursive(p, n-i-1));
		}
		
		return q;
	}
	
	
	private int cut_rod_memoized(int[] p, int n) { 
		int[] r = new int[p.length + 1];
		for (int i = 0; i < r.length; i++) { 
			r[i] = NEGATIVE_INFINITY;
		}
		
		int q;
		if (n == 0) { 
			q = 0;
			r[0] = 0;
		} else { 
			q = cut_rod_memoized_aux(p, n, r);
		}
		
		return q;
	}
	
	
	private int cut_rod_memoized_aux(int[] p, int n, int[] r) {
		if (r[n] >= 0) { 
			return r[n];
		}
		
		int q = NEGATIVE_INFINITY;
		if (n == 0) {
			q = 0;
		}
		else { 
			for (int i = 0; i < n; i++) { 
				q = Math.max(q, p[i] + cut_rod_memoized_aux(p, n - i - 1, r));
			}
		}
		
		r[n] = q;
		return q;
	}
	
	
}
