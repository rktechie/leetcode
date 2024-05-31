/*
 * Problem 129: Sum Root to Leaf Numbers
 * 
Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3

The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25. 
 */

public class SumRoottoLeafNumbers {

    public static void main(String[] args) {

    }

    // 1st method:
    // Time complexity: O(N) since one has to visit each node.
    // Space complexity: up to O(H) to keep the stack, where H is a tree height.
    public int sumNumbers(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null) {
            return root.val;
        }
        return sumNumbersHelper(root, "");
    }

    public int sumNumbersHelper(TreeNode root, String currentPath) {
        if (root.left == null && root.right == null) {
            return Integer.parseInt(currentPath + root.val);
        }

        int x = root.left == null ? 0 : sumNumbersHelper(root.left, currentPath + root.val);
        int y = root.right == null ? 0 : sumNumbersHelper(root.right, currentPath + root.val);

        return x + y;
    }

    
    // 2nd method:
    // Time complexity: O(N) since one has to visit each node.
    // Space complexity: up to O(H) to keep the stack, where H is a tree height.
    public int sumNumbers2(TreeNode root) {
        return sumNumbers2Helper(root, 0);
    }

    int sumNumbers2Helper(TreeNode root, int parentval) {
        if (root == null)
            return 0;

        int p = parentval * 10 + root.val;
        if (root.left == null && root.right == null)
            return p;
        
        return sumNumbers2Helper(root.left, p) + sumNumbers2Helper(root.right, p);
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
