/*
767. Reorganize String
Medium
Topics
Companies
Hint
Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.

Return any possible rearrangement of s or return "" if not possible.



Example 1:

Input: s = "aab"
Output: "aba"
Example 2:

Input: s = "aaab"
Output: ""


Constraints:

1 <= s.length <= 500
s consists of lowercase English letters.
 */
public class ReorganizeString {

  /*
   * Approach: Counting and Odd/Even
   *
   * Organize the characters into two groups: even and odd indices.
   *
   * By filling all the even indices first, we create a structure where no adjacent characters are the same
   * within this group.
   * Similarly, we proceed to fill the odd indices, ensuring that adjacent characters within this group are
   * also different from each other.
   *
   * To guarantee a valid rearrangement, we need to ensure that the frequency of the most frequent letter
   * does not exceed half the length of s, rounded up
   *
   * We must start by placing the most frequent character of string s in the even positions (0, 2, 4, ...)
   *
   * Let N be the total characters in the string. Let k be the total unique characters in the string.
   * Time complexity: O(N). We will have to iterate over the entire string once to gather the counts of each character.
   * Space complexity: O(k). The counter used to count the number of occurrences will incur a space complexity of O(k)
   *
   * (this also also be solved using PriorityQueue but this solution is more optimized)
   */
  public String reorganizeString(String s) {
    int[] charCounts = new int[26]; // store the counts of each character in the input string s
    for (char c : s.toCharArray()) {
      charCounts[c - 'a']++;
    }
    int maxCount = 0, letter = 0;
    for (int i = 0; i < charCounts.length; i++) { // find the max count and the corresponding letter
      if (charCounts[i] > maxCount) {
        maxCount = charCounts[i];
        letter = i;
      }
    }
    if (maxCount > (s.length() + 1) / 2) { // if the count of any character exceeds half the length of the string (i.e. if it appears more than ceil(length/2) times), it is not possible to rearrange the characters
      return "";
    }

    char[] ans = new char[s.length()];
    int index = 0;

    // Place the most frequent character letter in the ans list at every second index until its count becomes zero.
    // Increment index by 2 for each placement and decrease the count of letter in char_counts.
    while (charCounts[letter] != 0) {
      ans[index] = (char) (letter + 'a');
      index += 2;
      charCounts[letter]--;
    }

    // Iterate through the remaining characters and their counts in char_counts
    for (int i = 0; i < charCounts.length; i++) {
      while (charCounts[i] > 0) { // if index exceeds the length of s, set index as 1 to place all future characters at odd indices.
        if (index >= s.length()) {
          index = 1;
        }
        ans[index] = (char) (i + 'a');
        index += 2;
        charCounts[i]--;
      }
    }

    return String.valueOf(ans);
  }
}
