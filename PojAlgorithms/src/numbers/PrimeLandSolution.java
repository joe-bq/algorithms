package numbers;

/* the question number is 1365 */


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PrimeLandSolution {

	/* private fields */
	private ArrayList<Integer> primeNumbers = new ArrayList<Integer>();
	private int lastIndex;
	private final int INVALID_INPUT = -1;
	
	public PrimeLandSolution() { 
		primeNumbers.add(2);
		primeNumbers.add(3);
		primeNumbers.add(5);
		primeNumbers.add(7);
		
		lastIndex = 3;
	}
	
	private int product(String input) {
		String[] sequence = input.split(" ");
		int number = 1;
		for (int i = 0; i < sequence.length; ) {
			
			int factor = Integer.parseInt(sequence[i++]);
			if (factor == 0) { 
				return INVALID_INPUT;
			}
			int count = Integer.parseInt(sequence[i++]);
			number *= Math.pow(factor, count);
		}
		
		return number;
	}
	
	private String readLine(BufferedReader reader) {
		String line;
		try {
			if ((line = reader.readLine()) != null) {
				int number = product(line);
				if (number == INVALID_INPUT) { 
					return "";
				}
				
				ArrayList<Integer> factors = build(number - 1);
				int factor = factors.get(0);
				int count = 1;
				StringBuilder builder = new StringBuilder();
				for (int i = 1; i < factors.size(); i++) { 
					if (factor == factors.get(i)) { 
						count++;
					} else { 
						builder.append(factor);
						builder.append(' ');
						builder.append(count);
						builder.append(' ');
						count = 1;
						factor = factors.get(i);
					}
				}
				
				builder.append(factor);
				builder.append(' ');
				builder.append(count);
				return builder.toString();
			}
		} catch (IOException ex) { 
			ex.printStackTrace();
		}
		
		return null;
	}


	private ArrayList<Integer> build(int number) { 
		
		ArrayList<Integer> factors = new ArrayList<Integer>();
		int index = 0;
		while (number > 1) {
			if (number % primeNumbers.get(index) == 0) {
				factors.add(0, primeNumbers.get(index));
				number /= primeNumbers.get(index);
			} else { 
				if (index < lastIndex) { 
					index++;
				} else {
					nextPrime();
					index++;
				}
			}
		}
		
		return factors;
	}
	
	private boolean isPrime(int number) { 
		for (int i = 0; i < lastIndex && i * primeNumbers.get(i) < number; i++){ 
			if (number % primeNumbers.get(i) == 0) {
				return false;
			}
		}
		
		return true;
	}
	
	private int nextPrime() {
		int number = primeNumbers.get(lastIndex) + 1;
		while (!isPrime(number)) {
			number++;
		}
		
		primeNumbers.add(number);
		lastIndex++;
		
		return number;
	}
	
	/* we don't need a defactor */
	
	
	public static void main(String[] args) {
		PrimeLandSolution solution = new PrimeLandSolution();
		String input = "17 1\n" +
						"5 1 2 1\n" + 
						"509 1 59 1\n" + 
						"0";
		
		// BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(input.getBytes())));
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String output;
		while (!(output = solution.readLine(reader)).equals("")) { 
			System.out.println(output);
		}
	}
	
}
