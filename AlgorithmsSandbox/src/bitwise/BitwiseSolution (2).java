package bitwise;

import java.util.ArrayList;
import java.util.Arrays;

/* given that an array of numbers, we know that if only one number appear only once, and the rest appears twice, please find a solution that to find the number with computation and memory */
public class BitwiseSolution {
	public static void main(String[] args) {
		ArrayList<Integer> array = new ArrayList<Integer>();
		array.addAll(Arrays.asList(1,2,2,3,3,4,4,5,5));
		
		int number = 0;
		
		for (int i : array) { 
			number ^= i;
		}
		
		System.out.println(number);
	}
}
