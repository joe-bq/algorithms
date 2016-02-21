package sets;

import java.util.List;
import java.util.ArrayList;
import static java.lang.System.out;
import java.util.Arrays;
import java.util.Collections;


public class SubsetsWithDupRecursion3 {
	public static void main(String[] args) {
		int[] nums = new int[] { 1, 1, 2, 2, 3, 3, };
		SubsetsWithDupRecursion2 solution = new SubsetsWithDupRecursion2();
		
		Arrays.sort(nums);

		List<List<Integer>> result = solution.getListWithDups(nums);
		for (List<Integer> list : result) {
			out.print("[");
			for (int i : list) {
				out.print(i);
			}
			out.print("]");
		}
	}
	
	public List<List<Integer>> getListWithDups(int[] nums) {
		
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		getListWithDupsCore(nums, new ArrayList<Integer>(), 0, result);
		return result;
	}
	
	public void getListWithDupsCore(int[] nums, List<Integer> path, int index, List<List<Integer>> result) {
		result.add(path);
		
		int count = 0;
		for (int i = index; i < nums.length; i++ ) {
			if (nums[i] == nums[index]) {
				count++;
			} else { 
				break;
			}
		}
		
		for (int i = 0; i < count; i++) {
			ArrayList<Integer> newPath = new ArrayList<Integer>(path);
			for (int j = 0; j < i; j++) { 
				newPath.add(nums[index]);
			}
			
			getListWithDupsCore(nums, newPath, index + count, result);
		}
	}
}
