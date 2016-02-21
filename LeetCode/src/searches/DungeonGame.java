package searches;

/* leetcode: 
 * 	reorder list
 * 
 * 
 * problem link : https://leetcode.com/problems/dungeon-game/
 * 
 * solution:
 * 	dynamic programming
 * 
 *  given that m(0,0), we will do from i = 1 to n, while the n(0, i), n(1, i -1) to ... n(i-1, 1), n(i, 0) can be calculated by the maximum value of n(i-1, j) moves down one, or n(i, j-1) moves right
 *   
 *   then we will have the result 

 */

public class DungeonGame {

	public static void main(String[] args) {
		int[][] dungeon = new int[3][];
		dungeon[0] = new int[] { -2, -3, 3 };
		dungeon[1] = new int[] { -5, -10, 1 };
		dungeon[2] = new int[] { 10, 30, -5 };
		
		DungeonGame solution = new DungeonGame();
		int value = solution.calculateMinimumHP(dungeon);
		
		
		System.out.println(value);
	}
	
	
	public int calculateMinimumHP(int[][] dungeon) {
		
		int[][] m = new int[dungeon.length][];
		int[][] hp = new int[dungeon.length][];
		
		for (int i = 0; i < dungeon.length; i++) {
			m[i] = new int[dungeon[i].length];
			hp[i] = new int[dungeon[i].length];
		}
		
		if (dungeon.length > 0 && dungeon[0].length > 0) {
			m[0][0] = dungeon[0][0]; 
			hp[0][0] = dungeon[0][0] >= 0 ? 0 : dungeon[0][0];
		}
		
		int mlength = dungeon.length;
		int nlength = dungeon[0].length;
		
		for (int i = 1; i < mlength + nlength - 1; i++) { 
			for (int j = 0; j <= i; j++) {
				int k = i - j;
				/*
				int val = Integer.MAX_VALUE;
				if (k > 0) {
					val = m[k-1][j] + dungeon[k][j];	
				}
				
				if (j > 0) { 
					val = Math.min(val, m[k][j-1] + dungeon[k][j]);
				}
				m[k][j] = val;
				
				*/
				
				/*
				int val;
				if (k > 0 && j > 0) {
					val = Math.min(m[k][j-1] + dungeon[k][j], m[k-1][j] + dungeon[k][j]);
				} else if (k == 0) { 
					val = m[k][j-1] + dungeon[k][j];
				} else {
					val = m[k-1][j] + dungeon[k][j];
				}
				
				m[k][j] = val;
				*/
				
				// or we can do this, 
				// we can add this: if (i >= 0 && j >= 0) { ... }
				if (k >= 0 && k < mlength && j >= 0 && j < nlength) {
					if (k == 0) {
						m[k][j] = m[k][j-1] + dungeon[k][j];
						hp[k][j] = Math.min(hp[k][j-1], hp[k][j-1] + dungeon[k][j]);
						continue;
					}
					
					if (j == 0) {
						m[k][j] = m[k-1][j] + dungeon[k][j];
						hp[k][j] = Math.min(hp[k-1][j], hp[k-1][j] + dungeon[k][j]);
						continue;
					}
					
					if (k > 0 && j > 0) { 
						m[k][j] = Math.max(m[k][j-1] + dungeon[k][j], m[k-1][j] + dungeon[k][j]);
						hp[k][j] = Math.min(hp[k-1][j], hp[k][j-1]);
						hp[k][j] = Math.min(hp[k][j], hp[k][j] + dungeon[k][j]);
					}	
				}
				
				// or we can do this 
				/*
				if (k > 0) {
					m[k][j] = m[k-1][j] + dungeon[k][j];
					if (j > 0) { 
						m[k][j] = Math.min(m[k][j], m[k-1][j] + dungeon[k][j]);
					}
				} else {
					m[k][j] = m[k][j-1] + dungeon[k][j];
				}
				*/
			}
		}
		
		/* print the array */
		/*
		for (int i = 0 ; i < mlength; i++) {
			for (int j = 0; j < nlength; j++) { 
				System.out.print(m[i][j] + ",");
			}
			System.out.println();
		}
		
		return m[mlength-1][nlength-1] > 0 ? 1 : Math.abs(m[mlength-1][nlength-1] + 1);
		*/
		for (int i = 0 ; i < mlength; i++) {
			for (int j = 0; j < nlength; j++) { 
				System.out.print(hp[i][j] + ",");
			}
			System.out.println();
		}
		
		return hp[mlength - 1][nlength - 1] > 0 ? 1 : Math.abs(hp[mlength -1][nlength - 1]) + 1;
		
	}
}
