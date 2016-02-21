package quicksort;

import java.lang.*;
import static java.lang.System.out;


/* NOTE: THE FOLLOWING CODE DOES NOT WORK, WHICH SHOWS AN WRONG IMPLEMENTATION OF THE QUICK SORT 
 * 
 * THE FOLLOWING EXAMPLE GIVES THE FOLLOWING OUTPUT 
 * 
 * 
 * 5 19 42 34 21 7 69 
 */

public class QuickSortExample {
	public static void main(String[] args) throws UnsupportedOperationException{
		QuickSortExample example = new QuickSortExample();
		
		int[] data = new int[] { 34, 21, 42, 7, 19, 69, 5 };
		example.quicksort(data, 0, data.length - 1);
		
		example.print_data(data);
	}
	
	private void print_data(int[] data) {
		for (int i = 0; i < data.length; i++) { 
			out.print("" + data[i] + " ");
		}
		out.println();
	}
	
	public int partition(int[] data, int r, int q) { 
//		throw new UnsupportedOperationException();
		
		/* THE REASON WHY IT FAILS IS THAT -1 TO INDICATE NO EXCHANGE HAPPENS, WHIEL NO EXCHANGE HAPPENS DOES NOT MEAN WE DON'T NEED TO SORT ONE PART OR ANOTHER */
		// partition will partition the data into two parts, one have values less than the pivot, the other greater than the pivot.
		if (r < q) { 
			int i = r;
			int x = data[q];
			for (int j = i; j < q - 1; j++) {
				if (data[j] > x) {
					// swap i, j
					int temp = data[i];
					data[i] = data[j];
					data[j] = temp;
				}
			}
			
			// swap i, q
			int temp = data[q];
			data[q] = data[i];
			data[i] = temp;
			
			return i;
		}
		
		return -1; // or you can return the 
		
	}
	
	public void quicksort(int[] data, int r, int q) { 
		// the key to the quicksort is to halve the data into two parts.
		int i = partition(data, r, q);
		if (i != -1) {
			quicksort(data, r, i - 1);
			quicksort(data, i + 1, q);	
		}
		
	}
	
}
