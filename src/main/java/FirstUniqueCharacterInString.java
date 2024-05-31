/*
 * Problem : First Unique Character in a String
 * 
Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode",
return 2.
Note: You may assume the string contain only lowercase letters.
 */
public class FirstUniqueCharacterInString {

	/*
	 * Keep a count array of size either 26 or 256.
	 * 
	 * We keep a size of 256 to make it generic to solve problems which can have 
	 * any type of characters which are represented by ASCII
	 */
	public int firstUniqChar(String s) {
		int freq[] = new int[256];
		for (int i = 0; i < s.length(); i++)
			freq[s.charAt(i)]++;
		
		for (int i = 0; i < s.length(); i++)
			if (freq[s.charAt(i)] == 1)
				return i;
		
		return -1;
	}
}
