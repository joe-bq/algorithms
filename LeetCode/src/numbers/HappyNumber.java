package numbers;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {
	public static void main(String[] args) {
		int n = 19;
		HappyNumber solution = new HappyNumber();
		
		
		for (int i = 1; i < 20; i++) {
			if (solution.isHappy(i)) {
				System.out.println("" + i + " is happy number");
			} else { 
				System.out.println("" + i + " is NOT a happy number");
			}
		}
		
		

		
	}
	
	public boolean isHappy(int n) { 
		// return isHappyInternal(n, new HashSet());
		return isHappyInternalNonRecur(n);
	}
	
	public boolean isHappyInternal(int n, Set<Integer> numbers) {
		if (numbers.contains(n)) { 
			return false;
		}
		
		int sum = 0;
		int v = n;
		while (v != 0) { 
			int d = v % 10;
			sum += d * d;
			v /= 10;
		}
		
		if (v == 1) {
			return true;
		}
		
		numbers.add(n);
		return isHappyInternal(sum, numbers);
		
	}
	
	
	public boolean isHappyInternalNonRecur(int n) {
		Set<Integer> numbers = new HashSet();
		
		while (n != 1 && !numbers.contains(n)) {
			int sum = 0;
			int v = n;
			while (v != 0) { 
				int d = v % 10;
				sum += d * d;
				v /= 10;
			}
			
			numbers.add(n);
			n = sum;
		}
		
		return n == 1;
		
	}
	
}
