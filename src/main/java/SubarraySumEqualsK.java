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
	 * If the cumulative sum upto two indices, say i and j is at a difference of k i.e. 
	 * if sum[i] - sum[j] = k, the sum of elements lying between indices i and j is k.
	 * 
	 * Based on these thoughts, we make use of a hashmap which is used to store the cumulative sum upto all the 
	 * indices possible along with the number of times the same sum occurs.
	 * 
	 * 
	 * -We traverse over the array nums and keep on finding the cumulative sum. 
	 * -Every time we encounter a new sum, we make a new entry in the hashmap corresponding to that sum. 
	 * -If the same sum occurs again, we increment the count corresponding to that sum in the hashmap. 
	 * -Further, for every sum encountered, we also determine the number of times the sum: sum-k has occurred already, 
	 * since it will determine the number of times a subarray with sum k has occured upto the current index. 
	 * -We increment the result by the same amount.
	 * -After the complete array has been traversed, the result gives the required result.
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
        Map<Integer, Integer> map = new HashMap<>(); // key: preSum, value: frequency
        // it is for those (sum - k) == 0 calculations which are valid subarrays but need to get counted. 
        // e.g. if k = 7 and sum = 7 (at second element for array is : 3, 4, 3, 8) at some iteration.....then sum - k = 0....this 0 will get counted in statement result += preSum.get(sum - k);
        map.put(0, 1); 
        
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            result += map.getOrDefault(sum - k, 0); // at each sum if a presum exists then there is a subarray between this sum and presum. So we want to use its frequency instead of just adding 1 to result because there can exit multiple presum at this point of sum.
            map.put(sum, map.getOrDefault(sum, 0) + 1); // to note the total number of times we got the sum because this means that many combinations exist to get the desired sum
        }
        
        return result;
    }
}
