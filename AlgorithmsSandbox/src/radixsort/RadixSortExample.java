package radixsort;

import static java.lang.System.out;

public class RadixSortExample {
	public static void main(String[] args) {
		int[] data = new int[] { 323, 145, 773, 281, 127, 422, 369 };
		
		RadixSortExample example = new RadixSortExample();
		example.radixsort(data);
		
		example.print_data(data);
	}
	
	private void print_data(int[] data) {
		for (int i: data) {
			out.print("" + i + " ");
		}
		
		out.println();
	}
	
	/* NOTE: THIS IS A MORE C-STYLE IMPLEMENTATION, BUT WHICH IS QUIT PERFORMANT */
	public void radixsort(int[] data) {
		int base = 10;
		int exp = 1;
		
		int max = data[0];
		for (int i : data) {
			if (i > max) {
				max = i;
			}
		}
		
		// internally we will use the count sort (a stable sort to sort by each digit)
		
		int[] w = new int[data.length];
		
		for (int i = 0; i < data.length; i++) {
			w[i] = data[i];
		}
		
		int[] count = new int[base];
		for (int i = 0; i < base; i++) {
			count[i] = 0;
		}
		
		int[] w2 = new int[data.length];
		
		while (exp < max) {
			for (int i = 0; i < w.length; i++) {
				count[w[i] % base]++;
			}
			
			for (int i = 1; i < count.length; i++) {
				count[i] += count[i-1]; 
			}

			// copy w2 to w1
			for (int i = data.length-1; i >= 0; i--) {
				w2[count[w[i] % base]-1] = data[i];
				count[w[i] % base]--;
			}
			
			for (int i = 0; i < data.length; i++) {
				data[i] = w2[i];
			}
			
			exp *= base;
			
			for (int i = 0; i < data.length; i++) {
				w[i] = data[i] / exp;
			}
			
			for (int i = 0; i < count.length; i++) {
				count[i] = 0;
			}
		}
	}
	
}
