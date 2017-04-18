/*
 * Problem 81: Search in Rotated Sorted Array II
 * 
Follow up for "Search in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Write a function to determine if a given target is in the array.

The array may contain duplicates.
 */
public class SearchInRotatedSortedArray2 {

	public static void main(String[] args) {
		boolean t = search(new int[] {10, 11, 11, 1, 2, 5, 5}, 2);
		System.out.println(t);
	}

	public static boolean search(int[] nums, int target) {
		int left = 0, right = nums.length - 1;
		int pivot = 0;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] < nums[i-1]) {
				pivot = i;
			}
		}
		
		int mid = pivot;
		while (left <= right) {
			if (nums[mid] == target)
				return true;
			if (target > nums[mid]) {
				if (target > nums[right])
					right = mid - 1;
				else
					left = mid + 1;
			} else {
				if (target > nums[right])
					left = mid + 1;
				else
					right = mid - 1;
			}
			mid = (left + right) / 2;
		}
		
		return false;
	}
}
