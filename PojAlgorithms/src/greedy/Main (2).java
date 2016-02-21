package greedy;

import java.util.Scanner;

public class Main {

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
	
	
	private StickInput getInput() {
		Scanner scanner = new Scanner(System.in);
		
		
		int count = scanner.nextInt();
		
		if (count != 0) {
			int[] sticks = new int[count];
			for (int i = 0; i < count; i++) { 
				sticks[i] = scanner.nextInt();
			}
			
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
				if (sticks[i] != 0 && sticks[i] + sackLength <= length) { 
					sackLength += sticks[i];
					sack.push(sticks[i]);
					sackIdx.push(i);
					sticks[i] = 0;
					scanIdx = i;
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
				int stickLength = sack.pop();
				sticks[index] = stickLength;
				sackLength -= stickLength;
				scanIdx = index;
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		Main solution = new Main();
		
		StickInput input;
		while ((input = solution.getInput()) != null) { 
			int totalLength = 0;
			int maxLength = 0;
			for (int stick : input.getSticks()) { 
				totalLength += stick;
				if (stick > maxLength) {
					maxLength = stick;
				}
			}
			
			for (int i = totalLength / 5; i > 0; i--) {
				if (totalLength % i == 0 && totalLength / i >= maxLength) {
					if (solution.canBeDivided(input.getCount(), input.getSticks(), totalLength / i, i)) {
						System.out.println(totalLength / i);
					}
				}
				
			}
		}
	}
}
