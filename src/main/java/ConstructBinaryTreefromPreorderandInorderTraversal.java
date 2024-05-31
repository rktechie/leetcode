/*
 * 
 * Problem 105: Construct Binary Tree from Preorder and Inorder Traversal
 * 
Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree. 
 */


/*
 * 
 * 
Solution:  Recursion

Pre-order: F, B, A, D, C, E, G, I, H
           p

In-order:  A, B, C, D, E, F, G, H, I
                          q

From first char F in Pre-order, we are sure that the root of the tree is F.

and from In-order, F divide the tree into two parts, the left one is A, B, C, D, E, the right one is G, H, I.

               F
             /   \
A, B, C, D, E     G, H, I

A, B, C, D, E and B, A, D, C, E become a new buildTree problem. so we can build the tree recursively. the result tree is the left children of F.

Similarly, G, H, I and G, I, H become the right children of F.
 */

public class ConstructBinaryTreefromPreorderandInorderTraversal {
    int p = 0;
    int[] preorder;
    int[] inorder;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        this.p = 0;
        return buildTree(0, inorder.length);
    }

    TreeNode buildTree(int start, int end) {
        if (start >= end)
            return null;
        
        TreeNode root = new TreeNode(preorder[p]);
        int i;
        
        for (i = start; i < end && inorder[i] != preorder[p]; i++);

        p++;
        root.left = buildTree(start, i);
        root.right = buildTree(i + 1, end);
        return root;
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
