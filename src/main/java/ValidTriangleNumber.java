import java.util.Arrays;

/*
611. Valid Triangle Number
Medium
Topics
Companies
Given an integer array nums, return the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.



Example 1:

Input: nums = [2,2,3,4]
Output: 3
Explanation: Valid combinations are:
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3
Example 2:

Input: nums = [4,2,3,4]
Output: 4


Constraints:

1 <= nums.length <= 1000
0 <= nums[i] <= 1000
 */
public class ValidTriangleNumber {

  /*
   * Approach 1: Linear Scan
   *
   * once we sort the given nums array, we need to find the right limit of the index k for a pair of
   * indices (i,j) chosen to find the count of elements satisfying nums[i]+nums[j]>nums[k] for the
   * triplet (nums[i],nums[j],nums[k]) to form a valid triangle.
   *
   * We can find this right limit by simply traversing the index k's values starting from the index
   * k=j+1 for a pair (i,j) chosen and stopping at the first value of k not satisfying the above inequality.
   * Again, the count of elements nums[k] satisfying nums[i]+nums[j]>nums[k] for the pair of indices (i,j)
   * chosen is given by k−j−1
   *
   * Further, when we choose a higher value of index j for a particular i chosen, we need not start from
   * the index j+1. Instead, we can start off directly from the value of k where we left for the last index j.
   * This helps to save redundant computations.
   *
   * Time complexity : O(n^2). Loop of k and j will be executed O(n^2) times in total, because, we do not
   * reinitialize the value of k for a new value of j chosen(for the same i).
   * Thus the complexity will be O(n*log(n) + n^2)=O(n^2)
   *
   * Space complexity : O(1)
   */
  public int triangleNumber(int[] nums) {
    int count = 0;
    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 2; i++) {
      int k = i + 2;
      for (int j = i + 1; j < nums.length - 1 && nums[i] != 0; j++) {
        while (k < nums.length && nums[i] + nums[j] > nums[k]) {
          k++;
        }
        count += k - j - 1;
      }
    }
    return count;
  }

  /*
   * Approach 2: This problem is very similar to 3-Sum
   *
   * In 3-Sum, we can use three pointers (i, j, k and i < j < k) to solve the problem in O(n^2) time for
   * a sorted array, the way we do in 3-Sum is that we first lock pointer i and then scan j and k, if
   * nums[j] + nums[k] is too large, k--, otherwise j++, once we complete the scan, increase pointer i and repeat.
   *
   * For this problem, once we sort the input array nums, the key to solve the problem is that given
   * nums[k], count the combination of i and j where nums[i] + nums[j] > nums[k] (so that they can form a triangle).
   *
   * If nums[i] + nums[j] is larger than nums[k], we know that there will be j - i combination.
   *
   * Notice how we start at the largest value and move forward. This is because with the triangle inequality
   * rule (two sides must be greater than the third side), using the largest value implicitly implies that it + the
   * next greatest value (the nums[j] value in op's post) is guaranteed to be greater than the third
   * value (nums[i] in op's post).
   *
   * Note that if we start from the smallest value, while we make a check of nums[i] + nums[j] > nums[k],
   * we would have no guarantee that nums[i] + nums[k] > nums[j].
   *
   * Time complexity : O(n^2)
   * Space complexity : O(1)
   */
  public int triangleNumber2(int[] nums) {
    int count = 0;
    Arrays.sort(nums);
    for (int k = nums.length - 1; k > 1; k--) {
      int i = 0;
      int j = k - 1;
      while (i < j) {
        if (nums[i] + nums[j] > nums[k]) {
          count += j - i;
          j--;
        } else {
          i++;
        }
      }
    }
    return count;
  }
}
