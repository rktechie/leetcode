/*
 * Problem 189 : Rotate Array
 * 
Rotate an array of n elements to the right by k steps.

For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

Note:
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.

[show hint]
Hint:
Could you do it in-place with O(1) extra space?

Related problem: Reverse Words in a String II
 */

public class Problem189 {

    /*
     * Solution : Reversal (In-place with O(1) extra space and O(n) time)
     * 
     * 1. Divide the array two parts: 1,2,3,4 and 5, 6 
     * 2. Reverse first part:4,3,2,1,5,6 
     * 3. Reverse second part: 4,3,2,1,6,5 
     * 4. Reverse the whole array: 5,6,1,2,3,4
     */
    public void rotate(int[] nums, int k) {
        if (k > nums.length) {
            k = k % nums.length;
        }

        // length of first part
        int a = nums.length - k;

        reverse(nums, 0, a - 1);
        reverse(nums, a, nums.length - 1);
        reverse(nums, 0, nums.length - 1);
    }

    public static void reverse(int[] arr, int left, int right) {
        if (arr == null || arr.length == 1)
            return;

        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}
