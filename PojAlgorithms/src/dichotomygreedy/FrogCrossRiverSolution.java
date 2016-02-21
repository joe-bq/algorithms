package dichotomygreedy;

import java.util.Scanner;

public class FrogCrossRiverSolution {
/*
 * 
题意： 
    宽为L的河，有n块石头，青蛙可以通过石头跳到 
河对岸去，最多跳m次，问青蛙每次最少跳多远 
 
思路： 
    假设河的两岸都是石头，一共跳m次，一共有m+1块石头被用到 
那么我们就可以转化为在n个石头中挑出m+1个石头来解 
	
 */
	public static void main(String[] args) { 
		int l;
		int n;
		int m;
		int[] a;
		
		Scanner scanner = new Scanner(System.in);
		l = scanner.nextInt();
		n = scanner.nextInt();
		m = scanner.nextInt();
		a = new int[n];
		
		for (int i = 0; i < n; i++) { 
			a[i] = scanner.nextInt();
		}
		
		// in the code below, we approximate to the nearest length of minimus values.
		int left = 0; int right = l;
		while (left < right) {
			int mid = (left + right) >> 1; 
			if (judge(a, n, m, mid)) {
				right = mid; 
			} else {
				left += 1; 
			}
		}
	}
	
	public static boolean judge(int[] a, int n, int m, int k) {
		
		int cnt = 1; int pre = a[0];
		for (int i = 1; i < n; i++) { 
			if (a[i] - pre > k) { 
				pre = a[i-1];
				cnt++;
				if (a[i] - a[i-1] > k) { 
					return false;
				}
			}
		}
		
		cnt++;
		if (cnt > m) { 
			return false;
		}
		
		return true;
	}
	
}
