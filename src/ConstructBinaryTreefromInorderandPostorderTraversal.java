/*
 * Problem 106: Construct Binary Tree from Inorder and Postorder Traversal
 * 
Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree. 
 */

/*
 * Solution : Same as Construct Binary Tree from Preorder and Inorder Traversal
 * 
 The difference is run from end to 0 instead of run from 0 to end.

In additional, we should contruct the right tree before the left one.

Post-order: A, C, E, D, B, H, I, G, F
                                <- p

In-order:  A, B, C, D, E, F, G, H, I
 */

public class ConstructBinaryTreefromInorderandPostorderTraversal {

    int[] inorder;
    int[] postorder;
    int p;

    TreeNode buildTree(int start, int end) {
        if (start >= end)
            return null;

        TreeNode root = new TreeNode(postorder[p]);
        int i;
        for (i = start; i < end && inorder[i] != postorder[p]; i++)
            ;

        p--;
        root.right = buildTree(i + 1, end);
        root.left = buildTree(start, i);
        return root;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.p = postorder.length - 1;
        this.inorder = inorder;
        this.postorder = postorder;
        return buildTree(0, inorder.length);
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
