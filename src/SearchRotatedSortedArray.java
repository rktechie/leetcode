/*
 * Problem 33: Search in Rotated Sorted Array
 * 
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

-------------------------------------------
Algorithm : It is an application of binary search.
			   1. We first find the point where the pivot was made
			   2. Now the major point to note is that we cannot follow the binary search directly. Because there might be cases where the array after pivoting becomes
			      in a descending order like {5,4,3,2,1}.
			   3. So we get the pivoted nums array. That array can be of 3 types:
			   		a. Random order : {5,4,1,2,3}
			   		b. Increasing order : {1,2,3,4,5}
			   		c. Decreasing order : {5,4,3,2,1}
 */
public class SearchRotatedSortedArray {

	public static void main(String[] args) {
		int[] nums = {3,1};
		int t = search(nums, 3);
		System.out.println(t);
	}

	public static int search(int[] nums, int target) {
		int low = 0, mid = 0, high = nums.length - 1;
		int pivotIndex = 0;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] < nums[i-1]) {
				pivotIndex = i;
				break;
			}
		}
		
		mid = pivotIndex;
		while (low <= high) {
			if(target == nums[mid]) {
				return mid;
			} else if (target > nums[mid]) {
				// Target is greater than the mid, but which sub array has the higher values..is it right sub array or left sub array.
				if (target > nums[high]) { // if the sub array is of decreasing order i.e. 5,4,3,2,1 then we need to shift the high
					high = mid - 1;
				} else {	// if the sub array is of increasing order i.e. 1,2,3,4,5 then we need to shift the low (i.e. binary search)
					low = mid + 1;	
				}
			} else {
				// Target is less than the mid, but which sub array has the lower values..is it right sub array or left sub array.
				if (target > nums[high]) {
					low = mid + 1;
				} else {
					high = mid - 1;	
				}
			}
			mid = low + (high - low ) / 2;
		}
		return -1;
	}
}
