package states.compression;

import java.util.Scanner;

/***
 * 
 * this is a solution to solve the issue of the Corn fields 
 * 
 * the original problem is listed here in pku online judge
 * 	http://poj.org/problem?id=3254
 * 
 * @author wangboqi
 * 
 * 
 * State compression the key is to use the digital 1 and 0 to represent states which may require a byte or more to display
 */

public class CowPlants_StatesCompressions {

	/* static fields to simplifies situation */
	static final int N = 13; // input only have 12 line of input
	static final int M = 1<<N;
	
	static int[][] dp = new int[N][M];
	static final int mod = 100000000;
	static int[] st = new int[M];
	static int[] map = new int[M];
	
	public static void main(String[] args)
	{

		int n, m;
		int x;
		// scanning 
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			// clear all 
			for (int i = 0; i < st.length; i++) { 
				st[i] = 0;
			}
			
			for (int i = 0; i < map.length; i++) {
				map[i] = 0;
			}
			
			for (int i = 0; i < dp.length; i++) { 
				for (int j = 0; j < dp[i].length; j++) {
					dp[i][j] = 0;
				}
			}
			
			n = in.nextInt();
			m = in.nextInt();
			
			// initialize map
			for (int i = 1; i <= n; i++) { 
				for (int j = 1; j <= m; j++) {
					x = in.nextInt();
					if (x == 0) { 
						map[i] += (1 << (j - 1));
					}
				}
			}
			
			int k = 0;
			
			for (int i = 0; i < (1 << m); i++) { // do a whole states enumeration
				if (!judge1(i)) {
					st[k++] = i;
				}
			}
			
			for (int i = 0; i < k; i++) { 
				if (!judge2(1, i)) {
					dp[1][i] = 1;
				}
			}
			
			for (int i = 2; i <= n; i++) { 
				for (int j = 0; j < k; j++) {
					if (judge2(i, j)) {
						continue;
					}
					
					for (int f = 0; f < k; f++) {
						if (judge2(i - 1, f)) {
							continue;
						}
						
						if ((st[j] & st[f]) == 0) // if current row are compatible with previous row, current row is denoted by (i) while previous row is denoted by (i - 1)
							dp[i][j] += dp[i-1][f]; 
					}
				}
			}
			
			int ans = 0;
			for (int i = 0; i < k; i++) { 
				ans += dp[n][i];
				ans %= mod;
			}
			
			System.out.println("" + ans);
		}
	}
	
	public static boolean judge1(int x) { 
		return (x & (x << 1)) != 0; // if a number has adjacent x, then the expression will return non-zero.
	}
	
	public static boolean judge2(int i, int x) {
		return (map[i] & st[x]) != 0; // compares to check if adjacent row return has same 1, the principle is the same as described above.
	}
	
	
	
}
