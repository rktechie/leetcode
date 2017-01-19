/*
 * Problem 112: Path Sum
 * 
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
For example:
Given the below binary tree and sum = 22,

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1

return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 */

public class PathSum {

    public static void main(String[] args) {
        
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;
        return hasPathSumHelper(root, sum);
    }
    
    /*
     * Used recursion to go down the tree till the leaf node.
     * On reaching the leaf node we check the sum.
     * we keep subtracting the node's value from the sum so if the sum is zero at the root, then that path is equal to the sum.
     */
    public boolean hasPathSumHelper(TreeNode root, int sum) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null) {
            if (sum - root.val == 0)
                return true;
            else
                return false;
        }
        
        return hasPathSumHelper(root.left, sum - root.val) || hasPathSumHelper(root.right, sum - root.val);
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
