/*
 * Problem 5: Longest Palindromic Substring
 * 
Given a string S, find the longest palindromic substring in S. 
You may assume that the maximum length of S is 1000, and there exists one unique 
longest palindromic substring.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"
 */

public class LongestPalindromicSubstring {

	public static void main(String[] args) {
		System.out.println(longestPalindrome("abcdbab"));
	}
	
	private static int lo, maxLen;

	public static String longestPalindrome(String s) {
		int len = s.length();
		if (len < 2)
			return s;
		
	    for (int i = 0; i < len-1; i++) {
	     	extendPalindrome(s, i, i);  //assume odd length, try to extend Palindrome as possible
	     	extendPalindrome(s, i, i+1); //assume even length.
	    }
	    
	    return s.substring(lo, lo + maxLen);
	}

	private static void extendPalindrome(String s, int j, int k) {
		while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
			j--;
			k++;
		}
		if (maxLen < k - j - 1) {
			lo = j + 1;
			maxLen = k - j - 1;
		}
	}
	
	/*
	 * Manachers Algorithm
	 * 
	 * We expand around the centers to find out a palindrome
	 * Each character is considered as a center and the each gap is also considered as a center
	 * 
	 * What is bad - that we expand around every center
	 * So to optimize this,  
	 * 
	 */
	public static void longestPalindrome2(String s) {
		
	}
}
