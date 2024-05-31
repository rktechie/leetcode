/*
28. Find the Index of the First Occurrence in a String

Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.



Example 1:

Input: haystack = "sadbutsad", needle = "sad"
Output: 0
Explanation: "sad" occurs at index 0 and 6.
The first occurrence is at index 0, so we return 0.
Example 2:

Input: haystack = "leetcode", needle = "leeto"
Output: -1
Explanation: "leeto" did not occur in "leetcode", so we return -1.


Constraints:

1 <= haystack.length, needle.length <= 104
haystack and needle consist of only lowercase English characters.
 */
public class FindIndexFirstOccurrenceString {

  /**
   * Approach 1: Sliding Window
   *
   * Time complexity: O(nm). For every i, we may have to iterate at most m times.
   * There are n−m+1 such i's. Thus, it is O((n−m+1)⋅m), which is O(nm).
   *
   * Space complexity: O(1).
   */
  public int strStr(String haystack, String needle) {
    int index = 0;
    int j = 0;
    if(haystack.length() == 0 && needle.length() == 0)
      return 0;
    if(haystack.length() < needle.length())
      return -1;
    for (int i = 0; i <= haystack.length() - needle.length(); i++) {
      index = i;
      for (j = 0; j < needle.length(); j++) {
        if (haystack.charAt(index) != needle.charAt(j)) {
          break;
        }
        index++;
      }
      if (j == needle.length()) {
        return i;
      }
    }

    return -1;
  }
}
