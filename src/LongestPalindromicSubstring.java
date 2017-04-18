/*
 * Problem 5: Longest Palindromic Substring
 * 
Given a string S, find the longest palindromic substring in S. 
You may assume that the maximum length of S is 1000, and there exists one unique 
longest palindromic substring.
 */

public class LongestPalindromicSubstring {

	public static void main(String[] args) {
		System.out.println(longestPalindrome("abcdbab"));
	}

	public static String longestPalindrome(String s) {
		StringBuilder sentence;
		String first;
		String current;
		StringBuilder reverse;
		String maxPalindrome = "";

		if (s.length() == 1) {
			return s;
		}

		for (int i = 0; i < s.length(); i++) {
			first = s.substring(i, i + 1);
			sentence = new StringBuilder(first);
			for (int j = i + 1; j < s.length(); j++) {
				current = s.substring(j, j + 1);
				// Keep appending elements one by one to the sentence so that it keeps growing
				sentence.append(current);
				// The the first letter (i.e i'th letter) is equal to the last letter (i.e j'th letter) then only it makes sense to check if the string formed is a palindrome or not
				// The the first and last letter are not same then it will never be a palindrome.
				if (first.equals(current)) {
					reverse = new StringBuilder(sentence.toString());
					reverse = reverse.reverse();
					if (reverse.toString().equals(sentence.toString()) && sentence.length() > maxPalindrome.length()) {
						maxPalindrome = sentence.toString();
					}
					reverse = new StringBuilder();
				}
			}
		}

		return maxPalindrome;
	}
}
