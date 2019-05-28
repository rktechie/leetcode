
/*
 * Problem 3: Longest Substring Without Repeating Characters
 * 
Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 * Method 1:the basic idea is, keep a hashmap which stores the characters in
	 * string as keys and their positions as values, and keep two pointers which
	 * define the max substring.
	 * 
	 * Move the right pointer to scan through the string , and meanwhile update
	 * the hashmap. If the character is already in the hashmap, then move the
	 * left pointer to the right of the same character last found. Note that the
	 * two pointers can only move forward.
	 * 
	 * For j, we do j = Math.max(.....) because: j represents the start of the
	 * current string with unique characters, so if you have a string like abba,
	 * when you encounter last a, you want to mark the start of the string from
	 * index 2(second occurrence of b) which is the last known index which holds
	 * uniqueness assumption, not from map.get(a)+1 which is 1.
	 */
	public int lengthOfLongestSubstring(String s) {
		if (s.length() == 0)
			return 0;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		int max = 0;
		for (int i = 0, j = 0; i < s.length(); ++i) {
			if (map.containsKey(s.charAt(i))) {
				j = Math.max(j, map.get(s.charAt(i)) + 1);
			}
			map.put(s.charAt(i), i);
			max = Math.max(max, i - j + 1);
		}
		return max;
	}

	// Method 2:
	public int lengthOfLongestSubstring2(String s) {
		Set<Character> set = new HashSet<Character>();
		String current = "";
		String result = "";
		for (int i = 0; i < s.length() - 1 - result.length(); i++) {
			current = String.valueOf(s.charAt(i));
			set.add(s.charAt(i));
			for (int j = i + 1; j < s.length(); j++) {
				if (!set.contains(s.charAt(j))) {
					current = current + s.charAt(j);
					set.add(s.charAt(j));
				} else {
					if (result.length() < current.length()) {
						result = current;
					}
					break;
				}
			}
			set.clear();
		}

		return result.length();
	}

	// Method 3:
	public int lengthOfLongestSubstring3(String s) {
		final Set<Character> unique = new HashSet<>();
		int max = 0;
		for (int i = 0; i < s.length(); ++i) {
			final char c = s.charAt(i);
			if (!unique.add(c)) {
				for (int j = i - unique.size(); j < i; ++j) {
					if (s.charAt(j) != c) {
						unique.remove(s.charAt(j));
					} else {
						break;
					}
				}
			}
			max = Math.max(max, unique.size());
		}
		return max;
	}

	// Method 4:
	public int lengthOfLongestSubstring4(String s) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		String current = "";
		String result = "";
		for (int i = 0; i < s.length() - 1 - result.length(); i++) {
			current = String.valueOf(s.charAt(i));
			map.put(s.charAt(i), i);
			for (int j = i + 1; j < s.length(); j++) {
				Integer occurence = map.put(s.charAt(j), j);
				if (occurence == null) {
					current = current + s.charAt(j);
				} else {
					if (result.length() < current.length()) {
						result = current;
					}
					i = occurence + 1;
					break;
				}
			}
			map.clear();
		}

		return result.length();
	}
}
