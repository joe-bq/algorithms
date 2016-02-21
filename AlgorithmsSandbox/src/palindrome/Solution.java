package palindrome;

/*
 Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome

 */
public class Solution {

    public boolean isPalindrome(String s) {
    	int p = 0, q = s.length() - 1;
    	
    	while (p <= q) {
    		/*
    		while (!isAlphanumeric(s.charAt(p))) p++;
    		while (!isAlphanumeric(s.charAt(q))) q--;
    		
    		if (s.charAt(p) != s.charAt(q)) { 
    			return false;
    		}
    		*/
    		
    		while (!Character.isAlphabetic(s.charAt(p))) p++;
    		while (!Character.isAlphabetic(s.charAt(q))) q--;
    		
    		if (Character.toUpperCase(s.charAt(p++)) != Character.toUpperCase(s.charAt(q--))) 
    			return false;
    	}
    	
        return true;
    }
    
    
    /* it seems that the java language has already provided a Character.isAlphabetic method ready for you to tuse */
    private boolean isAlphanumeric(char c) {
    	if (c < 0x30 || (c >= 0x3a && c <= 0x40) || (c > 0x5a && c <= 0x60) || c > 0x7a)
            return false;
    	return true;
    }
    
    
	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println("\"A man, a plan, a canal: Panama\"" + solution.isPalindrome("A man, a plan, a canal: Panama"));
		System.out.println("\"race a car\"" + solution.isPalindrome("race a car"));
	}
}
