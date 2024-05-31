/*
Problem 45. Jump Game II
Medium
Topics
Companies
You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].

Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at nums[i], you can jump to any nums[i + j] where:

0 <= j <= nums[i] and
i + j < n
Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].



Example 1:

Input: nums = [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [2,3,0,1,4]
Output: 2


Constraints:

1 <= nums.length <= 104
0 <= nums[i] <= 1000
It's guaranteed that you can reach nums[n - 1].
 */

public class JumpGame2 {

  /**
   * Approach: Greedy (BFS)
   * The current jump ends when we reach index end.
   * Between the current index and end, we find the farthest reachable index far.
   * At the end of the current jump, we increment our answer and set end = far for the next jump.
   *
   * end is the furthest starting index of the current jump.
   * far is the furthest reachable index of the current jump.
   *
   *
   * You can think of the solution in terms of a sliding window or jumps.
   *
   * Time complexity: O(n)
   */
  public int jump(int[] nums) {
    // The starting range of the first jump is [0, 0]
    int answer = 0;
    int curEnd = 0, curFar = 0;

    for (int i = 0; i < nums.length - 1; ++i) {
      // Update the farthest reachable index of this jump.
      curFar = Math.max(curFar, i + nums[i]);

      // If we finish the starting range of this jump,
      // Move on to the starting range of the next jump.
      if (i == curEnd) {
        answer++;
        curEnd = curFar;
      }
    }

    return answer;
  }

}
