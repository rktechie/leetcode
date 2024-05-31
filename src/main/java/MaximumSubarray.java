/*
 * Problem 53: Maximum Subarray

Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [−2,1,−3,4,−1,2,1,−5,4], the contiguous subarray [4,−1,2,1] has the largest sum = 6. 
 * 
 */

public class MaximumSubarray {

	public static void main(String[] args) {
		int t = maxSubArray(new int[] { -1, -2 });
		System.out.println(t);
	}

	/*
	 * Solution 1: Pick the locally optimal move at each step, and that will lead to the globally optimal solution.
	 *  
	 *  It starts at the left end (element A[1]) and scans through to the right end (element A[n]), 
	 *  keeping track of the maximum sum subvector seen so far. 
	 *  
	 *  The maximum is initially A[0]. Suppose we've solved the problem for A[1 .. i - 1]; 
	 *  how can we extend that to A[1 .. i]? 
	 *  
	 *  The maximum sum in the first I elements is either the maximum sum in the first i - 1 elements 
	 *  (which we'll call MaxSoFar), or it is that of a subvector that ends in position i 
	 *  (which we'll call MaxEndingHere).
	 *  
	 *  MaxEndingHere is either A[i] plus the previous MaxEndingHere, or just A[i], whichever is larger.
	 *  
	 *  Time complexity : O(N) since it's one pass along the array.
	 *  Space complexity : O(1), since it's a constant space solution.
	 */
	public static int maxSubArray(int[] nums) {
		if (nums.length == 0)
			return 0;
		int dp = nums[0], res = nums[0];
		for (int i = 1; i < nums.length; ++i) {
			dp = Math.max(nums[i], dp + nums[i]); //maxEndingHere
			res = Math.max(res, dp); //maxSoFar
		}
		return res;
	}

	/*
	 * Solution 2: Dynamic Programming (Kadane's algorithm)
	 * 
	 * The problem to find sum or maximum or minimum in an entire array or in a fixed-size sliding window 
	 * could be solved by the dynamic programming (DP) approach in linear time.
	 * 
	 * The approach is a constant space one. Move along the array and modify the array itself to track 
	 * the current local maximum sum at this given point.
	 * Next step is to update the global maximum sum, knowing the local one.
	 * 
	 * Time complexity : O(N) since it's one pass along the array.
	 * Space complexity : O(1), since it's a constant space solution.
	 */
	public int maxSubArray2(int[] nums) {
	    int n = nums.length, maxSum = nums[0];
	    for(int i = 1; i < n; ++i) {
	      if (nums[i - 1] > 0) nums[i] += nums[i - 1];
	      maxSum = Math.max(nums[i], maxSum);
	    }
	    return maxSum;
	  }
}
