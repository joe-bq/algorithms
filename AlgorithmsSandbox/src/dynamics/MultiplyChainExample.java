package dynamics;

public class MultiplyChainExample {
	public static void main(String[] args) {
		int[] p = new int[] {30, 35, 15, 5, 10, 20, 25};
		MultiplyChainExample example =new MultiplyChainExample();
		MultiplyChainSolution solution = example.matrix_chain_order(p);
		example.print_solution(solution.s, 1, p.length-1);
	}
	
	
	class MultiplyChainSolution
	{
		public int[][] m;
		public int[][] s;
	}
	
	public static final int INFINITY = Integer.MAX_VALUE;
	
	/**
	 * 
	 * the algorithm' is based on the basic principle of Dynamic programming
	 * 
	 * with the following essential element,
	 * 
	 * 1. boundary condition: when m[i][j] == 0 when i == j, 
	 * 2. given i and j, where j > i and k, where k > i and k < j, the value of m[i][j] = min(m[i,k] + pi-1 * pk * pj, + m[k+1, j])
	 * 3. when i = 1, and j = n, then we are done.
	 * @param p
	 * @return
	 */
	public MultiplyChainSolution matrix_chain_order(int[] p) {

		int n = p.length;
		
		// 
		int[][] m = new int[n][n]; // where m' range is m[1..n,1..n];
		int[][] s = new int[n][n]; // where s' range is s[1..n-1,2..n]
		
		// no calculation is required if i == j
		for (int i = 1; i < n; i++) {
			m[i][i] = 0;
			s[i][i] = i;
		}
		
		for (int l = 2; l < n; l++) {
			for (int i = 1; i <= n-l; i++) { // i: start index 
				int j = i + l - 1; // end idx
				m[i][j] = INFINITY;
				for (int k = i; k < j; k++) { 
					int q = m[i][k] + p[i-1]*p[k]*p[j] + m[k+1][j];
					if (q < m[i][j]) {
						m[i][j] = q;
						s[i][j] = k;
					}
				}
			}
		}
		
		MultiplyChainSolution solution = this.new MultiplyChainSolution();
		solution.m = m;
		solution.s = s;
		return solution;
	}
	
	/**
	 * the solution table
	 * 
	 * @param s solution table
	 * @param i the start index
	 * @param j the end index
	 */
	public void print_solution(int[][] s, int i, int j) {
		
		if (i == j) { 
			System.out.print("A" + i);
		}
		else
		{
			System.out.print("(");
			print_solution(s, i, s[i][j]);
			print_solution(s, s[i][j]+1, j);
			System.out.print(")");
		}
	}
}
