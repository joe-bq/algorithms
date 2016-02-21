package reversepolish;

import java.util.*;

public class Solution {
	public int evalRPN(String[] tokens) {
		Stack stack = new Stack();
		for (String i : tokens) { 
			switch (i) {
			case "+":
				int op1 = (int)stack.pop();
				int op2 = (int)stack.pop();
				stack.push(op1 + op2);
				break;
			case "-":
				op1 = (int)stack.pop();
				op2 = (int)stack.pop();
				stack.push(op2 - op1);
				break;
			case "*":
				op1 = (int)stack.pop();
				op2 = (int)stack.pop();
				stack.push(op1 * op2);
				break;
			case "/":
				op1 = (int)stack.pop();
				op2 = (int)stack.pop();
				stack.push(op2 / op1);
				break;				
			default:
				stack.push(Integer.parseInt(i));
				break;
			}
			
		}
		
		return (int)stack.pop();
	}
	
	public static void main(String args[])
	{
		Solution solution = new Solution();
		System.out.println(solution.evalRPN(args));
	}
}
