import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * Problem 144: Binary Tree Preorder Traversal
 * 
Given a binary tree, return the preorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},

   1
    \
     2
    /
   3

return [1,2,3].

Note: Recursive solution is trivial, could you do it iteratively?
 */

public class BinaryTreePreorderTraversal {

    // Solution 1: Recursive method
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        preorderTraversalHelper(root, result);

        return result;
    }

    public void preorderTraversalHelper(TreeNode root, List<Integer> result) {
        if (root == null)
            return;
        result.add(root.val);
        preorderTraversalHelper(root.left, result);
        preorderTraversalHelper(root.right, result);
    }

    /* 
     * Solution 2 : Iterative method. 
     * Note in the while loop, we first add right and then we add left.
     * This is because if we pop, we will first get left and then we will get right.
     * Preorder is Parent, Left, Right.
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null)
            return result;
        Stack<TreeNode> stk = new Stack<TreeNode>();
        stk.push(root);
        while (stk.isEmpty() == false) {
            TreeNode cur = stk.pop();
            result.add(cur.val);
            if (cur.right != null)
                stk.push(cur.right);
            if (cur.left != null)
                stk.push(cur.left);
        }

        return result;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
