/*
 * Problem 10: Regular Expression Matching
 * 
Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true
 */

public class RegularExpressionMatching {

	public static void main(String[] args) {
		boolean b = isMatch("abcd", "d*");
		System.out.println(b);
	}

	// Easy solution found on the internet
	/*
	 * The key of the problem is to check if p[j + 1] is a '*', and has two
	 * cases: 1. If p[j + 1] is a '.', then this case is simple. Just need to
	 * check s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'. If not, return
	 * false, else s and p goes to the next character, ie. i + 1, j + 1.
	 * 
	 * 2. If p[j + 1] is a "*", the case is a bit tricky. Suppose that if s[i],
	 * s[i + 1], s[i + 2] .. s[i + k] is equal to p[j], that means all those
	 * could be the possible matches. So we need to check the rest of (i, j +
	 * 2), (i + 1, j + 2), (i + 2, j + 2), ... (i + k, j + 2).
	 */
	public static boolean isMatch(String s, String p) {
		if (p == null || p.length() == 0) {
			return s == null || s.length() == 0;
		}

		// Case 1: p.length() == 1
		if (p.length() == 1) {
			if (s == null || s.length() == 0) {
				return false;
			}
			if (s.charAt(0) != p.charAt(0) && p.charAt(0) != '.') {
				return false;
			}
			return isMatch(s.substring(1), p.substring(1));
		}

		// Case 2: p.charAt(1) != '*'
		if (p.charAt(1) != '*') {
			if (s.length() == 0) {
				return false;
			}
			if (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') {
				return isMatch(s.substring(1), p.substring(1));
			} else {
				return false;
			}
		} else { // case 3
			if (isMatch(s, p.substring(2))) {
				return true;
			}
			int i = 0;
			while (i < s.length() && (s.charAt(i) == p.charAt(0) || p.charAt(0) == '.')) {
				if (isMatch(s.substring(i + 1), p.substring(2))) {
					return true;
				}
				i++;
			}

			return false;
		}
	}

	// Better solution using recursion even for the case involving *
	public static boolean isMatch1(String s, String p) {
		if (p.length() == 0)
			return s.length() == 0;

		if (p.length() == 1) {
			if (s.length() != 1)
				return false;
			return (s.charAt(0) == p.charAt(0)) || (p.charAt(0) == '.');
		}

		if (s.length() != 0 && (p.charAt(0) == s.charAt(0) || (p.charAt(0) == '.'))) {
			if (p.charAt(1) == '*')
				// 1st half of OR is for the case if there are 1 or more
				// elements for the * in s.
				// 2nd half of OR is for the case if there are 0 elements for
				// the * in s, So thats why to move on we do substring(2) in p
				return isMatch1(s.substring(1), p) || isMatch1(s, p.substring(2));
			return isMatch1(s.substring(1), p.substring(1));
		}

		return p.charAt(1) == '*' && isMatch1(s, p.substring(2));
	}
}
