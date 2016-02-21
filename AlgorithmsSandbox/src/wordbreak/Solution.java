package wordbreak;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".
 */
public class Solution {
    public boolean wordBreak(String s, Set<String> dict) {
    	// constructs a substring list 
    	
    	Set<String> roots = new HashSet<String>();
    	for (String word : dict) { 
    		if (s.contains(word)) { 
    			roots.add(word);
    		}
    	}
    	
    	return wordBreakFiltered(s, roots);
    }
    
    private boolean wordBreakFiltered(String s, Set<String> roots) { 
    	if (s.equals("")) return true;
    	for (String word : roots) {
    		if (s.startsWith(word)) {
    			// if it allows repeat use of words 
    			// roots.remove(word);
    			if (wordBreakFiltered(s.substring(word.length()), roots)) {
    				return true;
    			}
    			// if it allows repeat use of words
    			//else {
    			//	roots.add(word);
    			//}
    		}
    	}
    	
        return false;
    }
    
    public static void main(String[] args) {
    	Solution solution = new Solution();
    	// How to initialize a hashset values by constructions.
    	// http://stackoverflow.com/questions/2041778/how-to-initialize-hashset-values-by-construction
    	System.out.println("Word break result :" + solution.wordBreak("leetcode", new HashSet(Arrays.asList("leet", "code"))));
    }
}