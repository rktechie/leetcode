/*
 * Problem 162: Find Peak Element
 * 
A peak element is an element that is greater than its neighbors.

Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that num[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.

click to show spoilers.
Note:

Your solution should be in logarithmic complexity.

 */
public class FindPeakElement {

    public static void main(String args[]) {
        int x = findPeakElement(new int[] {1});
        System.out.println(x);
    }

    // Used Binary Search - Time complexity : O(log2(n))
    public static int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1, mid = 0;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (mid > 0 && nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1])
                return mid;
            else if (nums[mid] < nums[mid + 1])
                left = mid + 1;
            else
                right = mid - 1;
        }

        return left;
    }
}
