/*
 * Problem 35: Search Insert Position
 * 
Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0

 */
public class SearchInsertPosition {

	public static void main(String[] args) {
		int t = searchInsert(new int[] {1,3,5}, 2);
		System.out.println(t);
	}
	
	// It is similar to binary search. Return low if the element is not found.
	public static int searchInsert(int[] nums, int target) {
		int low = 0, mid = 0, high = nums.length - 1;
		
		if (target > nums[high]) {
			return high + 1;
		} else if (target < nums[low]) {
			return 0;
		}
		while (low <= high) {
			mid = low + (high - low) / 2;
			if (target == nums[mid]) {
				return mid;
			} else if (target > nums[mid]) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		
		return low;
	}
}
