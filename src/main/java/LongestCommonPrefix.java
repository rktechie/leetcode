/*
 * Problem 14: Longest Common Prefix
 * 
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:

Input: ["flower","flow","flight"]
Output: "fl"


Example 2:

Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.

Note:
All given inputs are in lowercase letters a-z.

 */

public class LongestCommonPrefix {

    public static void main(String[] args) {
        String strs[] = { "aba", "aaa" };
        String t = longestCommonPrefix(strs);
        System.out.println(t);
    }
    
    public static String longestCommonPrefix(String[] strs) {
        String lcp = "";
        int numOfStrings = strs.length;

        if (numOfStrings == 0) {
            return "";
        } else if (numOfStrings == 1) {
            return strs[0];
        }

        // We compare 1st String with all the other strings.
        for (int i = 0; i < strs[0].length(); i++) {
            for (int j = 1; j < numOfStrings; j++) {
                // If any one of the string is smaller than our base string or
                // if any character does not match, then the loop shouldn't
                // continue as we have reached the lcp
                if (i >= strs[j].length() || strs[0].charAt(i) != strs[j].charAt(i)) {
                    return lcp;
                }
            }
            lcp = lcp + strs[0].charAt(i);
        }

        return lcp;
    }
}
