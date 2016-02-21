package states.compression;

import java.util.Scanner;

public class CowPlants_StatesCompression_Handwritting {

	static final int N = 13;
	static final int M = 1 << N;
	static final int[] st = new int[M];
	static final int[] map = new int[N];
	static final int[][] dp = new int[N][M];
	static final int mod = 100000000;

	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int n = in.nextInt();
			int m = in.nextInt();
			
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
			
			// build map
			for (int i = 1; i <= n; i++ ) {
				for (int j = 1; j <= m; j++) {
					int x = in.nextInt();
					if (x == 0) {
						map[i] += (1 << (j -1 ));
					}
				}
			}
			
			int k = 0;
			// build states
			for (int i = 0; i < (1<<m); i++) {
				if (!judge1(i)) {
					st[k++] = i;
				}
			}
			
			for (int i = 0; i < k; i++) {
				if (!judge2(1, i)) 
					dp[1][i] = 1;
			}
			
			// d-p solving the problems.
			for (int i = 2; i <= n; i++) {
				for (int j = 0; j < k; j++) {
					if (judge2(i, j)) {
						continue;
					}
					
					for (int f = 0; f < k; f++) {
						if (judge2(i-1, f)) {
							continue;
						}
						
						if ((st[f] & st[j]) == 0) {
							dp[i][j] += dp[i-1][f];
						}
					}
				}
			}
			
			int ans = 0;
			for (int i = 0; i < k; i++) {
				ans += dp[n][i];
				ans = ans % mod;
			}
			System.out.println("" + ans);
		}
	}
	
	public static boolean judge1(int x) { 
		return (x & (x << 1)) != 0;
	}
	
	public static boolean judge2(int i, int k) { 
		return (map[i] & st[k]) != 0;
	}
		
		
	
}
