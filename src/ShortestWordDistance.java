/*
 * Problem 243: Shortest Word Distance
 * 
Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: word1 = “coding”, word2 = “practice”
Output: 3
Input: word1 = "makes", word2 = "coding"
Output: 1
Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */
public class ShortestWordDistance {

	/*
	 * Keeping two indices i1 and i2 where we store the most recent locations of word1 and word2. 
	 * Each time we find a new occurrence of one of the words, we do not need to search the 
	 * entire array for the other word, since we already have the index of its most recent occurrence.
	 */
	public int shortestDistance(String[] words, String word1, String word2) {
		int i1 = -1, i2 = -1;
	    int minDistance = words.length;
	    for (int i = 0; i < words.length; i++) {
	        if (words[i].equals(word1)) {
	            i1 = i;
	        } else if (words[i].equals(word2)) {
	            i2 = i;
	        }

	        if (i1 != -1 && i2 != -1) {
	            minDistance = Math.min(minDistance, Math.abs(i1 - i2));
	        }
	    }
	    
	    return minDistance;
    }
}
