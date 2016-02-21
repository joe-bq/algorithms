package greedy;

import java.util.Scanner;
import java.util.Stack;

public class SticksSolution4 {

	class StickInput { 
		/* private fields */
		private int count;
		private int[] sticks;
		
		/* Getter or setter */
		public int getCount() {
			return count;
		}
		
		public int[] getSticks() { 
			// return sticks;
			int[] sticksCopy = new int[count];
			for (int i = 0; i < count; i++) { 
				sticksCopy[i] = sticks[i];
			}
			return sticksCopy;
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
			return new StickInput(count, sticks);
		}
		
		return null;
	}
	
	private int partition(int[] bucks, int i, int k){ 
		int storeIdx = i;
		for (int j = i; j < k; j++) {
			if (bucks[j] > bucks[k]) { 
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
	
	private void sort(int[] bucks, int i, int k) { 
		if (i < k) { 
			int p  = partition(bucks, i, k);
			sort(bucks, i, p - 1);
			sort(bucks, p + 1, k);
		}
	}
	
	/* the main algorithm solution */
	public boolean canBeDivided(int count, int[] sticks, int length, int bags) {
		if (bags == 1) {
			return true;
		}
		
		int bagLength = 0;
		int stickLength = 0;
		int scanIdx = 0;
		Stack<Integer> sackIndices = new Stack<Integer>();
		Stack<Integer> sackLengths = new Stack<Integer>();
		
		for (int i = 0; i < count; i++) { 
			if (sticks[i] != 0) {
				bagLength += sticks[i];
				scanIdx = i;
				sackIndices.push(i);
				scanIdx = i;
				sackLengths.push(sticks[i]);
				stickLength = sticks[i] + 1;
				sticks[i] = 0;
				break;
			}
		}
		
		while (!sackLengths.isEmpty()) { 
			boolean needsPop = true;
			for (int i = scanIdx + 1; i < count; i++) { 
				if (sticks[i] < stickLength) {
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
		SticksSolution4 solution = new SticksSolution4();
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
			if (i != 0) { 
				while (totalLength % i != 0 && totalLength / i > maxStick) {
					i--;
				}	
			}
						
			for (; i > 0; i--) { 
				if (totalLength % i == 0 && totalLength / i >= maxStick) {
					if (solution.canBeDivided(input.getCount(), input.getSticks(), totalLength / i, i)) { 
						System.out.println(new Integer(totalLength / i).toString());
						break;
					}
				}
				
			}
		}
	}
}
