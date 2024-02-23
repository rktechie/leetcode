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

	/*
	 * Time complexity O(n^2)  --- Not a dp solution but a very efficient soln with same complexity as dp
	 * 
	 * We pick a center and keep expanding around it.
	 * 
	 * Each letter is a center (for odd length palindrome scenario) 
	 * and the spaces between the letter are centers too (for even length palindrome scenario)
	 * 
	 */
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
		// loop until meet invalid match
		while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
			j--;
			k++;
		}
		
		int newLen = k - j - 1;
		
		if (maxLen < newLen) {
			lo = j + 1;
			maxLen = newLen;
		}
	}
	
	/*
	 * Dp solution
	 */
	public static String longestPalindrome2(String s) {
		int n = s.length();
		String res = null;
		int palindromeStartsAt = 0, maxLen = 0;

		// dp[i][j] indicates whether substring s starting at index i and ending at j is palindrome
		boolean[][] dp = new boolean[n][n];

		for(int i = n-1; i >= 0; i--) { // keep increasing the possible palindrome string
				for(int j = i; j < n; j++) { // find the max palindrome within this window of (i,j)

						//check if substring between (i,j) is palindrome
						dp[i][j] = (s.charAt(i) == s.charAt(j)) // chars at i and j should match
											 &&
											 ( j-i < 3  // if window is less than or equal to 3, just end chars should match
												 || dp[i+1][j-1]  ); // if window is > 3, substring (i+1, j-1) should be palindrome too

						//update max palindrome string
						if(dp[i][j] && (j-i+1 > maxLen)) {
								palindromeStartsAt = i;
								maxLen = j-i+1;
						}
				}
		}

		return s.substring(palindromeStartsAt, palindromeStartsAt+maxLen);
	}
}
