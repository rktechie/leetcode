/*
 * Problem 104: Maximum Depth of Binary Tree

Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 */

public class MaximumDepthofBinaryTree {

    public static void main(String[] args) {
        
    }

    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        int x = maxDepth(root.left);
        int y = maxDepth(root.right);
        
        return x > y ? x + 1 : y + 1;
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
