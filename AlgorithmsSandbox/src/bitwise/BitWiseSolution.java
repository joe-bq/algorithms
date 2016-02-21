package bitwise;

import java.util.ArrayList;

/* suppose that there is array of numbers, one nubmer appears odd times, while all other numbers appears even times */

public class BitWiseSolution {
	
	public static void main(String[] args) 	{
		ArrayList<Integer> array = new ArrayList<Integer>();
		array.add(3);
		array.add(3);
		array.add(5);
		array.add(6);
		array.add(6);
		array.add(7);
		array.add(7);
		
		int number = 0;
		for (int i : array)	{
			number ^= i;
		}
		
		System.out.println("the number that we want to find is " + number);
	}
}
