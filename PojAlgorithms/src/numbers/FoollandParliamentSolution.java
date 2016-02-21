package numbers;

import java.util.Scanner;


/* problem link is poj.org/problem?id=1032.
 * 
 *  solution: here we know that the number should be dispersed around the number of its median (which is number >> 1)
 *  
 * here is my solution. 
 * 
 * we first calculate all the way 2,3...,n where the remain m <= n
 * 
 * then we increment each of the 2,3,..,n that each has remain / count; (apparently , it shall be 0)..
 * 
 * then we work back from n, n-1, k, where count(k,n) == m;
 * 
 *  */

public class FoollandParliamentSolution {
	public static void main(String[] args) { 
		Scanner scanner = new Scanner(System.in);
		
		
		int number = scanner.nextInt();
		
		int[] fraction = new int[256];
		
		int sum = 0;
		int remain = number;
		
		int seqEnd = 0; 
		for (int i = 2; i < remain; i++) {
			fraction[seqEnd++] = i;
			sum += i;
			remain -= i;
		}
		
		int compl = (remain / (fraction[seqEnd - 1] - 1));
		
		if (compl > 0) {
			for (int i = 0; i < seqEnd; i++) {
				fraction[i] += compl;
			}
		}
		
		remain -= (compl * seqEnd);
		
		// fraction[seqEnd-1] += remain;
		
		for (int i = seqEnd - 1; i > seqEnd - 1 - remain; i--) { 
			fraction[i] += 1;
		}
		
		
		for (int i = 0; i < seqEnd; i++) { 
			System.out.print(fraction[i]);
			if (i != seqEnd - 1) {
				System.out.print(' ');
			}
			
		}
	}
	

}
