/*
680. Valid Palindrome II
Easy
Topics
Companies
Given a string s, return true if the s can be palindrome after deleting at most one character from it.



Example 1:

Input: s = "aba"
Output: true
Example 2:

Input: s = "abca"
Output: true
Explanation: You could delete the character 'c'.
Example 3:

Input: s = "abc"
Output: false


Constraints:

1 <= s.length <= 105
s consists of lowercase English letters.
 */
public class ValidPalindrome2 {

  // Approach: Two Pointers
  // follow up question is problem 1216. Valid Palindrome III
  public boolean validPalindrome(String s) {
    int i = 0;
    int j = s.length() - 1;

    while (i < j) {
      // Found a mismatched pair - try both deletions
      if (s.charAt(i) != s.charAt(j)) {
        return (checkPalindrome(s, i, j - 1) || checkPalindrome(s, i + 1, j));
      }
      i++;
      j--;
    }

    return true;
  }

  private boolean checkPalindrome(String s, int i, int j) {
    while (i < j) {
      if (s.charAt(i) != s.charAt(j)) {
        return false;
      }
      i++;
      j--;
    }

    return true;
  }
}
