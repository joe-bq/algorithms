package radixsort;

import static java.lang.System.out;

import java.util.ArrayList;

public class RadixSortJavaStyleExample {

	/* THIS EXAMPLE WILL SHOW RADIX SORT IN MORE JAVA LIKE STYLE AND WITH INSERSION SORT.. */
	public static void main(String[] args) {
		int[] data = new int[] { 323, 145, 773, 281, 127, 422, 369 };
		
		RadixSortJavaStyleExample example = new RadixSortJavaStyleExample();
		example.radixsort(data);
		
		example.print_data(data);
	}
	
	private void print_data(int[] data) {
		for (int i: data) {
			out.print("" + i + " ");
		}
		
		out.println();
	}
	
	public void radixsort(int[] data) {
		int base = 10;
		ArrayList<ArrayList<Integer>> w = new ArrayList<ArrayList<Integer>>(base); // though we have created ArrayList<ArrayList<Integer>(base) and reserved the space, 
																				   // but it does not mean we have already have the element ready!
		
		for (int i = 0; i < base; i++) {
			w.add(new ArrayList<Integer>());
		}
		
		for (int i = 0; i < w.size(); i++) {
			w.get(i).clear();
		}
		
		int exp = 1;
		int max = data[0];
		for (int i = 1; i < data.length; i++) {
			if (data[i] > max) { 
				max = data[i];
			}
		}
		
		while (max > exp) {
			for (int i = 0; i < data.length; i++) {
				w.get((data[i] / exp) % base).add(data[i]);
			}
			
			int j = 0;
			for (ArrayList<Integer> l : w) {
				for (int k : l) {
					data[j++] = k;
				}
			}
			
			for (ArrayList<Integer> l : w) {
				l.clear();
			}
			
			exp *= base;
		}
	}
	
}
