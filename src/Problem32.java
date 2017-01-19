/*
 *  NOTE: We are NOT storing the brackets in the stack. We are only going to store the count value which was there when we encountered the '('.
  		  So indirectly, for each '(', a count a stored.

 *  Algorithm:
	1. On encountering '(', push the count in the stack and reset the count.
	   The count is pushed so that the current sequence count of the well formed parenthesis till that point is stored.
	   Now, as there is '(', so a fresh set of parenthesis is starting, therefore, we reset the count.
	   
	2. On encountering ')' and if the stack if not empty, pop the count from the stack and compare it with the result
	
	3. The else is very important for cases such as ")())()()". The answer without else is 6 but the actual answer should be 4.
	   So if a closing bracket comes and the stack is empty, then we have to reset the count. 
	   Because count is used to store the sequence of well formed parenthesis, now if we encounter ')' and there was no count for '(' in the stack, means that ')' is extra.
	   So count is reset.
 */

import java.util.Stack;

public class Problem32 {

	public static void main(String[] args) {
		int t = longestValidParentheses(")())()()");
		System.out.println(t);
	}

	public static int longestValidParentheses(String s) {
		Stack<Integer> stack = new Stack<Integer>();
		int count = 0;
		int res = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push(count);
				count = 0;
			} else if (s.charAt(i) == ')' && !stack.isEmpty()) {
				count = count + (1 + stack.pop());
				res = Math.max(res, count);
			} else { // very important
				count = 0;
			}
		}
		
		return res * 2; // Multiply by 2 because they need the total length. We are only keeping a count of the set and not of each bracket.
	}

}
