/*
 * Problem 152: Find Minimum in Rotated Sorted Array
 * 
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

You may assume no duplicate exists in the array.
 */

public class FindMinimumInRotatedSortedArray {

    public static void main(String[] args) {

    }

    public int findMin(int[] nums) {
        if (nums.length == 0)
            return 0;
        
        int left = 0, right = nums.length - 1;
        while (left < right && nums[left] > nums[right]) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) // this means that we need to look for the inflection point on the right of mid.
                left = mid + 1;
            else
                right = mid;
        }
        
        return nums[left];
    }
}
