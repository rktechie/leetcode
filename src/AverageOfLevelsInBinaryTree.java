import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
637. Average of Levels in Binary Tree
Easy
Topics
Companies
Given the root of a binary tree, return the average value of the nodes on each level in the form of an array. Answers within 10-5 of the actual answer will be accepted.


Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: [3.00000,14.50000,11.00000]
Explanation: The average value of nodes on level 0 is 3, on level 1 is 14.5, and on level 2 is 11.
Hence return [3, 14.5, 11].
Example 2:


Input: root = [3,9,20,15,7]
Output: [3.00000,14.50000,11.00000]


Constraints:

The number of nodes in the tree is in the range [1, 104].
-231 <= Node.val <= 231 - 1
 */
public class AverageOfLevelsInBinaryTree {

  /*
  Time complexity : O(n). The whole tree is traversed at most once. Here, n refers to the number of nodes
  in the given binary tree.

  Space complexity : O(m). The size of queue can grow upto at most the maximum number of nodes at any level.
   */
  public List<Double> averageOfLevels(TreeNode root) {
    List<Double> res = new ArrayList<>();
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      int size = queue.size();
      double sum = 0;
      int i = 0;
      while (i < size) {
        TreeNode curr = queue.poll();
        if (curr.left != null)
          queue.add(curr.left);
        if (curr.right != null)
          queue.add(curr.right);
        sum += curr.val;
        i++;
      }
      res.add(sum / size);
    }

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
