import java.util.LinkedList;
import java.util.Queue;

/*
958. Check Completeness of a Binary Tree
Medium
Topics
Companies
Given the root of a binary tree, determine if it is a complete binary tree.

In a complete binary tree, every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.



Example 1:


Input: root = [1,2,3,4,5,6]
Output: true
Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.
Example 2:


Input: root = [1,2,3,4,5,null,7]
Output: false
Explanation: The node with value 7 isn't as far left as possible.


Constraints:

The number of nodes in the tree is in the range [1, 100].
1 <= Node.val <= 1000
 */
public class CheckCompletenessOfABinaryTree {

  /*
   * Approach 1: BFS
   *
   * By analyzing the definition, we can see that a binary tree is complete if there is no node to the right
   * of the first null node and no node at a greater level than the first null node.
   * It means that if we traverse the tree level by level from left to right and we come across a null node,
   * all subsequent nodes in this traversal should be null as well (should not exist). The level-order traversal
   * array of a complete binary tree will never have a null node in between non-null nodes.
   *
   */
  public boolean isCompleteTree(TreeNode root) {
    if (root == null) {
      return true;
    }

    boolean nullNodeFound = false;
    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);

    while (!q.isEmpty()) {
      TreeNode node = q.poll();

      if (node == null) {
        nullNodeFound = true;
      } else {
        if (nullNodeFound) {
          return false;
        }
        q.offer(node.left);
        q.offer(node.right);
      }
    }
    return true;
  }

  /*
   * Approach 2: DFS
   */
  public boolean isCompleteTree2(TreeNode root) {
    return dfs(root, 0, countNodes(root));
  }

  public int countNodes(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return 1 + countNodes(root.left) + countNodes(root.right);
  }

  public boolean dfs(TreeNode node, int index, int n) {
    if (node == null) {
      return true;
    }
    // If index assigned to current node is greater or equal to the number of nodes
    // in tree, then the given tree is not a complete binary tree.
    if (index >= n) {
      return false;
    }
    // Recursively move to left and right subtrees.
    return dfs(node.left, 2 * index + 1, n) &&
        dfs(node.right, 2 * index + 2, n);
  }


  public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }
}
