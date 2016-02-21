package reversewords;

public class Solution {
	public String reverseWords(String s) {
		String[] splitted = s.split(" ");
		String[] result = new String[splitted.length];
		for (int i = 0; i < splitted.length; i++) {
			result[splitted.length - i - 1] = splitted[i];
		}
		return String.join(" ", result);
	}
	
	public static void main(String[] args)
	{
		System.out.println(new Solution().reverseWords(args[0]));
	}
}
