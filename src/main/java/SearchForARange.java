/*
 * Problem 34: Search for a Range
 * 
Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].

----------------------------

Algorithm: Use a custom modified version binary search to get the lower bound and upper bound separately
 */

public class SearchForARange {

	public static void main(String[] args) {
		int[] t = searchRange(new int[] {1,2,2,3,4,5,6}, 3);
		for(int x : t)
			System.out.println(x);
	}
	
	// Found the solution online
	public static int[] searchRange(int[] nums, int target) {
		int[] res = { -1, -1 };
		int lower = getLowerBound(nums, target);
		int upper = getUpperBound(nums, target);
		if (lower <= upper) {
			res[0] = lower;
			res[1] = upper;
		}
		return res;
	}
	
	// Note in this we return l
	public static int getLowerBound(int[] A, int target) {
		int l = 0, r = A.length - 1;
		// This while loop exits when the l points to the target if it exists. It exists when r goes lower than l and in that situation l points to the target.
		while (l <= r) {
			int mid = (l + r) / 2;
			if (A[mid] < target)
				l = mid + 1;
			else
				r = mid - 1;
		}
		return l;
	}

	// Note in this we return r
	public static int getUpperBound(int[] A, int target) {
		int l = 0, r = A.length - 1;
		while (l <= r) {
			int mid = (l + r) / 2;
			if (A[mid] <= target) // This is '<=' unlike '<' for lower bound. So because of this l gets incremented one element beyond the target upper bound
				l = mid + 1;
			else
				r = mid - 1; // This is one lower than l, therefore it marks the upper bound of the target
		}
		return r;
	}
}
