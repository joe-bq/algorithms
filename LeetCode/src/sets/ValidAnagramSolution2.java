package sets;


import static java.lang.System.out;

/*
 * this is a leet code problem and you can find the leet code from 
 * 		https://leetcode.com/problems/valid-anagram/
 * 
 * here we use a quick lookup table solution, where we have created a quick look-up table of size 256, (or 26 if you like) 
 * because we know in advance the input are chars.
 * 
 */

public class ValidAnagramSolution2 {
	public static void main(String[] args) {
		String s = "anagram";
		String t = "nagaram";
		
		ValidAnagramSolution2 solution = new ValidAnagramSolution2();
		out.println("t isAnagram of t == " + solution.isAnagram(s, t));
	}
	
	public boolean isAnagram(String s, String t) {
		char[] sChars = s.toCharArray();
		char[] tChars = t.toCharArray();
		
		// by default the value will be initialized to 0, no need to do initialization again.
		//
		int[] charArray = new int[256];
		
		for (char ch : sChars) { 
			charArray[ch]++;
		}
		
		for (char ch : tChars) { 
			if (charArray[ch] == 0) 
				return false;
			charArray[ch]--;
		}
		
		return true;
		
	}
}
