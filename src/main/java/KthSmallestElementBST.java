import java.util.LinkedList;

/*
 * Problem 230: Kth Smallest Element in a BST
 * 
Given a binary search tree, write a function kth Smallest to find the kth smallest element in it.

Note:
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently?
How would you optimize the kth Smallest routine?

Hint:

    Try to utilize the property of a BST.
    What if you could modify the BST node's structure?
    The optimal runtime complexity is O(height of BST).

 */
public class KthSmallestElementBST {

    public static void main(String[] args) {

    }

    // 1st Solution: DFS in-order recursive:
    // time complexity: O(N)
    private static int number = 0;
    private static int count = 0;

    public int kthSmallest1(TreeNode root, int k) {
        count = k;
        helper(root);
        return number;
    }

    public void helper(TreeNode n) {
        if (n.left != null)
            helper(n.left);
        count--;
        if (count == 0) {
            number = n.val;
            return;
        }
        if (n.right != null)
            helper(n.right);
    }

    // 2nd Solution: DFS in-order iterative:
    // time complexity: O(N)
    public int kthSmallest(TreeNode root, int k) {
        LinkedList<TreeNode> stack = new LinkedList<>();

        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (--k == 0) {
                return root.val;
            }
            root = root.right;
        }
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
