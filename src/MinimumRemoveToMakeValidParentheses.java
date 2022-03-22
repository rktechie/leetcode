import java.util.Stack;

/*
 * Problem : Minimum Remove to Make Valid Parentheses
 * 
Given a string s of '(' , ')' and lowercase English characters. 

Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.

Formally, a parentheses string is valid if and only if:

It is the empty string, contains only lowercase characters, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
 

Example 1:

Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
Example 2:

Input: s = "a)b(c)d"
Output: "ab(c)d"
Example 3:

Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.
Example 4:

Input: s = "(a(b(c)d)"
Output: "a(b(c)d)"
 

Constraints:

1 <= s.length <= 10^5
s[i] is one of  '(' , ')' and lowercase English letters.
 */
public class MinimumRemoveToMakeValidParentheses {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	// Read both the solutions
	
	/*
	 * Solution 1: To make the string valid with minimum removals, we need to get rid of all parentheses that do not have a matching pair.
	 * 
	 * We can track indexes of all mismatched parentheses, and erase them in the end going right-to-left. 
	 * We can introduce another stack to collect indexes of mismatched ')', or we can use the same stack and mark mismatched ')' somehow. Here, we just negate the index to indicate ')'.
	 * 
	 * Note that I am adding 1 to make the index 1-based. You cannot tell if zero is negated :)
	 * 
	 * Time complexity: O(n^2)
	 * in the while loop we are making deleteCharAt library calls which are O(n) and n number of times. 
	 */
	public String minRemoveToMakeValid(String s) {
		StringBuilder sb = new StringBuilder(s);
		Stack<Integer> st = new Stack<>();
		
		for (int i = 0; i < sb.length(); ++i) {
			if (sb.charAt(i) == '(')
				st.add(i + 1); // we add +ve for '('
			if (sb.charAt(i) == ')') {
				if (!st.empty() && st.peek() >= 0) // >=0 so that we only pop if the element in stack is '('
					st.pop();
				else
					st.add(-(i + 1)); // -ve for ')'. Only so that we can differentiate '(' from ')'.
			}                         // +1 just to make it 1-based for identification for examples such as ")ab(a)"
		}
		
		while (!st.empty())
			sb.deleteCharAt(Math.abs(st.pop()) - 1); // math.abs because now it doesnt matter if it is '(' or ')' as we just want to remove these extras
													 // -1 to make it 0-based again
		return sb.toString();
	}

	/*
	 * Solution 2: Same as above but without deleteCharAt
	 * If we want to optimize for the worst-case scenario, we should avoid deleteCharAt inside the loop. 
	 * Instead, we can copy characters that do not appear in the stack into another string builder. 
	 * Since characters in the stack are naturally sorted, we can use two-pointer technique to do it in the linear time.
	 * 
	 * Time complexity: O(n)
	 * We have 2 loops that are looping through up to n characters, doing an O(1) operation on each.
	 */
	public String minRemoveToMakeValid2(String s) {
		StringBuilder sb = new StringBuilder(s), sb1 = new StringBuilder();
		Stack<Integer> st = new Stack();
		for (int i = 0; i < sb.length(); ++i) {
			if (sb.charAt(i) == '(')
				st.add(i + 1);
			if (sb.charAt(i) == ')') {
				if (!st.empty() && st.peek() >= 0)
					st.pop();
				else
					st.add(-(i + 1));
			}
		}
		for (int i = 0, j = 0; i < sb.length(); ++i) {
			if (j >= st.size() || i != Math.abs(st.elementAt(j)) - 1) {
				sb1.append(sb.charAt(i));
			} else
				++j;
		}
		return sb1.toString();
	}
}
