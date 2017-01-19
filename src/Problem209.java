/*
 * Problem 209: Minimum Size Subarray Sum
 * 
Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.

click to show more practice.
More practice:

If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).

 */
public class Problem209 {

    /* Solution: Sliding Window O(n)
     * 
     * We will maintain a window that grows until "sum" reaches the given sum i.e. "s".
     * Once the window grows to sum at least s then we can start shirking the window from left with the hope to find a smaller window. 
     * We shrink until sum falls below s.
     * Then we can grow the window on right again and so on.
     * We keep this procedure of growing-shrinking until the window end reaches the end of the array.
     */
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int start = 0, end = 0, sum = 0, min = Integer.MAX_VALUE;

        while (end < nums.length) {
            sum += nums[end];
            end++;
            
            while (sum >= s) {
                min = Math.min(min, end - start);
                sum -= nums[start];
                start++;
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
