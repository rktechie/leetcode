
public class Problem26 {

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
