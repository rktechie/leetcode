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

    // Used Binary Search -
    // Time complexity : O(log2(n)) - We reduce the search space in half at every step.
    //
    // we follow the slopes upward. we may miss some slopes during binary cuts, but there is at least 1
    // that will be encountered since beyond 0 and n - 1 are negative infinities.
    // Eg: nums = 1 2 1 3 5 6 7 , you might be thinking after getting mid it will go right side i.e 3 to 7 but
    // there also a one peak element which is 7>-INF and 7>6 basically
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
