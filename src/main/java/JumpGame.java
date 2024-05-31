/*
You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.

Return true if you can reach the last index, or false otherwise.



Example 1:

Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.


Constraints:

1 <= nums.length <= 104
0 <= nums[i] <= 105
 */

public class JumpGame {

  /* Greedy approach
  Idea is to work backwards from the last index.
  Keep track of the smallest index that can "jump" to the last index.
  Check whether the current index can jump to this smallest index.
   */
  public boolean canJump(int[] nums) {
    int lastPos = nums.length - 1;
    for (int i = nums.length - 1; i >= 0; i--) {
      if (i + nums[i] >= lastPos) { // (i + nums[i]) is the max reachable position
        lastPos = i;
      }
    }
    return lastPos == 0;
  }

  /* Greedy approach
  One possible improvement to above soln is that your loops runs for n times no matter what.
  This might not be necessary if we compute from start to end. The idea is: whenever we realize that
  we cannot reach a point i, return false
   */
  public boolean canJump2(int[] nums) {
    int reachable = 0;
    for (int i = 0; i < nums.length; i ++) {
      if (i > reachable)
        return false;
      reachable = Math.max(reachable, i + nums[i]);
    }

    return true;
  }

}
