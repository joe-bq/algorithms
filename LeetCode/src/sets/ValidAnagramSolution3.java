package sets;

import static java.lang.System.out;
import java.util.Arrays;
import java.util.Collections;

/*
 * this is a leet code problem and you can find the leet code from 
 * 		https://leetcode.com/problems/valid-anagram/
 * 
 * Here should be a simple slution where we relies on the api codes , sort and string.Compare
 * 
 * 
 */

public class ValidAnagramSolution3 {
	public static void main(String[] args) {
		String s = "anagram";
		String t = "nagaram";
		
		ValidAnagramSolution2 solution = new ValidAnagramSolution2();
		out.println("t isAnagram of t == " + solution.isAnagram(s, t));
	}
	
	public boolean isAnagram(String s, String t) {

		if (s.length() != t.length()) return false; 
		char[] sArrays = s.toCharArray();
		char[] tArrays = t.toCharArray();
		Arrays.sort(sArrays);
		Arrays.sort(tArrays);
		
		for (int i = 0; i < sArrays.length; i++) {
			if (sArrays[i] != tArrays[i]) return false;
			
		}
		
		return true;
		
	}
}
