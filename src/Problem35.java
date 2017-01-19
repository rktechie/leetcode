
public class Problem35 {

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
