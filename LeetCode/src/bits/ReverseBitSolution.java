package bits;

import java.util.Stack;


/* the reference link to this page is availabe here : 
 * 
 * leetcode.com/2011/08/reverse-bits.html 
 */

public class ReverseBitSolution {

	public static void main(String[] args) {
		
		long[] longValues = new long[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		for (long i : longValues) { 
			long reversedValue = reverseXor(i);
			System.out.println(String.format("Original value: %d, reversed value: %d", i, reversedValue));
			System.out.println(String.format("Dumped value - Original - %s, Dumped value - reversed - %s", dumpLong(i), dumpLong(reversedValue)));
		}
		
		for (long i : longValues) { 
			long reversedValue = reverseMask(i);
			System.out.println(String.format("Original value: %d, reversed value: %d", i, reversedValue));
			System.out.println(String.format("Dumped value - Original - %s, Dumped value - reversed - %s", dumpLong(i), dumpLong(reversedValue)));
		}
	}
	
	/* since the limitation of java platform, we will use long 's lowest 32 bits to represent the unsigned value */
	public static long swapBits(long x, int i, int j) {
		
		long lo = ((x >> i) & 1);
		long hi = ((x >> j) & 1);
		if ((lo ^ hi) != 0) { 
			x ^= ((1 << i) | (1 << j));
		}
		
		return x;
	}
	
	public static long reverseXor(long x) { 
		
		// long n = sizeof(int) * 8;  /* in java, there is no size of operator */
		int n = Integer.SIZE;
		for (int i = 0; i < n/2; i++) {
			x = swapBits(x, i, 32 - i - 1);
		}
		return x;
	}
	
	/**
	 * 
	 * this uses a merge type of trick to reverse the order of bits.
	 * 
	 * 
	 * 				87654321
	 * 			/				\
	 * 		4321			    8765
	 * 		/						\
	 *    2143						6587
	 * 	  /							  \
	 *  1234						  5678
	 * 
	 * @param x the mask that to reverse the orders. 
	 * @return
	 */
	public static long reverseMask(long x) { 
		x = ((x & 0x55555555) << 1) | ((x & 0xAAAAAAAA) >> 1); 
		x = ((x & 0x33333333) << 2) | ((x & 0xCCCCCCCC) >> 2);
		x = ((x & 0x0F0F0F0F) << 4) | ((x & 0xF0F0F0F0) >> 4);
		x = ((x & 0x00FF00FF) << 8) | ((x & 0xFF00FF00) >> 8);
		x = ((x & 0x0000FFFF) << 16) | ((x & 0xFFFF0000) >> 16);
		return x;
	}
	
	
	/** 
	 *  dumpReverseLong
	 *  
	 *  @param x: the long value containing an integer value
	 */
	public static String dumpLong(long x) { 
		StringBuffer buffer = new StringBuffer();
		
		long hiBit = 0b10000000000000000000000000000000l; // you should not write as0b10000000000000000000000000000000; 
		int n = Integer.SIZE;
		for (int i = 0; i < n; i++) { 
			if ((x & hiBit) == 0) { 
				buffer.append('0');
			}	else {
				buffer.append('1');
			}
			x <<= 1;
		}
		
		return buffer.toString();
	}
	
	/** 
	 *  dumpReverseLong
	 *  
	 *  @param x: the long value containing an integer value
	 */
	public static String dumpReverseLong(long x) { 
		StringBuffer buffer = new StringBuffer();
		
		int n = Integer.SIZE;
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < n; i++) { 
			if ((x & 1) == 0) { 
				stack.push('0');
			} else { 
				stack.push('1');
			}
			x >>= 1;
		}
		
		while (!stack.empty()) {
			buffer.append(stack.pop());
		}
		
		return buffer.toString();
	}
}
