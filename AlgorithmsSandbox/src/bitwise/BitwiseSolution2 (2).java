package bitwise;

import java.util.ArrayList;
import java.util.Arrays;

/* given that one number appears once, while other numbers in the array appears thrice , find a solution that is
 * efficient and effective to find the number
 *
 */
public class BitwiseSolution2 {

	public static void main(String[] args) {
		ArrayList<Integer> array = new ArrayList<Integer>();

		array.addAll(Arrays.asList(1,1,1,2,3,3,3,6,6,6,8,8,8));
		
		
		int[] bits = new int[32];
		
		for (int i: array) {
			int j = 0;
			while (i != 0) {
				if ((i & 1) != 0) {
					bits[j]++;	
				}
				
				j++;
				i >>= 1;
			}
		}
		
		int number = 0;
		for (int i = 0; i < 32; i++) { 
			if (bits[i] % 3 != 0) { 
				number |= 1 << i;
			}
		}
		
		System.out.println("the number that we found is " + number);
	}
}
