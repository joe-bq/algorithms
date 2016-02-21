package dichotomygreedy;

import java.util.Scanner;

/* in this problem, we will examine one problem where we 'd like to put m cows into n corral, while the requirement dictates
 * that each of cow should be separate at least that far.. (otherwise we can put all cows one by one next to each other .
 * 
 *  Find the least value of such distance and out put the numbers.
 *  
 *  References link:  [二分+贪心(总结) - no pains,no gains - 博客频道 - CSDN.NET](http://blog.csdn.net/jzmzy/article/details/22954505)
 */

public class BullCorralSolution {

	/**
	 * the main function of the BullCorralSolution 
	 * @param args
	 */
	public static void main(String[] args) { 
		int n, m;
		int[] a;
		
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		m = scanner.nextInt();
		a = new int[n];
		for (int i = 0; i < n; i++) { 
			a[i] = scanner.nextInt();
		}
		
		int smallest_dis = solve(a, n, m);
		
		System.out.println(String.format("the shortest dis between two corral is %d", smallest_dis));
	}
	
	/**
	 * judge if we can puts m cows into n corral where each of the cows should be separated by at least k. the distance of each corral is given by a
	 * @param a the distance of each corral
	 * @param n the number of corral
	 * @param m the number of cows
	 * @param k the least value of corral
	 * @return true if we put that cows otherwise false.
	 */
	public static boolean judge(int[] a, int n, int m, int k) { 
		/* this is the greedy algorithm to find if the given k satisfy the requirement */
		
		int cnt = 0, pre = a[0];
		for (int i = 1; i < n; i++) {
			if (a[i] - pre > k) { 
				cnt++;
				pre = a[i];
			}
		}
		
		cnt++;
		if (cnt >= m) return true;
		return false;
	}
	
	
	public static int solve(int[] a, int n, int m) { 
		
		int l = 0, r = 0;
		
		for (int i = 0; i < n; i++) {
			r += a[i];
		}
		
		while (l < r) { 
			int mid = (l + r) >> 2;
			if (judge(a, n, m, mid)) { 
				r = mid;
			} else {
				l += 1;
			}
		}
		
		return l;
	}
}
