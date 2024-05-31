import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * Problem 128: Longest Consecutive Sequence
 * 
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Your algorithm should run in O(n) complexity.

Example:

Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 */
public class LongestConsecutiveSequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	// READ BOTH THE SOLUTIONS

	/*
	 * Solution 1: HashSet and Intelligent Sequence Building
	 * The numbers are stored in a HashSet (or Set, in Python) to allow O(1) lookups, and we only attempt to build sequences 
	 * from numbers that are not already part of a longer sequence. 
	 * This is accomplished by first ensuring that the number that would immediately precede the current number in a sequence 
	 * is not present, as that number would necessarily be part of a longer sequence.
	 * 
	 * Time complexity : O(n)
	 * Although the time complexity appears to be quadratic due to the while loop nested within the for loop, closer inspection 
	 * reveals it to be linear. Because the while loop is reached only when currentNum marks the beginning of a sequence 
	 * (i.e. currentNum-1 is not present in nums), the while loop can only run for n iterations throughout the entire runtime 
	 * of the algorithm. This means that despite looking like O(n⋅n) complexity, the nested loops actually run in 
	 * O(n + n) = O(n) time. All other computations occur in constant time, so the overall runtime is linear.
	 * 
	 * Space complexity : O(n)
	 * In order to set up O(1) containment lookups, we allocate linear space for a hash table to store the O(n) numbers in nums
	 */
	public int longestConsecutive(int[] nums) {
		Set<Integer> num_set = new HashSet<Integer>();
		for (int num : nums) {
			num_set.add(num);
		}

		int longestStreak = 0;

		for (int num : num_set) {
			if (!num_set.contains(num - 1)) { // important check because if a smaller num exists, then the current num has already been included in the streak calculation  
				int currentNum = num;
				int currentStreak = 1;
				while (num_set.contains(currentNum + 1)) {
					currentNum += 1;
					currentStreak += 1;
				}
				longestStreak = Math.max(longestStreak, currentStreak);
			}
		}

		return longestStreak;
	}
	
	/*
	 * Solution 2: HashMap
	 * The key thing is to keep track of the sequence length and store that in the boundary points of the sequence. 
	 * For example, as a result, for sequence {1, 2, 3, 4, 5}, map.get(1) and map.get(5) should both return 5.
	 * 
	 * Whenever a new element n is inserted into the map, do two things:
	 * 1. See if n - 1 and n + 1 exist in the map, and if so, it means there is an existing sequence next to n. 
	 * Variables left and right will be the length of those two sequences, while 0 means there is no sequence and n will be 
	 * the boundary point later. Store (left + right + 1) as the associated value to key n into the map.
	 * 
	 * 2. Use left and right to locate the other end of the sequences to the left and right of n respectively, and replace 
	 * the value with the new length.
	 * 
	 * Time Complexity: Everything inside the for loop is O(1) so the total time is O(n).
	 */
	public int longestConsecutive2(int[] nums) {
		Map<Integer, Integer> ranges = new HashMap<>();
		int max = 0;
		for (int num : nums) {
			if (ranges.containsKey(num)) // to prevent duplication
				continue;

			// 1.Find left and right num
			int left = ranges.getOrDefault(num - 1, 0);
			int right = ranges.getOrDefault(num + 1, 0);
			int sum = left + right + 1;
			max = Math.max(max, sum);

			// 2.Union by only updating boundary
			if (left > 0)
				ranges.put(num - left, sum);
			if (right > 0)
				ranges.put(num + right, sum);
			
			ranges.put(num, sum);
		}
		
		return max;
	}
}
