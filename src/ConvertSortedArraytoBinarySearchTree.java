/*
 * Problem 108: Convert Sorted Array to Binary Search Tree
 * 
Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 */

public class ConvertSortedArraytoBinarySearchTree {

    public static void main(String[] args) {
        sortedArrayToBST(new int[]{1, 3});
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
    }
    
    public static TreeNode sortedArrayToBSTHelper(int[] nums, int start, int end) {
        if (start > end)
            return null;
        if (start == end)
            return new TreeNode(nums[start]);
        int mid = start + (end - start) / 2; // OR mid = (left + right) / 2
        TreeNode p = new TreeNode(nums[mid]);
        p.left = sortedArrayToBSTHelper(nums, start, mid - 1);
        p.right = sortedArrayToBSTHelper(nums, mid + 1, end);
        
        return p;
    }
    
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
