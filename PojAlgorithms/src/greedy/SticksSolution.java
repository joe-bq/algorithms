package greedy;

import java.util.Scanner;

public class SticksSolution {
	/* private class */
	private class Stack {
		
		
		/* private fields */
		private int top;
		private int[] stack;
		private int size;
		
		/* constructor(s)  */
		public Stack(int size) {
			this.size = size;
			this.stack = new int[size];
			this.top = -1;
		}
		
		public boolean isEmpty() {
			return top < 0;
		}
		
		public boolean isFull() { 
			return top >= size - 1;
		}
		
		public void push(int value) {
			this.stack[++top] = value;
		}
		
		public int pop() {
			return this.stack[top--];
		}
		
		public int peek() {
			return this.stack[top];
		}
		
		public int getTop() { 
			return this.top;
		}
		
		public int[] getStack() { 
			return this.stack;
		}
	}
	
	private class StickInput { 
		/* private fields */
		private int count;
		private int[] sticks;
		
		/* Getter or setter */
		public int getCount() {
			return count;
		}
		
		public int[] getSticks() { 
			return sticks;
		}
		
		/* constructor(s) */
		public StickInput(int count, int[] sticks) { 
			this.count = count;
			this.sticks = sticks;
		}
	}
	
	
	StickInput getStickInput(Scanner scanner) {
		int count = scanner.nextInt();
		if (count != 0) {
			int[] sticks = new int[count];
			for (int i = 0; i< count; i++) {
				sticks[i] = scanner.nextInt();
			}
			
			sort(sticks, 0, count - 1);
			return this.new StickInput(count, sticks);
		}
		
		return null;
	}
	
	
	public int partition(int[] bucks, int i, int k){ 
		int storeIdx = i;
		for (int j = i; j < k; j++) {
			if (bucks[j] < bucks[k]) { 
				int temp = bucks[j];
				bucks[j] = bucks[storeIdx];
				bucks[storeIdx] = temp;
				storeIdx++;
			}
		}
		
		int temp = bucks[k];
		bucks[k] = bucks[storeIdx];
		bucks[storeIdx] = temp;
		return storeIdx;
	}
	
	public void sort(int[] bucks, int i, int k) { 
		if (i < k) { 
			int p  = partition(bucks, i, k);
			sort(bucks, i, p - 1);
			sort(bucks, p + 1, k);
		}
	}
	
	
	/* the main algorithm solution */
	
	public boolean canBeDivided(int count, int[] sticks, int length, int bags) {
		if (bags == 0) {
			return true;
		}
		
		int bagLength = 0;
		int stickLength = 0;
		int scanIdx = 0;
		Stack sackIndices = new Stack(count);
		Stack sackLengths = new Stack(count);

		
		for (int i = 0; i < count; i++) { 
			if (sticks[i] != 0) {
				bagLength += sticks[i];
				scanIdx = i;
				sackIndices.push(i);
				scanIdx = i;
				sackLengths.push(sticks[i]);
				sticks[i] = 0;
				break;
			}
		}
		
		while (!sackLengths.isEmpty()) { 
			boolean needsPop = true;
			for (int i = scanIdx + 1; i < count; i++) { 
				if (sticks[i] > stickLength) {
					if (sticks[i] + bagLength <= length) { 
						bagLength += sticks[i];
						scanIdx = i;
						sackIndices.push(i);
						sackLengths.push(sticks[i]);
						sticks[i] = 0;
					}
				}
				
				if (bagLength == length) { 
					needsPop = false;
					break;
				}
			}
			
			if (!needsPop)  {
				boolean greedyCanBedivided = canBeDivided(count, sticks, length, bags - 1);
				if (greedyCanBedivided) {
					/*
					System.out.print("Bags = " + new Integer(bags).toString() + " : ");
					int[] stack = sackLengths.getStack();
					for (int i = 0; i <= sackLengths.getTop(); i++) {
						System.out.print(new Integer(stack[i]).toString() + " ");
					}
					System.out.println();
					*/
					return true;
				} else { 
					needsPop = true;
				}
			}
			
			if (needsPop) {
				 scanIdx = sackIndices.pop();
				 stickLength = sackLengths.pop();
				 sticks[scanIdx] = stickLength;
				 bagLength -= stickLength;
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		SticksSolution solution = new SticksSolution();
		StickInput input;
		while ((input = solution.getStickInput(scanner)) != null) {
			int maxStick = 0;
			int totalLength = 0;
			for (int i : input.getSticks()) {
				if (maxStick < i) {
					maxStick = i;
				}
				
				totalLength += i;
			}
			
			
			int i = totalLength / 5;
			while (totalLength % i != 0) {
				i--;
			}
			
			for (; i > 0; i--) { 
				if (totalLength % i == 0) {
					if (solution.canBeDivided(input.getCount(), input.getSticks(), totalLength / i, i)) { 
						System.out.println(totalLength / i);
						break;
					}
				}
				
			}
		}
		
	}
}
