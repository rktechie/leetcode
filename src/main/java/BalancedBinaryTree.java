/*
 * Problem 110: Balanced Binary Tree
 * 
Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1. 
 */

public class BalancedBinaryTree {

    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        int diff = isBalancedHelper(root);
        if (diff == -1)
            return false;
        return true;
    }

    /*
     * Approach is from cracking the coding interview.
     */
    public int isBalancedHelper(TreeNode root) {
        if (root == null)
            return 0;
        int left = isBalancedHelper(root.left);
        if (left == -1)
            return -1;

        int right = isBalancedHelper(root.right);
        if (right == -1)
            return -1;

        if (Math.abs(left - right) > 1)
            return -1;

        return Math.max(left, right) + 1;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
