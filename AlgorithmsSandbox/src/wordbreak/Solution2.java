package wordbreak;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].
 */

public class Solution2 {

	/* Private fields 
	 */
	private ArrayList<String> _breadcumbs = new ArrayList<String>();
	private List<String> _solution = new ArrayList<String>();
	
    public List<String> wordBreak(String s, Set<String> dict) {

    	Set<String> roots = new HashSet<String>();
    	for (String word : dict) { 
    		if (s.contains(word)) { 
    			roots.add(word);
    		}
    	}
    	
    	return wordBreakFiltered(s, roots);
    }
    
       
    public List<String> wordBreakFiltered(String s, Set<String> dict) { 
    	
    	if (s.equals(""))  _solution.add(trail());
    	
    	for (String word : dict) { 
    		if (s.startsWith(word)) {
    			_breadcumbs.add(word);
    			wordBreakFiltered(s.substring(word.length()), dict);
    			_breadcumbs.remove(_breadcumbs.size() - 1);
    		}
    	}
    	
    	return _solution;
    }
    
    private String trail() { 
    	return String.join(" ", _breadcumbs);
    }
    
	public static void main(String[] args) {
		Solution2 solution = new Solution2();
		// Initialize hashset values by construction 
		// http://stackoverflow.com/questions/2041778/how-to-initialize-hashset-values-by-construction
		List<String> sentences = solution.wordBreak("catsanddog", new HashSet<String>() {{add("cat"); add("cats"); add("and"); add("sand"); add("dog");}});
		for (String sentence : sentences) {
			System.out.println(sentence);
		}
	}
}
