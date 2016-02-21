package searches;
import java.util.*;
import java.math.*;

import javax.swing.text.MaskFormatter;

public class ContainsNearbyAlmostDuplicate {

	public static void main(String[] args) {
//		int[] array = new int[] { 1, 3, 5, 6, 3, 9, 10};
//		int[] array = new int[] { 99, 99};
//		int[] array = new int[] {1};
		int[] array = new int[] {-1, 2147483647};
		
		ContainsNearbyAlmostDuplicate solution = new ContainsNearbyAlmostDuplicate();
		boolean contains = solution.containsNearbyAlmostDuplicate(array, 1, 2147483647);
		
		if (contains) {
			System.out.println("Contains");
		} else { 
			System.out.println("Does not contains");
		}
	}
	
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) { 
		/*
		// first naively, the brute force solution would be 
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; i + j < Math.min(nums.length -i, k+1); j++) {
				if (Math.abs(nums[i], num[j]) <= t)
					return true;
			}
		}
		
		return false;
		*/
		
		/* 
		// the problem below is that it also creates a lot of assisting structure, which consumes a lot of time&space 
		// HashSet & HashMap
		HashMap<Integer, HashSet<Integer>> map = new HashMap<Integer, HashSet<Integer>>();
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(nums[i])) {
				HashSet<Integer> set = map.get(nums[i]);
				for (Integer n : set) { 
					if (i - n <= k) {
						return true;
					}
				}
				
				for (int j = Math.max(1, nums[i] - t); j <= nums[i] + t; j++) { 
					if (j != i) {
						if (map.containsKey(j)) {
							final int refI = i;
							map.get(j).removeIf(r -> nums[r] == nums[refI]);
							map.get(j).add(i);
						
						}
						else {
							map.put(j, new HashSet<Integer>());
						}	
					}
				}
			}
			else {
				for (int j = Math.max(1, nums[i] - t); j < nums[i] + t; j++) {
					if (i != j) {
						if (map.containsKey(j)) {
							final int refI = i;
							map.get(j).removeIf(r -> nums[r] == nums[refI]);
							map.get(j).add(i);
						
						}
						else {
							map.put(j, new HashSet<Integer>());
						}	
					}					
				}
			}
		}
		
		return false;
		*/
		
		/*
		// we have to be smart, and we can divide severals buckets, each bucket is a set/dictionary
		// bucket -> (value -> index)
		HashMap<Integer, HashMap<Integer, Integer>> maps = new HashMap<Integer, HashMap<Integer, Integer>>();
		if (t <= 0) 
		{
			// similar to the following, however, worrying that time complexity.
		}
		else
		{
			for (int i = 0; i < nums.length; i++) { 
				int bucket = nums[i] / t;
				
				if (maps.containsKey(bucket)) {
					for (int j : maps.get(bucket).values()) {
						int diff = i - j;
						if (diff <= k)
							return true;
					}
				}
				
				if (maps.containsKey(bucket - 1)) {
					for (int j : maps.get(bucket - 1).keySet()) { 
						if (Math.abs(nums[i] - j) <= t) {
							int diff = i - maps.get(bucket - 1).get(j);
							if (diff <= k) 
								return true;
						}
					}
				}
				
				if (maps.containsKey(bucket + 1)) {
					for (int j : maps.get(bucket + 1).keySet()) { 
						if (Math.abs(nums[i] - j) <= t) {
							int diff = i - maps.get(bucket + 1).get(j);
							if (diff <= k) 
								return true;
						}
					}
				}
				
				if (!maps.containsKey(bucket)) {
					maps.put(bucket, new HashMap<Integer, Integer>());
				}
				
				maps.get(bucket).put(nums[i], i);
			}
		}
		
		return false;
		
		*/
		
		// References the implementation 
		//   https://leetcode.com/discuss/48670/o-n-python-using-buckets-with-explanation-10-lines
		HashMap<Integer, Integer> buckets = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			// t== 0 is a special case where we only have to check the bucket that i is in
			int bucketNum, offset;
			
			if (t > 0) { 
				bucketNum = nums[i] / t;
				offset = 1;
			} else { 
				bucketNum = nums[i];
				offset = 0;
			}
			
			for (int j = bucketNum - offset; j <= bucketNum + offset; j++) { 
				if (buckets.containsKey(j)) {
					if (Math.abs((long)(buckets.get(j) - nums[i])) <= (long)t)
						return true;
				}
			}
			
			// update the bucket
			buckets.put(bucketNum, nums[i]);
			
			// cut down unnecessary  
			if (buckets.size() > k) { 
				if (t > 0) {
					buckets.remove(nums[i-k]/t);
				} else { 
					buckets.remove(nums[i-k]);
				}
			}
		}
		
		return false;
	}
}
