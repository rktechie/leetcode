
/*
 * Problem 49: Group Anagrams
 * 
Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
Return:

[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note: All inputs will be in lower-case.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {

	public static void main(String[] args) {
		String[] strs = { "", "" };
		List<List<String>> t = groupAnagrams(strs);
	}

	/*
	 * Approach 1: Categorize by Sorted String
	 * Algorithm: Sort each word. So if two words are anagrams then after sorting
	 * each word will have the same sequence. By this we will get all anagrams and
	 * add them to a list.
	 *
	 * Time Complexity: O(NKlogK), where N is the length of strs, and K is the maximum length of a
	 * string in strs. The outer loop has complexity O(N) as we iterate through each string.
	 * Then, we sort each string in O(KlogK) time.
	 *
	 * Space Complexity: O(NK), the total information content stored in ans.
	 */
	public static List<List<String>> groupAnagrams(String[] strs) {
		if (strs == null || strs.length == 0)
			return new ArrayList<List<String>>();
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		for (String s : strs) {
			char[] ca = s.toCharArray();
			Arrays.sort(ca);
			String keyStr = String.valueOf(ca);
			if (!map.containsKey(keyStr))
				map.put(keyStr, new ArrayList<String>());
			map.get(keyStr).add(s);
		}
		return new ArrayList<List<String>>(map.values());
	}

	/*
	 * Approach 2: Categorize by Count
	 * We can transform each string "s" into a character count "count", consisting of 26 non-negative
	 * integers representing the number of a's, b's, c's, etc. We use these counts as the basis for our hash map.
	 *
	 * In Java, the hashable representation of our count will be a string delimited with '#' characters.
	 *
	 * Time Complexity: O(NK), where N is the length of strs, and K is the maximum length of a string
	 * in strs. Counting each string is linear in the size of the string, and we count every string.
	 *
	 * Space Complexity: O(NK), the total information content stored in ans.
	 */
	public List<List<String>> groupAnagrams2(String[] strs) {
		if (strs.length == 0) return new ArrayList();
		Map<String, List> ans = new HashMap<String, List>();
		int[] count = new int[26];
		for (String s : strs) {
			Arrays.fill(count, 0);
			for (char c : s.toCharArray())
				count[c - 'a']++;

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 26; i++) {
				sb.append('#');
				sb.append(count[i]);
			}
			String key = sb.toString();
			if (!ans.containsKey(key))
				ans.put(key, new ArrayList<String>());
			ans.get(key).add(s);
		}
		return new ArrayList(ans.values());
	}
}
