/*
 * Problem 114: Flatten Binary Tree to Linked List
 * 
Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6

The flattened tree should look like:

   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6

click to show hints.
Hints:

If you notice carefully in the flattened tree, each node's right child points to the next node of a pre-order traversal.

 */

public class FlattenBinaryTreeToLinkedList {

    /*
     * We keep going down the tree till we reach the node whose children are leafs or till the left of the left child of the parent is null.
     * 
     * We then make the left child as the right child of the parent. And the original right child becomes the right child of the new right child.
     * 
     * While shifting the left child as the right child, If the left child is not a single node, then we find the right most element of that left child,
     * now the original right child will become the right child of this rightmost child.
     * 
     * Keep this example in your mind : [1,2,null,3]
     * If we don't find the rightmost child of the left child during shifting phase then the answer we get is: [1,null,2]
     * But the actual answer is : [1,null,2,null,3]
     */
    public void flatten(TreeNode root) {
        if (root == null)
            return;

        while (root.left != null) { // note this is a while loop
            if (root.left.left == null) {
                TreeNode temp = root.left;
                root.left = null;
                TreeNode rightMost = temp;
                while (rightMost != null && rightMost.right != null)
                    rightMost = rightMost.right;
                rightMost.right = root.right;
                root.right = temp;
            } else {
                flatten(root.left);
            }
        }

        flatten(root.right);
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
