/*
410. Split Array Largest Sum
Hard
Topics
Companies
Given an integer array nums and an integer k, split nums into k non-empty subarrays such that the largest sum of any subarray is minimized.

Return the minimized largest sum of the split.

A subarray is a contiguous part of the array.



Example 1:

Input: nums = [7,2,5,10,8], k = 2
Output: 18
Explanation: There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8], where the largest sum among the two subarrays is only 18.
Example 2:

Input: nums = [1,2,3,4,5], k = 2
Output: 9
Explanation: There are four ways to split nums into two subarrays.
The best way is to split it into [1,2,3] and [4,5], where the largest sum among the two subarrays is only 9.


Constraints:

1 <= nums.length <= 1000
0 <= nums[i] <= 106
1 <= k <= min(50, nums.length)
 */
public class SplitArrayLargestSum {

  /*
   * Approach: Binary Search
   *
   * The goal of this problem is to find the minimum largest subarray sum with m subarrays.
   * Given an array of n integers and a value X, determine the minimum number of subarrays the array needs
   * to be divided into such that no subarray sum is greater than X.
   *
   * The solution to this newly defined problem is as follows:
   * 1. First, make sure X is greater than or equal to the maximum element in the array. Otherwise, no
   * solution would be possible because we cannot divide an element.
   * 2. Start from 0th index and keep adding elements to sum only if adding the element does not make sum greater than X.
   * 3. If adding the current element would make the sum greater than X then we have to split the subarray here.
   * So we will increment the number of splits required (splitsRequired) and set sum to the value of current
   * element (signifying that the current subarray only contains the current element).
   * 4. Once we traversed the whole array, return splitsRequired + 1. This is the minimum number of subarrays required.
   *
   * Now the problem with the current solution is that the value of X can be as large as the sum of
   * integers in the given array. Hence, checking for all values of X is not feasible.
   * Therefore, instead of searching linearly for X, we can do a binary search! If we can split the array
   * into m or fewer subarrays all with a sum that is less than or equal to X then we will try a smaller
   * value for X otherwise we will try a larger value for X. Each time we will select X so that we reduce
   * the size of the search space by half.
   *
   * Here N is the length of the array and S is the sum of integers in the array.
   * Time complexity: O(N*log(S))
   * The total number of iterations in the binary search is log(S), and for each such iteration, we call
   * minimumSubarraysRequired which takes O(N) time. Hence, the time complexity is equal to O(N*log(S)).
   *
   * Space complexity: O(1)
   * We do not use any data structures that require more than constant extra space.
   */
  public int splitArray(int[] nums, int m) {
    // Find the sum of all elements and the maximum element
    int sum = 0;
    int maxElement = Integer.MIN_VALUE;
    for (int element : nums) {
      sum += element;
      maxElement = Math.max(maxElement, element);
    }

    // Define the left and right boundary of binary search
    int left = maxElement;
    int right = sum;
    int minimumLargestSplitSum = 0;
    while (left <= right) {
      // Find the mid value
      int maxSumAllowed = left + (right - left) / 2;

      // Find the minimum splits. If splitsRequired is less than
      // or equal to m move towards left i.e., smaller values
      if (minimumSubarraysRequired(nums, maxSumAllowed) <= m) {
        right = maxSumAllowed - 1;
        minimumLargestSplitSum = maxSumAllowed;
      } else {
        // Move towards right if splitsRequired is more than m
        left = maxSumAllowed + 1;
      }
    }

    return minimumLargestSplitSum;
  }

  private int minimumSubarraysRequired(int[] nums, int maxSumAllowed) {
    int currentSum = 0;
    int splitsRequired = 0;

    for (int element : nums) {
      // Add element only if the sum doesn't exceed maxSumAllowed
      if (currentSum + element <= maxSumAllowed) {
        currentSum += element;
      } else {
        // If the element addition makes sum more than maxSumAllowed
        // Increment the splits required and reset sum
        currentSum = element;
        splitsRequired++;
      }
    }

    // Return the number of subarrays, which is the number of splits + 1
    return splitsRequired + 1;
  }
}
