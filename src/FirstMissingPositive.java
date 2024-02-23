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

	/*
	 * First of all, let's get rid of negative numbers and zeros since there is no need for them.
	 * One could get rid of all numbers larger than n as well since the first missing positive is for sure
	 * smaller or equal to n + 1
	 *
	 * To ensure that the first missing positive is not 1, one has to verify the presence of 1 before proceeding
	 * to replace all 0, -ve and larger > n numbers with 1.
	 *
	 * Now we have an array that contains only positive numbers in a range from 1 to n
	 * Check each element value elem, and change the sign of element nums[elem] to negative to mark that
	 * number elem is present in nums.
	 *
	 * Be careful with duplicates and ensure that the sign was changed only once.
	 *
	 * Time complexity: O(N)
	 */
	public static int firstMissingPositive(int[] nums) {
		int n = nums.length;

		// Base case.
		int contains = 0;
		for (int i = 0; i < n; i++) {
			if (nums[i] == 1) {
				contains++;
				break;
			}
		}

		if (contains == 0) {
			return 1;
		}

		// Replace negative numbers, zeros, and numbers larger than n by 1s.
		// After this conversion nums will contain only positive numbers.
		for (int i = 0; i < n; i++) {
			if ((nums[i] <= 0) || (nums[i] > n)) {
				nums[i] = 1;
			}
		}

		// Use index as a hash key and number sign as a presence detector.
		// For example, if nums[1] is negative that means that number `1` is present in the array.
		// If nums[2] is positive - number 2 is missing.
		for (int i = 0; i < n; i++) {
			int a = Math.abs(nums[i]);
			// If you meet number a in the array - change the sign of a-th element.
			// Be careful with duplicates : do it only once.
			if (a == n) {
				nums[0] = -Math.abs(nums[0]); // Use index 0 to save information about the presence of number n since index n is not available
			} else {
				nums[a] = -Math.abs(nums[a]);
			}
		}

		// Now the index of the first positive number is equal to first missing positive.
		for (int i = 1; i < n; i++) {
			if (nums[i] > 0) {
				return i;
			}
		}

		if (nums[0] > 0) {
			return n;
		}

		return n + 1; // If in the previous step, you didn't find the positive element in nums, that means that the answer is n + 1.
	}


	/*
	 * Approach 2
	 */
	public static int firstMissingPositive2(int[] nums) {
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
