package quicksort;

import static java.lang.System.out;

public class QuickSortExample {
	
	public static void main(String[] args) {
        int[] data = new int[] { 34, 21, 42, 7, 19, 69, 5 };

        QuickSortExample example = new QuickSortExample();
        example.quicksort(data, 0, data.length - 1);
        example.print_data(data);
	}
	
	private void print_data(int[] data) {
		for (int i : data) {
			out.print("" + i + " ");
		}
		out.println();
	}
	
	public int partition(int[] data, int r, int q) {
		/* THERE IS NO NEED TO CHECK FOR r < q IN PARTITION METHOD, THINK OF WHY? */
		int i = r;
		for (int j = r; j < q; j++) {
			if (data[j] < data[q]) { 
				// swap i and  j
				int temp = data[i];
				data[i] = data[j];
				data[j] = temp;
				i++;
			}
		}

		// swap the i and q.
		int temp = data[i];
		data[i] = data[q];
		data[q] = temp;
		
		//i++; // advance the i again because 
		
		return i;
	}
	
	public void quicksort(int[] data, int r, int q) {
		if (r < q) {
			int i = partition(data, r, q);
			quicksort(data, r, i - 1);
			quicksort(data, i + 1, q);
		}
	}

}
