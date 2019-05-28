import java.util.HashMap;
import java.util.Map;

/*
 * Problem : Subarray Sum Equals K
 * 
Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals 
to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2
Note:
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].

 */
public class SubarraySumEqualsK {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	/*
	 * Solution:
	 * sum(i,j)=sum(0,j)-sum(0,i), where sum(i,j) represents the sum of all the elements from index i to j-1. 
	 * 
	 * We just need to go through the array, calculate the current sum and save number of all seen PreSum to a HashMap. 
	 * 
	 * There is a special case when nums[x] == k that is current sum itself is equal to target without any subtractions. 
	 * For this solution, we can either increment count by 1 whenever sum == k below or 
	 * make an entry as a special case in our map preSumFreq.put(0, 1) to cover those cases.
	 * 
	 * Time complexity O(n), Space complexity O(n)
	 * 
	 * We do -> result += preSum.getOrDefault(sum - k, 0);
	 * because we want to capture all the combinations that exist for sum - k, and then add it to the total result
	 */
	public int subarraySum(int[] nums, int k) {
        int sum = 0, result = 0; // sum is to keep track of sum of all the elements so far
        Map<Integer, Integer> preSum = new HashMap<>(); // key: preSum, value: frequency
        // it is for those (sum - k) == 0 calculations which are valid subarrays but need to get counted. 
        // e.g. if k = 7 and sum = 7 (at second element for array is : 3, 4, 3, 8) at some iteration.....then sum - k = 0....this 0 will get counted in statement result += preSum.get(sum - k);
        preSum.put(0, 1); 
        
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            result += preSum.getOrDefault(sum - k, 0); // at each sum if there is a presum exists then there is a subarray between this sum and presum. So we want to use its frequency instead of just adding 1 to result because there can exit multiple presum at this point of sum.
            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1); // to note the total number of times we got the sum because this mean that many combinations exist to get the desired sum
        }
        
        return result;
    }
}
