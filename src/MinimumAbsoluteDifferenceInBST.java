/*
530. Minimum Absolute Difference in BST
Easy
Topics
Companies
Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two different nodes in the tree.



Example 1:


Input: root = [4,2,6,1,3]
Output: 1
Example 2:


Input: root = [1,0,48,null,null,12,49]
Output: 1


Constraints:

The number of nodes in the tree is in the range [2, 104].
0 <= Node.val <= 105


Note: This question is the same as 783: https://leetcode.com/problems/minimum-distance-between-bst-nodes/
 */
public class MinimumAbsoluteDifferenceInBST {
  int minDifference = Integer.MAX_VALUE;
  // Initially, it will be null.
  TreeNode prevNode;

  /**
   *  Approach: In-order Traversal Without List
   *
   *  The in-order traversal works by visiting the left subtree of a node first, then handling the node itself
   *  and finally visiting the right subtree. Since all the nodes in the left subtree are lesser than the
   *  current node's value and all nodes in the right subtree are greater than the current node's value,
   *  it generates a sorted list of values.
   *
   *  We only need the immediate in-order predecessor of any node to calculate the minimum difference.
   *  Thus, we can avoid storing elements in a list if we can find the difference between consecutive
   *  nodes on the fly during in-order traversal.
   *
   *
   *  Time complexity: O(n) We traverse once over each node of the BST.
   *
   *  Space complexity: O(n) The in-order traversal is recursive and would take some space to store the
   *  stack calls. The maximum number of active stack calls at a time would be the tree's height, which
   *  in the worst case would be O(n)
   */
  int getMinimumDifference(TreeNode root) {
    inorderTraversal(root);
    return minDifference;
  }

  void inorderTraversal(TreeNode node) {
    if (node == null) {
      return;
    }

    inorderTraversal(node.left);
    // Find the difference with the previous value if it is there.
    if (prevNode != null) {
      minDifference = Math.min(minDifference, node.val - prevNode.val);
    }
    prevNode = node;
    inorderTraversal(node.right);
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
