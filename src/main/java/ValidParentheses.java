/*
 * Problem 20: Valid Parentheses
 * 
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 */
import java.util.Stack;

public class ValidParentheses {

	public static void main(String[] args) {
		boolean b = isValid("()}");
		System.out.println(b);
	}

	public static boolean isValid(String s) {
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '{' || s.charAt(i) == '(' || s.charAt(i) == '[') {
				stack.push(s.charAt(i));
			} else if(s.charAt(i) == '}' && !stack.isEmpty()) {
				Character c = stack.pop();
				if (c != '{')
					return false;
			} else if (s.charAt(i) == ')'  && !stack.isEmpty()) {
				Character c = stack.pop();
				if (c != '(')
					return false;
			} else if (s.charAt(i) == ']' && !stack.isEmpty()) {
				Character c = stack.pop();
				if (c != '[')
					return false;
			} else {
				return false;
			}
		}
		
		if(stack.isEmpty())
			return true;
		else
			return false;
	}
}
