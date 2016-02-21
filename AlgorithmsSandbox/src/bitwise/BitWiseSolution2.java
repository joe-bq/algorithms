package bitwise;

import java.util.ArrayList;
import java.util.Arrays;


/* Suppose that for an array of numbers, one appears once, while the others appears three times, find it */
public class BitWiseSolution2 {
	
	public static void main(String[] args) 	{
		ArrayList<Integer> array = new ArrayList<Integer>();
		array.addAll(Arrays.asList(2, 2, 2, 3, 3, 3, 1, 5, 5, 5, 6,6,6));
		
		int[] bits = new int[32]; /* suppose that we are working with a 32-bit numbers */
		
		/*
		for (int i : array) {
			for (int j = 0; j < 32; j++) { 
				bits[j] += (((1 << j) & array.get(i)) == 0 ? 0 : 1);
			}
		}
		*/
		for (int i : array) { 
			int j = 0;
		
		/*
			do {
				if (1 == (i & 1)) {
					bits[j]++;
				}
				j++;
				i <<= 1;
			} while (i != 0);
		*/
			while (i != 0) {
				if (1 == (i & 1)) {
					bits[j]++;
					
				}
				j++;
				i <<=1 ;
			}
		}
		
//		int number = -1; // whose binary representation should be 11111111
		int number = 0;
		for (int i = 0; i < 32; i++) { 
			 if (bits[i] % 3 == 1) { 
				 number |= (1 << i);
			 }
		}
		
		System.out.println("the number which appears only once is " + number);
		
	}
}
