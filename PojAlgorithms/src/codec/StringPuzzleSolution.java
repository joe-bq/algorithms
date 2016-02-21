package codec;

import java.util.Scanner;

/* this is to encode the string into the corresponding numbers */
/* the problem is http://poj.org/problem?id=3226 
 * 
 * this problem has been resolved.
 * 
 * 
 * */

public class StringPuzzleSolution {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int length = 0;
		String code;
		int[] pow = new int[26];
		
		
		while ((length = scanner.nextInt()) != 0) {
			scanner.nextLine();
			code = scanner.nextLine();
			int number = 0;
			for (int i = 0; i < length; i++) { 
				number += (code.charAt(i) - 'A' - i) * pow(i, length); 
			}
			
			System.out.println(number);
		}
		
	}
	
	
	public static int pow(int i, int length) {
		int number = 1;
		int dis = 27 - length + i;
		for (;i < length - 1; i++) { 
			number *= dis--;
		}
		return number;
	}
}
