package numbers;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.time.StopWatch;

/*
 * 
 * the problem id is http://poj.org/problem?id=1142
 * 
 * 
 * 			1142
 * 
 */

public class SmithNumberSolution {

	private int lastIndex;
	private ArrayList<Integer> primeNumbers;
	
	public SmithNumberSolution() { 
		primeNumbers = new ArrayList<Integer>();
		primeNumbers.add(2);
		primeNumbers.add(3);
		primeNumbers.add(5);
		primeNumbers.add(7);
		primeNumbers.add(11);
		lastIndex = 4;
	}
	
	public int nextPrime() { 
		int number = primeNumbers.get(lastIndex) + 1;
		
		while (!isPrime(number)) {
			number++;
		}
		
		primeNumbers.add(number);
		lastIndex++;
		return number;
	}
	
	public void build(int number) {
		if (needsBuild(number)) {
			int i = 0;
			while (i * primeNumbers.get(i) < number) { 
				nextPrime();
				i++;
			}

			int startIndex = 0;
			do {
				int factorIndex = firstFactorIndex(number, startIndex);
				if (factorIndex == -1) {
					startIndex = lastIndex;
					nextPrime();
				} else { 
					number /= primeNumbers.get(factorIndex);
					startIndex = factorIndex;
				}
			} while (primeNumbers.get(startIndex) != number);
			
		}
	}
	
	public boolean isPrime(int number) {
		int i = 0;

		while (i * primeNumbers.get(i) < number) {
			
			if (number % primeNumbers.get(i) == 0) {
				return false;
			}
			
			i++;
		}
		
		return true;
	}
	
	public int firstFactorIndex(int number, int startIndex) { 
		for (int i = startIndex; i < lastIndex; i++) { 
			if (number % primeNumbers.get(i) == 0) {
				return i;
			}
		}
		
		return -1;
	}
	
	public List<Integer> defactor(int number) { 
		ArrayList<Integer> factors = new ArrayList<Integer>();
		
		build(number);
		
		int i = 0;
		while (i < lastIndex && primeNumbers.get(i) <= number) {
			if (number % primeNumbers.get(i) == 0) {
				factors.add(primeNumbers.get(i));
				number /= primeNumbers.get(i);
			} else {
				i++;
			}
		}
		
		return factors;
	}
	
	
	public List<Integer> decompose(int number) { 
		ArrayList<Integer> digits =new ArrayList<Integer>();
		
		while (number != 0) {
			digits.add(0, number % 10);
			number /= 10;
		}
		
		return digits;
	}
	
	public boolean isSmithNumber(int number) {
		List<Integer> factors = defactor(number);
		List<Integer> digits = decompose(number);
		
		int totalFactors = 0, totalDigits = 0;
		
		for (int i : factors) { 
			List<Integer> subfactors = decompose(i);
			for (int j : subfactors) { 
				totalFactors += j;
			}
		}
		
		for (int i : digits) { 
			totalDigits += i;
		}
		
		return totalDigits == totalFactors;
	}
	
	public boolean needsBuild(int number) {
		return primeNumbers.get(lastIndex) * lastIndex < number;
	}
	
	
	public static void main(String[] args)  {
		
		StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		SmithNumberSolution solution = new SmithNumberSolution();
		boolean isSmithNumber = solution.isSmithNumber(4937775);
		System.out.println("4937775 is smith number : " + isSmithNumber);
		stopwatch.split();
		System.out.println("Elapsed time: " + stopwatch.getSplitTime());
		stopwatch.stop();
	}
}
