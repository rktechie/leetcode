/*
 * Problem 124: Binary Tree Maximum Path Sum
 * 
Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6
Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42
 */
public class BinaryTreeMaximumPathSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	int maxValue;

	/**
	 * A path from start to end, goes up on the tree for 0 or more steps, then goes down for 0 or more steps. 
	 * Once it goes down, it can't go up. Each path has a highest node, which is also the lowest common ancestor of 
	 * all other nodes on the path.
	 * 
	 * A recursive method maxPathDown(TreeNode node) 
	 * (1) computes the maximum path sum with highest node is the input node, update maximum if necessary 
	 * (2) returns the maximum sum of the path that can be extended to input node's parent.
	 */
	public int maxPathSum(TreeNode root) {
		maxValue = Integer.MIN_VALUE;
		maxPathDown(root);
		return maxValue;
	}

	private int maxPathDown(TreeNode node) {
		if (node == null)
			return 0;
		// We do Math.max(0,...) because if the maxPathDown(left) or maxPathDown(right) is -ve,
		// then we will not take that path which amounts to 0 in value.
		int left = Math.max(0, maxPathDown(node.left));
		int right = Math.max(0, maxPathDown(node.right));
		maxValue = Math.max(maxValue, left + right + node.val); // keep checking for maxValue at each stage

		// as this can be a path of a bigger subtree, we can only go in either left or right direction so we take the max and return to the caller
		return Math.max(left, right) + node.val;
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
