import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
349. Intersection of Two Arrays
Easy
Topics
Companies
Given two integer arrays nums1 and nums2, return an array of their
intersection
. Each element in the result must be unique and you may return the result in any order.



Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
Explanation: [4,9] is also accepted.


Constraints:

1 <= nums1.length, nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 1000

 */
public class IntersectionOfTwoArrays {

  /*
   * Approach: One Dictionary
   *
   * Time complexity: O(n+m) in the average case where n and m are the arrays' lengths.
   * Space complexity: O(n) because we use a map of size n store the elements from nums1. The result
   * array is just used to store the result, so it is not counted in the space complexity.
   */
  public int[] intersection(int[] nums1, int[] nums2) {
    // Initialize seen map and result list
    Map<Integer, Integer> seen = new HashMap<>();
    List<Integer> result = new ArrayList<>();

    // Mark values occurring in nums1
    for (int x : nums1) {
      seen.put(x, 1);
    }

    // Check if n is in dictionary and not in the result
    for (int x : nums2) {
      if (seen.containsKey(x) && seen.get(x) == 1) {
        result.add(x);
        seen.put(x, 0);
      }
    }

    // Convert to int array and result the result
    return result.stream().mapToInt(i -> i).toArray();
  }
}
