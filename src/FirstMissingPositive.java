/* 
 * Problem 41: First Missing Positive
 * 
Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.

------------------------------------
Algorithm: nums[i] should be equal to i + 1. If not then swap. 
           So finally we return the value which does not satisfy this condition.
           
if every number is in its place only
nums[i] = i+1
index 0 1 2 3
num[] 1 2 3 4
return 5;

index 0 1 2 3
nums[] 3 -1 1 4
i =0,
nums[i]-1 = 2 , nums[i]-1 !=
After swap
index 0 1 2 3
nums[] 1 -1 3 4
Scan every number to see if they match their index
nums[i] != i+1
so missing positive is 2;
 */
public class FirstMissingPositive {

	public static void main(String[] args) {
		int t = firstMissingPositive(new int[] {3,4,-1,1});
		System.out.println(t);
	}

	public static int firstMissingPositive(int[] nums) {	
		for (int i = 0; i < nums.length; i++) {
			// Note this is a while loop and not a for loop. 
			// It will keep on running till the nums[i] is at the correct position.
			// So if even after swapping, the number which is now at nums[i] should be at a different position, 
			// then the loop will run.
			// This keeps on happening until the nums[i] = i + 1 or nums[i] < 1
			while (nums[i] >= 1 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
				int temp = nums[nums[i] - 1];
				nums[nums[i] - 1] = nums[i];
				nums[i] = temp;
			}
		}
		
		int i = 0;
		for (i = 0; i < nums.length; i++) {
			if (nums[i] != i + 1) {
				return i + 1;
			}
		}
		
		return i+1;
	}
}
