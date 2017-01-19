/*
 * Problem 14: Longest Common Prefix
 * 
Write a function to find the longest common prefix string amongst an array of strings.

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
