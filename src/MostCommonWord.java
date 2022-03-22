import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * Problem 819 : Most Common Word
 * 
Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.  It is guaranteed there is at least one word that isn't banned, and that the answer is unique.

Words in the list of banned words are given in lowercase, and free of punctuation.  Words in the paragraph are not case sensitive.  The answer is in lowercase.

 

Example:

Input: 
paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
banned = ["hit"]
Output: "ball"
Explanation: 
"hit" occurs 3 times, but it is a banned word.
"ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph. 
Note that words in the paragraph are not case sensitive,
that punctuation is ignored (even if adjacent to words, such as "ball,"), 
and that "hit" isn't the answer even though it occurs more because it is banned.
 

Note:

1 <= paragraph.length <= 1000.
0 <= banned.length <= 100.
1 <= banned[i].length <= 10.
The answer is unique, and written in lowercase (even if its occurrences in paragraph may have uppercase symbols, and even if it is a proper noun.)
paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
There are no hyphens or hyphenated words.
Words only consist of letters, never apostrophes or other punctuation symbols.
 */
public class MostCommonWord {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * Time Complexity: O(P + B), where P is the size of paragraph and B is the size of banned.
	 * Space Complexity: O(P + B), to store the count and the banned set.
	 */
	public String mostCommonWord(String paragraph, String[] banned) {
		Set<String> set = new HashSet<>(Arrays.asList(banned));

		Map<String, Integer> map = new HashMap<>();
		String ans = "";
		int count = 0; // keep the max count
		for (String s : paragraph.replaceAll("\\W+", " ").toLowerCase().split("\\s+")) {
			s = s.trim();
			if (s.length() == 0 || set.contains(s))
				continue;
			map.put(s, map.getOrDefault(s, 0) + 1);
			if (count < map.get(s)) {
				count = map.get(s);
				ans = s;
			}
		}

		return ans;
	}

}
