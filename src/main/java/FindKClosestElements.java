import java.util.ArrayList;
import java.util.List;

/*
658. Find K Closest Elements
Medium
Topics
Companies
Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array.
The result should also be sorted in ascending order.

An integer a is closer to x than an integer b if:

|a - x| < |b - x|, or
|a - x| == |b - x| and a < b


Example 1:

Input: arr = [1,2,3,4,5], k = 4, x = 3
Output: [1,2,3,4]
Example 2:

Input: arr = [1,2,3,4,5], k = 4, x = -1
Output: [1,2,3,4]


Constraints:

1 <= k <= arr.length
1 <= arr.length <= 104
arr is sorted in ascending order.
-104 <= arr[i], x <= 104
 */
public class FindKClosestElements {

  /*
   * Approach: Binary Search To Find The Left Bound
   * (Note: As this is a sorted array, we should take advantage of this fact rather than using a
   * minHeap/maxHeap which sorts the array)
   *
   * Let's start by finding the closest number to x in arr. binary search is typically used to find if
   * an element exists or where an element belongs in a sorted array
   * - If the element at arr[mid] is closer to x than arr[mid + k], then that means arr[mid + k], as well
   * as every element to the right of it can never be in the answer. This means we should move our right
   * pointer to avoid considering them. The logic is the same vice-versa - if arr[mid + k] is closer to x,
   * then move the left pointer.
   *
   * Time complexity: O(log(N−k)+k).
   * Although finding the bounds only takes O(log(N−k)) time from the binary search, it still costs
   * us O(k) to build the final output.
   * Both the Java and Python implementations require O(k) time to build the result.
   * However, it is worth noting that if the input array were given as a list instead of an array of integers,
   * then the Java implementation could use the ArrayList.subList() method to build the result in O(1) time.
   * If this were the case, the Java solution would have an (extremely fast) overall time complexity of O(log(N−k)).
   *
   * Space complexity: O(1)
   * We only use integer variables left and right that are O(1) regardless of input size.
   * Space used for the output is not counted towards the space complexity.
   */
  public List<Integer> findClosestElements(int[] arr, int k, int x) {
    // Initialize binary search bounds
    int left = 0;
    int right = arr.length - k;

    // Binary search against the criteria described. We want to expand the window towards the side with the closer number
    while (left < right) {
      int mid = (left + right) / 2;
      if (x - arr[mid] > arr[mid + k] - x) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }

    // Create output in correct format
    List<Integer> result = new ArrayList<>();
    for (int i = left; i < left + k; i++) {
      result.add(arr[i]);
    }

    return result;
  }
}
