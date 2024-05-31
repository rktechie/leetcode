import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Problem 244 : Shortest Word Distance II
 * 
Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list. Your method will be called repeatedly many times with different parameters. 

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: word1 = “coding”, word2 = “practice”
Output: 3
Input: word1 = "makes", word2 = "coding"
Output: 1
Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */
public class ShortestWordDistance2 {

	/*
	 * (https://leetcode.com/problems/shortest-word-distance-ii/solution/)
	 * 1. Since we process all the words from left to right, we will get all the
	 * indices in a sorted order by default for all the words. So, we don't have
	 * to sort the indices ourselves. 
	 * 
	 * 2. Let's call the dictionary that we build, locations. 
	 * 
	 * 3. For a given pair of words, obtain the list of indices
	 * (appearances inside the original list/array of words). Let's call the two
	 * arrays loc1 and loc2. 
	 * 
	 * 4. Initialize two pointer variables l1 = 0 and l2 = 0.
	 * 
	 * 5. For a given l1 and l2, we first update (if possible) the minimum
	 * difference (distance) till now i.e. dist = min(dist, abs(loc1[l1] -
	 * loc2[l2])). Then, we check if loc1[l1] < loc2[l2] and if this is the
	 * case, we move l1 one step forward i.e. l1 = l1 + 1. Otherwise, we move l2
	 * one step forward i.e. l2 = l2 + 1.
	 */
	class WordDistance {

		HashMap<String, ArrayList<Integer>> locations;

		public WordDistance(String[] words) {
			this.locations = new HashMap<String, ArrayList<Integer>>();

			// Prepare a mapping from a word to all it's locations (indices).
			for (int i = 0; i < words.length; i++) {
				ArrayList<Integer> loc = this.locations.getOrDefault(words[i], new ArrayList<Integer>());
				loc.add(i);
				this.locations.put(words[i], loc);
			}
		}

		public int shortest(String word1, String word2) {
			ArrayList<Integer> loc1, loc2;

			// Location lists for both the words
			// the indices will be in SORTED order by default
			loc1 = this.locations.get(word1);
			loc2 = this.locations.get(word2);

			int l1 = 0, l2 = 0, minDiff = Integer.MAX_VALUE;
			while (l1 < loc1.size() && l2 < loc2.size()) {
				minDiff = Math.min(minDiff, Math.abs(loc1.get(l1) - loc2.get(l2)));
				if (loc1.get(l1) < loc2.get(l2)) {
					l1++;
				} else {
					l2++;
				}
			}

			return minDiff;
		}
	}
}
