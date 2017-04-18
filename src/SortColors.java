/*
 * Problem 75: Sort Colors
 * 
Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem.

Follow up:
A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.

Could you come up with an one-pass algorithm using only constant space?
 */

public class SortColors {

	public static void main(String[] args) {

	}

	void swap(int[] A, int i, int j) {
		int t = A[i];
		A[i] = A[j];
		A[j] = t;
	}

	// Solution is on https://github.com/tg123/leetcode/tree/gh-pages/sort-colors
	public void sortColors(int[] nums) {
		int red = 0;
		int blue = nums.length - 1;
		int white = 0;

		while (white <= blue) {
			if (nums[white] == 0) { // red
				swap(nums, white, red);
				red++;
				white++;
			} else if (nums[white] == 1) { // white
				white++;
			} else if (nums[white] == 2) { // blue
				swap(nums, white, blue);
				blue--;
			}
		}
	}
}
