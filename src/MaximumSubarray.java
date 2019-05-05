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

}
