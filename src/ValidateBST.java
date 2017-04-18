/**
 * Problem 98 : Validate BST
 * 
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

    The left subtree of a node contains only nodes with keys less than the node's key.
    The right subtree of a node contains only nodes with keys greater than the node's key.
    Both the left and right subtrees must also be binary search trees.

confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.

OJ's Binary Tree Serialization:

The serialization of a binary tree follows a level order traversal, where '#' signifies a path terminator where no node exists below.

Here's an example:

   1
  / \
 2   3
    /
   4
    \
     5

The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}". 

 * @author rishabh
 *
 */

public class ValidateBST {

    public static void main(String[] args) {

    }

    /**
     * 1st solution : Through upper and lower bounds
     * Complexity : O(n)
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;
        
        return isValidBSTHelper(root, (long)Integer.MIN_VALUE - 1, (long) Integer.MAX_VALUE + 1); 
    }

    public boolean isValidBSTHelper(TreeNode root, long left, long right) {
        if (root == null)
            return true;
        
        return left < root.val && root.val < right && isValidBSTHelper(root.left, left, root.val) && isValidBSTHelper(root.right, root.val, right);
    }
    
    
    /**
     * 2nd Solution : Use inorder traversal 
     * Complexity : O(n)
     */
    boolean isValidBST_2(TreeNode root) {
        long[] val = new long[1];
        val[0] = (long)Integer.MIN_VALUE - 1;
        
        return inorder(root, val);
    }
    
    // We keep going left till we encounter null and then we move right.
    boolean inorder(TreeNode root, long[] val) {
        if (root == null) 
            return true;
        if (inorder(root.left, val) == false) 
            return false;
        if (root.val <= val[0]) 
            return false;
        val[0] = root.val;
        
        return inorder(root.right, val);
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
