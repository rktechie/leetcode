import java.util.ArrayList;
import java.util.List;

/*
1570. Dot Product of Two Sparse Vectors
Medium
Topics
Companies
Hint
Given two sparse vectors, compute their dot product.

Implement class SparseVector:

SparseVector(nums) Initializes the object with the vector nums
dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently and compute the dot product between two SparseVector.

Follow up: What if only one of the vectors is sparse?



Example 1:

Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
Output: 8
Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8
Example 2:

Input: nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
Output: 0
Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0
Example 3:

Input: nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
Output: 6


Constraints:

n == nums1.length == nums2.length
1 <= n <= 10^5
0 <= nums1[i], nums2[i] <= 100
 */
public class DotProductOfTwoSparseVectors {

  /*
   * Approach: Index-Value Pairs
   *
   * We can also represent elements of a sparse vector as a list of <index, value> pairs.
   * We use two pointers to iterate through the two vectors to calculate the dot product.
   *
   * BUT if asked the follow up question - What if only one vector is sparse and the other is full of non-zero values?
   * then we dont use the 2 pointers but instead call binary search to find the matching index.
   * I have also implemented dotProductFollowUp method to make it easier to understand how to modify the function
   *
   * Time complexity: O(n) for creating the <index, value> pair for non-zero values; O(L+L2) for calculating the dot product
   * where Let n be the length of the input array and L and L2 be the number of non-zero elements for the two vectors.
   */
  class SparseVector {

    List<int[]> pairs;

    SparseVector(int[] nums) {
      pairs = new ArrayList<>();
      for (int i = 0; i < nums.length; i++) {
        if (nums[i] != 0) {
          pairs.add(new int[]{i, nums[i]});
        }
      }
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
      int result = 0, p = 0, q = 0;
      while (p < pairs.size() && q < vec.pairs.size()) {
        if (pairs.get(p)[0] == vec.pairs.get(q)[0]) {
          result += pairs.get(p)[1] * vec.pairs.get(q)[1];
          p++;
          q++;
        }
        else if (pairs.get(p)[0] > vec.pairs.get(q)[0]) {
          q++;
        }
        else {
          p++;
        }
      }
      return result;
    }

    // If asked follow up question - What if only one vector is sparse and the other is full of non-zero values?
    // then you have to use binary search to find the matching index
    public int dotProductFollowUp(SparseVector vec) {
      int result = 0;
      for (int[] pair : pairs) {
        int val = binarySearch(vec.pairs, pair[0]);
        if (val != Integer.MAX_VALUE) {
          result += val * pair[1];
        }
      }

      return result;
    }

    public int binarySearch(List<int[]> vecPairs, int targetIdx) {
      int start = 0;
      int end = vecPairs.size() - 1;

      while (start <= end) {
        int mid = (start + end) / 2;
        if (vecPairs.get(mid)[0] == targetIdx){
          return vecPairs.get(mid)[1];
        } else if (vecPairs.get(mid)[0] > targetIdx) {
          end = mid - 1;
        } else {
          start = mid + 1;
        }
      }

      return Integer.MAX_VALUE;
    }
  }

}
