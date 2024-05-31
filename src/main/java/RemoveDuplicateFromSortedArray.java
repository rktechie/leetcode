/*
 * Problem 26: Remove Duplicates from Sorted Array
 * 
Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the new length.
 */

public class RemoveDuplicateFromSortedArray {

	public static void main(String[] args) {
		int nums[] = {1,1,2,3};
		int t = removeDuplicates(nums);
		System.out.println(t);
	}

	public static int removeDuplicates(int[] nums) {
		// Note it is a SORTED array. 
		int length = 0;
		if (nums.length <=1) {
			return nums.length;
		}
		int j = 0;
		for (int i = 0; i < nums.length; i++) {
			if (i == 0 || nums[i-1] != nums[i]) {	// We just have to check for uniqueness of each element with the previous element as its a sorted array. 0th element is unique 
				nums[j++] = nums[i];	// This is so that the array contains only unique elements
				length++;
			}
		}
		
		return length;
	}
}
