package quicksort;

import static java.lang.System.out;

public class QuickSortDecreasingExample {
	public static void main(String[] args) {
		QuickSortDecreasingExample example = new QuickSortDecreasingExample();

	        int[] data = new int[] { 34, 21, 42, 7, 19, 69, 5 };
	        out.println(" before quick sort, data:");
	        example.print_data(data);
	        example.quicksort(data, 0, data.length - 1);
	        out.println(" after quick sort, data:");
	        example.print_data(data);
	}
	
	private void print_data(int[] data) {
        for (int i = 0; i < data.length; i++) { 
            out.print("" + data[i] + " ");
        }
        out.println();
    }

    public int partition(int[] data, int r, int q) {
    	/*
    	 * NOTE: since we are doing the decreasing sort, we have to choose the right initial value of i (when no swap happens, we should return q rather than r 
    	 * 
    	 */
        int i = q - 1;
        for (int j = i; j >= r; j--) { 
            if (data[j] > data[q]) {
                // swap i and j
                int temp = data[i];
                data[i] = data[j];
                data[j] = temp;
                i--; // advance the i to next.
            }
        }

        i++;
        int temp = data[q];
        data[q] = data[i];
        data[i] = temp;
        
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
