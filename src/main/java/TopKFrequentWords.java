import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/*
 * Problem 692 : Top K Frequent Words
 * 
Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.

Example 1:
Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.
Example 2:
Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
Output: ["the", "is", "sunny", "day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
    with the number of occurrence being 4, 3, 2 and 1 respectively.
Note:
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Input words contain only lowercase letters.
Follow up:
Try to solve it in O(n log k) time and O(n) extra space.
 */
public class TopKFrequentWords {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	/*
	 * Time Complexity: O(Nlogk), where N is the length of words. 
	 * We count the frequency of each word in O(N) time, then we add N words to the heap, each in O(logk) time.
	 * Finally, we pop from the heap up to k times. As k ≤ N, this is O(Nlogk) in total.
	 * 
	 * Space Complexity: O(N), the space used to store our map.
	 */
	public List<String> topKFrequent(String[] words, int k) {
		List<String> result = new LinkedList<>();
		HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
        	map.put(word, map.getOrDefault(word, 0) + 1);
        }
        
        PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<>(
        		(a,b) -> a.getValue() == b.getValue() ? b.getKey().compareTo(a.getKey()) : a.getValue()-b.getValue()
        );
        
        for (Map.Entry e : map.entrySet()) {
        	queue.add(e);
        	if (queue.size() > k)
        		queue.poll();
        }
        
        while(!queue.isEmpty())
            result.add(0, queue.poll().getKey());
        
        return result;
    }
}
