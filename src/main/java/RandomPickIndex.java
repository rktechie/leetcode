import java.util.Random;

/*
398. Random Pick Index
Medium
Topics
Companies
Given an integer array nums with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.

Implement the Solution class:

Solution(int[] nums) Initializes the object with the array nums.
int pick(int target) Picks a random index i from nums where nums[i] == target. If there are multiple valid i's, then each index should have an equal probability of returning.


Example 1:

Input
["Solution", "pick", "pick", "pick"]
[[[1, 2, 3, 3, 3]], [3], [1], [3]]
Output
[null, 4, 0, 2]

Explanation
Solution solution = new Solution([1, 2, 3, 3, 3]);
solution.pick(3); // It should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(1); // It should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(3); // It should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.


Constraints:

1 <= nums.length <= 2 * 104
-231 <= nums[i] <= 231 - 1
target is an integer from nums.
At most 104 calls will be made to pick.
 */
public class RandomPickIndex {

  /*
   * Approach: Reservoir sampling
   *
   * Consider the example in the OJ
   * {1,2,3,3,3} with target 3, you want to select 2,3,4 with a probability of 1/3 each.
   * 2 : It's probability of selection is 1 * (1/2) * (2/3) = 1/3
   * 3 : It's probability of selection is (1/2) * (2/3) = 1/3
   * 4 : It's probability of selection is just 1/3
   * So they are each randomly selected.
   *
   * Say we now we have nums=[1,5,5,6,5,7,9,0] and the target is 5.
   * 1. Now let's focus on the loop. When i=1, we get the first target number, and by rnd.nextInt(++count) we
   * select a random number between [0, 1), which means actually you could only select 0, so the
   * probability of making result = 1 is 1.
   * 2. Keep going. In the loop where i = 2, we get the second number. Now we have to get a random number in {0,1}.
   * So what should we do if we want to keep result = 1? It's simple: we have to make sure that, at this time,
   * the random number generated should be 1 rather than 0 (otherwise the value of result will be changed),
   * so the probability of keeping result = 1 is 1 * 1/2
   * 3. It is similar when we get the third target number, i.e., i = 4. Now we have to get a random
   * number in {0,1,2}. If we still wish to keep result = 1, the only way is to randomly get number 1
   * or 2 rather than 0, and the probability is 2/3. So the total probability of keeping result = 1 will be 1 * 1/2 * 2/3 .
   *
   */
  public class Solution {

    int[] nums;
    Random rnd;

    public Solution(int[] nums) {
      this.nums = nums;
      this.rnd = new Random();
    }

    public int pick(int target) {
      int result = -1; // represents the index we have picked
      int count = 0; // count is used to count the target number in nums
      for (int i = 0; i < nums.length; i++) {
        if (nums[i] != target)
          continue;
        if (rnd.nextInt(++count) == 0)
          result = i;
      }

      return result;
    }
  }
}
