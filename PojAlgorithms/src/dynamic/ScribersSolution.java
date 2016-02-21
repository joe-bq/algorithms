package dynamic;

import java.util.Scanner;

/* the link to the problem is :http://poj.org/problem?id=1505
 * 
problem 1505...

the problem is not yet done.
 */

public class ScribersSolution {

	
	public static void main(String[] args) { 
		Scanner scanner = new Scanner(System.in);
		
		int inputCount = 0;
		inputCount = scanner.nextInt();
		for (int i = 0; i < inputCount; i++) { 
			int m = 0, k = 0; // m denote the numbers of books, while the k means how many scribers
			m = scanner.nextInt();
			k = scanner.nextInt();
			
			int[] books = new int[m];
			int[] acc_pages = new int[m];
			
			for (int j = 0; j < m; j++) {
				books[j] = scanner.nextInt();
				acc_pages[j] = j > 0 ? acc_pages[j - 1] : 0 + books[j]; 
			}
			
			int[] scribers = new int[k];
			int[] scriber_books = new int[k];
			for (int j = 0; j < k; j++) { 
				scriber_books[j] = j+1; // that means scriber 0 scribes book [0..1), scriber 1 scribes books [1,2)....
				scribers[j] = books[i];
			}
			
			for (int j = 0; j < m; j++) {
				
			}
		}
	}
}
