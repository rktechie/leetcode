/*
 * Problem : Second Minimum Node In a Binary Tree
 * 
Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node. 
If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes. 
More formally, the property root.val = min(root.left.val, root.right.val) always holds.

Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.

If no such second minimum value exists, output -1 instead.

Example 1:

Input: 
    2
   / \
  2   5
     / \
    5   7

Output: 5
Explanation: The smallest value is 2, the second smallest value is 5.
 

Example 2:

Input: 
    2
   / \
  2   2

Output: -1
Explanation: The smallest value is 2, but there isn't any second smallest value.
 */
public class SecondMinimumNodeInBinaryTree {

	/*
	 * Solution: DFS
	 * 
	 * Steps:
	 * 1. Compare the root.val with left and right nodes
	 * 2. If the root.val is same as either of the nodes, then make a recursive call on that node, to find the next min
	 * 3. If the val is not same, then record that val as that can be the next min. No need to recursion on that node because that node holds the min of that subtree.
	 * 4. Compare left and right
	 * 	- If any of the val = -1, then return the other val as thats the next min
	 *  - If both the vals are != -1, then get the min of the 2 vals
	 * 
	 */
	public int findSecondMinimumValue(TreeNode root) {
	    // each node in this tree has exactly two or zero sub-node
	    if (root.left == null) {
	        return -1;
	    }
	    
	    int left = -1;
	    if (root.val != root.left.val) { // find a value bigger than root.val
	        left = root.left.val;
	    } else {
	        left = findSecondMinimumValue(root.left);
	    }
	    
	    int right = -1;
	    if (root.val != root.right.val) { // find a value bigger than root.val
	        right = root.right.val;
	    } else {
	        right = findSecondMinimumValue(root.right);
	    }
	    
	    if (left == -1 || right == -1) { // the bigger value is left or right, or it not exist
	        return Math.max(left, right);
	    } else { // the min of left and right is what we want
	        return Math.min(left, right);
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
