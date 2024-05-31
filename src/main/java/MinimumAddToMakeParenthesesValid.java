/*
921. Minimum Add to Make Parentheses Valid
Medium
Topics
Companies
A parentheses string is valid if and only if:

It is the empty string,
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
You are given a parentheses string s. In one move, you can insert a parenthesis at any position of the string.

For example, if s = "()))", you can insert an opening parenthesis to be "(()))" or a closing parenthesis to be "())))".
Return the minimum number of moves required to make s valid.



Example 1:

Input: s = "())"
Output: 1
Example 2:

Input: s = "((("
Output: 3


Constraints:

1 <= s.length <= 1000
s[i] is either '(' or ')'.
 */
public class MinimumAddToMakeParenthesesValid {

  /*
   * Intuition: To make a string valid, we can add some ( on the left, and add some ) on the right. We need to find the number of each.
   * notOpened records the number of ( we need to add on the left of S
   * notClosed records the number of ) we need to add on the right of S, which equals to the number of current opened parentheses.
   *
   * Loop char c in the string S:
   * if (c == '('), we increment notClosed,
   * if (c == ')'), we decrement notClosed.
   * When right is already 0, we increment notOpened
   * Return notOpened + notClosed in the end
   *
   * Time O(N)
   * Space O(1)
   */
  public int minAddToMakeValid(String S) {
    int notOpened = 0, notClosed = 0;
    for (int i = 0; i < S.length(); ++i) {
      if (S.charAt(i) == '(') {
        notClosed++;
      } else if (notClosed > 0) {
        notClosed--;
      } else {
        notOpened++;
      }
    }
    return notOpened + notClosed;
  }
}
