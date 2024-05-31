import java.util.Stack;

/*
 * Problem 227 : Basic Calculator II
 * 
Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

Example 1:

Input: "3+2*2"
Output: 7
Example 2:

Input: " 3/2 "
Output: 1
Example 3:

Input: " 3+5 / 2 "
Output: 5
Note:

You may assume that the given expression is always valid.
Do not use the eval built-in library function.
 */
public class BasicCalculator2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	public int calculate(String s) {
		int len;
		if (s == null || (len = s.length()) == 0)
			return 0;
		Stack<Integer> stack = new Stack<Integer>();
		int num = 0;
		char sign = '+'; // this is used to track the previous sign
		for (int i = 0; i < len; i++) {
			if (Character.isDigit(s.charAt(i))) {
				num = num * 10 + s.charAt(i) - '0';
			}
			// note: this is not else if but instead its if so that it gets executed for the condition when i == len - 1
			if ((!Character.isDigit(s.charAt(i)) && ' ' != s.charAt(i)) || i == len - 1) { // i == len - 1 is very important as its used to push the last number in the stack
				// perform the action based on the previous sign encountered
				if (sign == '-') {
					stack.push(-num); // we negate the number itself while pushing it, so in the end everything just needs to be added and we dont need to deal with subtraction separately
				}
				if (sign == '+') {
					stack.push(num);
				}
				if (sign == '*') {
					stack.push(stack.pop() * num); // note we immediately perform the * operation to follow bodmas rule
				}
				if (sign == '/') {
					stack.push(stack.pop() / num); // note we immediately perform the / operation to follow bodmas rule
				}
				sign = s.charAt(i); // accept the new sign
				num = 0;
			}
		}

		int re = 0;
		for (int i : stack) {
			re += i;
		}
		
		return re;
	}
}
