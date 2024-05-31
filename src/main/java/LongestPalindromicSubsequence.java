/*
516. Longest Palindromic Subsequence
Medium
Topics
Companies
Given a string s, find the longest palindromic subsequence's length in s.

A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.



Example 1:

Input: s = "bbbab"
Output: 4
Explanation: One possible longest palindromic subsequence is "bbbb".
Example 2:

Input: s = "cbbd"
Output: 2
Explanation: One possible longest palindromic subsequence is "bb".


Constraints:

1 <= s.length <= 1000
s consists only of lowercase English letters.
 */
public class LongestPalindromicSubsequence {

  /*
   * Approach 1: Iterative Dynamic Programming
   *
   * We can also use a bottom-up approach to solve such problems without using recursion. We build answers
   * to subproblems iteratively first, then use them to build answers to larger problems.
   *
   * We create a 2D-array dp, where dp[i][j] contains the answer of the longest palindromic subsequence of
   * the substring formed from index i to j in s. Our answer would be dp[0][n - 1], where n is the size of s.
   * The state transition would be as follows:
   * If s[i] == s[j], perform dp[i][j] = 2 + dp[i + 1][j - 1].
   * Otherwise, perform dp[i][j] = max(dp[i][j - 1], dp[i + 1][j].
   *
   * Using two pointers: We can use two pointers, i and j, where i points to the first character of the
   * substring under consideration and j points to the last character.
   * Using dp entries corresponding to all the substrings formed by selecting indices within the range from
   * i to j (inclusive), we form answers for all the substrings that start index i - 1.
   * The pointer j moves from j = i - 1 to j = n - 1 to cover all possible substrings that start at index i - 1.
   * (we can also choose to move from i to j + 1, i.e., from left to right).
   * From the end of the string, we move from right to left, decrementing i by 1 until we reach the index 0.
   *
   * Here, n is the length of s.
   * Time complexity: O(n^2) - We fill the dp array which takes (n^2) time.
   * Space complexity: O(n^2) - The dp array consumes O(n^2) space.
   */
  public int longestPalindromeSubseq(String s) {
    int[][] dp = new int[s.length()][s.length()];

    for (int i = s.length() - 1; i >= 0; i--) {
      dp[i][i] = 1; // initialization. =1 because it denotes just one character
      for (int j = i + 1; j < s.length(); j++) {
        if (s.charAt(i) == s.charAt(j)) { // if the first and last characters match
          dp[i][j] = dp[i + 1][j - 1] + 2; // we include these two characters (at i and j) in the palindromic subsequence and add it to the longest palindromic subsequence formed using the substring from index i + 1 to j - 1 (inclusive).
        } else { // if the first and the last characters do not match
          dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]); // we look for the longest palindromic subsequence in both the substrings formed after ignoring the first and last characters. We pick the maximum of these two.
        }
      }
    }

    return dp[0][s.length() - 1];
  }

  /*
   * Approach 2: Dynamic Programming with Space Optimization
   * (instead of 2d array, we use 1d array to optimize space. its important to learn both 2d showed in approach 1
   * and 1d method so you learn how to eventually think in 1d array for dp)
   *
   * We can define a 1D array dp of size n to store the lengths of the longest palindromic subsequences of
   * substrings ending at each index.
   * - We can initialize dp[i] to 1 for all i in [0, n-1] , since a single character is a palindrome of length 1.
   * - We can then iterate over the columns of the memoization table, and for each column j , we can iterate
   * over the rows of the memoization table in reverse order, and for each row i , we can compute dp[i] based
   * on the values of dp[i+1] and dp[i] from the previous column, and update max_len accordingly.
   *
   * Here, n is the length of s.
   * Time complexity: O(n^2)
   * Initializing the dp and dpPrev arrays take O(n) time.
   * To get the answer, we use two loops that take O(n^2) time.
   *
   * Space complexity: O(n)
   * The dp and dpPrev arrays take O(n) space each.
   */
  public int longestPalindromeSubseq2(String s) {
    int n = s.length();
    int[] dp = new int[n];
    int[] dpPrev = new int[n];

    for (int i = n - 1; i >= 0; --i) {
      dp[i] = 1;
      for (int j = i + 1; j < n; ++j) {
        if (s.charAt(i) == s.charAt(j)) {
          dp[j] = dpPrev[j - 1] + 2;
        } else {
          dp[j] = Math.max(dpPrev[j], dp[j - 1]);
        }
      }
      dpPrev = dp.clone();
    }

    return dp[n - 1];
  }
}
