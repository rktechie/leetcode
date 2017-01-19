import java.util.Stack;

/*
 * Problem : Binary Search Tree Iterator
 * 
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree. 
 */
/**
 * Your BSTIterator will be called like this: 
 * BSTIterator i = new BSTIterator(root); 
 * while (i.hasNext()) 
 *      v[f()] = i.next();
 */
public class Problem173 {
}

class BSTIterator {
    private Stack<TreeNode> stk;
    private TreeNode node;

    public BSTIterator(TreeNode root) {
        stk = new Stack<TreeNode>();
        node = root;
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        if (stk.isEmpty() == true && node == null)
            return false;
        return true;
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        if (stk.isEmpty() == true && node == null)
            return 0;
        while (node != null) {
            stk.push(node);
            node = node.left;
        }
        int res = 0;
        node = stk.pop();
        res = node.val;
        node = node.right;
        return res;
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
