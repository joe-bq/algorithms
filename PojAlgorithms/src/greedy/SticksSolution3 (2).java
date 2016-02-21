package greedy;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * this solution is about the greedy algorithm sticks, the link to the question is http://poj.org/problem?id=1011
 */

/*
Description

George took sticks of the same length and cut them randomly until all parts became at most 50 units long. Now he wants to return sticks to the original state, but he forgot how many sticks he had originally and how long they were originally. Please help him and design a program which computes the smallest possible original length of those sticks. All lengths expressed in units are integers greater than zero.
Input

The input contains blocks of 2 lines. The first line contains the number of sticks parts after cutting, there are at most 64 sticks. The second line contains the lengths of those parts separated by the space. The last line of the file contains zero.
Output

The output should contains the smallest possible length of original sticks, one per line.
Sample Input

9
5 2 1 5 2 1 5 2 1
4
1 2 3 4
0
Sample Output

6
5
Source

*/

public class SticksSolution3 {

	/* private class */
	class StickInput { 
		
		/* constructor */
		public StickInput(int count, int[] sticks) { 
			this.count = count;
			this.sticks = sticks;
		}
		
		/* private fields */
		private int count;
		private int[] sticks;
		
		/* getter and setter */
		public int getCount() { 
			return count;
		}
		
		public int[] getSticks() { 
			return sticks;
		}
		
		
		
	}
	
	class Stack { 
		/* Constructor(s) */
		public Stack(int size) { 
			stack = new int[size];
			top = -1;
		}
		
		/* fields */
		private int top;
		private int size;
		private int[] stack;
		
		
		public int getTop() { 
			return top;
		}
		
		public int getSize() { 
			return size;
		}
		
		public int pop(){
			return stack[top--];
		}
		
		public void push(int value) {
			stack[++top] = value;
		}
		
		public boolean isEmpty() { 
			return top < 0;
		}
		
	}
	
	
	private StickInput getInput(Scanner scanner) {
		// Scanner scanner = new Scanner(System.in);
		int count = scanner.nextInt();
		
		if (count != 0) {
			int[] sticks = new int[count];
			for (int i = 0; i < count; i++) { 
				sticks[i] = scanner.nextInt();
			}
			
			sort(sticks, 0, count - 1);
			return new StickInput(count, sticks);
		}
		
		return null;
	}
	
	public boolean canBeDivided(int count, int[] sticks,  int length, int bags) { 
		if (bags == 0) {
			return true;
		}
		
		Stack sack = new Stack(count);
		Stack sackIdx = new Stack(count);
		int sackLength = 0;
		int scanIdx = 0;
		int stickLength = 0;
		
		for (int i = 0; i < count; i++) {
			if (sticks[i] != 0) {
				sackLength = sticks[i];
				sack.push(sticks[i]);
				sackIdx.push(i);
				sticks[i] = 0;
				scanIdx = i;
				break;
			}
		}
				
		while (!sack.isEmpty()) {
			boolean exhausted = true;
			for (int i = scanIdx + 1; i < count && sackLength < length; i++) { 
				if (sticks[i] != 0 && sticks[i] != stickLength && sticks[i] + sackLength <= length) { 
					sackLength += sticks[i];
					sack.push(sticks[i]);
					sackIdx.push(i);
					sticks[i] = 0;
					scanIdx = i;
					stickLength = 0;
					exhausted = false;
					if (sackLength == length) {
						break;
					}
				}
			}
			
			if (sackLength == length && canBeDivided(count, sticks, length, bags - 1)) {
				return true;
			} else if (exhausted) { 
				// we have to pop, right?
				int index = sackIdx.pop();
				stickLength = sack.pop();
				sticks[index] = stickLength;
				sackLength -= stickLength;
				scanIdx = index;
			}
		}
		
		return false;
	}
	
	// Check on how the qujick sort works
	//   http://en.wikipedia.org/wiki/Quicksort
	private int partition(int[] bucks, int i, int k) {
		// choose the tail index as the pivot value
		int p = i; 
		int q = k;
		
		for (int j = i; j < k; j++) {
			if (bucks[j] < bucks[q]) {
				int temp = bucks[p];
				bucks[p] = bucks[j];
				bucks[j] = temp;
				p++;
			}
		}
		int temp = bucks[p];
		bucks[p] = bucks[q];
		bucks[q] = temp;
		return p;
	}
	
	private void sort(int[] bucks, int i, int k) {
		if (i < k) { 
			int p = partition(bucks, i, k);
			sort(bucks, i, p - 1);
			sort(bucks, p + 1, k);
		}
	}
	
	public static void main(String[] args) {
		SticksSolution3 solution = new SticksSolution3();
		ArrayList<StickInput> inputs = new ArrayList<StickInput>();
		
		StickInput input;
		// int j = 0;
		Scanner scanner = new Scanner(System.in);
		while ((input = solution.getInput(scanner)) != null) { 
			// j++;
			inputs.add(input);
		}

		for (StickInput in : inputs) { 
			int totalLength = 0;
			int maxLength = 0;
			for (int stick : in.getSticks()) { 
				totalLength += stick;
				if (stick > maxLength) {
					maxLength = stick;
				}
			}
			
			for (int i = totalLength / 5; i > 0; i--) {
				if (totalLength % i == 0 && totalLength / i >= maxLength) {
					if (solution.canBeDivided(in.getCount(), in.getSticks(), totalLength / i, i)) {
						System.out.println(totalLength / i);
						break;
					}
				}
				
			}
		}
	}
}
