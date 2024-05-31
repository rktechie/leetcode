/*
 * Problem 698: Partition to K Equal Sum Subsets
 * 
Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k 
non-empty subsets whose sums are all equal.

Example 1:

Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 

Note:

1 <= k <= len(nums) <= 16.
0 < nums[i] < 10000.
 */
public class PartitionToKEqualSumSubsets {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	/*
	 * Solution 1: DFS
	 * 
	 * Assume sum is the sum of nums[] . 
	 * The dfs process is to find a subset of nums[] whose sum equals to sum/k. 
	 * We use an array visited[] to record which element in nums[] is used. 
	 * Each time when we get a cur_sum = sum/k, we will start from position 0 in nums[] to look up the elements that 
	 * are not used yet and find another cur_sum = sum/k. 
	 * Only if cur_sum = sum/k && cur_num >0, we can start another look up process.
	 */
	public boolean canPartitionKSubsets(int[] nums, int k) {
		int sum = 0;
		for (int num : nums)
			sum += num;
		if (k <= 0 || sum % k != 0) // initial test to check if partitioning into k sets is possible
			return false;
		int[] visited = new int[nums.length];
		return canPartition(nums, visited, 0, k, 0, sum / k); // target=sum/k to create partitions of equal sum
	}

	public boolean canPartition(int[] nums, int[] visited, int start_index, int k, int cur_sum, int target) {
		if (k == 1)
			return true; // base case: in each loop we do k-1, if we reach k=1 then we can successfully divide the array into k subsets 
		
		if (cur_sum == target)
			return canPartition(nums, visited, 0, k - 1, 0, target); // We have a found a set satisfying our sum condition, now lets find other sets from elements which have not been visited from index 0
		
		for (int i = start_index; i < nums.length; i++) {
			if (visited[i] == 0) {
				visited[i] = 1;
				if (canPartition(nums, visited, i + 1, k, cur_sum + nums[i], target))
					return true;
				visited[i] = 0;
			}
		}
		
		return false;
	}
}
