/*
 * Problem 31: Next Permutation
 * 
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1

 * See the explanation of the solution from http://fisherlei.blogspot.com/2012/12/leetcode-next-permutation.html
 * NOTE: I understood the solution and what is done. BUT I DIDNT understand why its done!!!
 */

import java.util.Arrays;
public class NextPermutation {

	public static void main(String[] args) {

	}

	public void nextPermutation(int[] nums) {
		if (nums.length < 2)
			return;
		// From right to left, Find the partition number ,i.e., the number which violates the increasing trend. 
		int p = -1;
		for (int i = nums.length - 1; i > 0; i--) {
			if (nums[i] > nums[i - 1]) {
				p = i - 1;
				break;
			}
		}
		
		// if p = -1, then the array is in descending order. So we just sort it to get in ascending order
		if (p == -1) {
			Arrays.sort(nums);
			return;
		}
		
		// From right to left, Find the first digit (call it change number), which is larger than the partition number
		int c = -1;
		for (int i = nums.length - 1; i >= 0; i--) {
			if (nums[i] > nums[p]) {
				c = i;
				break;
			}
		}
		
		// Swap the partition number and the change number
		swap(nums, p, c);
		
		// Reverse all the digits which are on the right of the partition index
		Arrays.sort(nums, p + 1, nums.length);
	}

	void swap(int x[], int a, int b) {
		int t = x[a];
		x[a] = x[b];
		x[b] = t;
	}
}
