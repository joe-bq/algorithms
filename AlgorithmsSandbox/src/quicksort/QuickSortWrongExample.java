package quicksort;


import static java.lang.System.out;

public class QuickSortWrongExample {

	public static void main(String[] args) {
		QuickSortWrongExample example = new QuickSortWrongExample();

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
        int i = r;
        for (int j = i; i < q; j++) { 
        	/* the problem with this algorithm is the comparison of data[j] and data[q];
        	 * 
        	 *  if this is all increasing (no swap happens), we have to consider the final return i should be 
        	 *  
        	 *  depends on the initial value of i, in this case, the initial i is r the little end. and if no swap happens, and when we sort decreasing, we should return the big end (q)
        	 *  
        	 *  
        	 *  */
            if (data[j] < data[q]) {
                // swap i and j
                int temp = data[i];
                data[i] = data[j];
                data[j] = temp;
                i++; // advance the i to next.
            }
        }

        // swap i and q
        int temp = data[i];
        data[i] = data[q];
        data[q] = temp;


        out.println(" after one pass, the data: ");
        print_data(data);

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
