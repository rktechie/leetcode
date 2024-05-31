import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javafx.util.Pair;

/*
543. Diameter of Binary Tree
Easy
Topics
Companies
Given the root of a binary tree, return the length of the diameter of the tree.

The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

The length of a path between two nodes is represented by the number of edges between them.



Example 1:


Input: root = [1,2,3,4,5]
Output: 3
Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
Example 2:

Input: root = [1,2]
Output: 1


Constraints:

The number of nodes in the tree is in the range [1, 104].
-100 <= Node.val <= 100
 */
public class DiameterOfBinaryTree {

  private int diameter;

  /*
   * Approach: DFS
   *
   * Let N be the number of nodes in the tree.
   * Time complexity: O(N). This is because in our recursion function longestPath, we only enter and exit from
   * each node once. We know this because each node is entered from its parent, and in a tree, nodes only have one parent.
   *
   * Space complexity: O(N). The space complexity depends on the size of our implicit call stack during our DFS,
   * which relates to the height of the tree.  In the worst case, the tree is skewed so the height of the tree is O(N).
   * If the tree is balanced, it'd be O(logN).
   */
  public int diameterOfBinaryTree(TreeNode root) {
    diameter = 0;
    longestPath(root);
    return diameter;
  }

  private int longestPath(TreeNode node) {
    if (node == null) {
      return 0;
    }
    // recursively find the longest path in both left child and right child
    int leftPath = longestPath(node.left);
    int rightPath = longestPath(node.right);

    // update the diameter if left_path plus right_path is larger
    diameter = Math.max(diameter, leftPath + rightPath);

    // return the longest one between left_path and right_path;
    // remember to add 1 for the path connecting the node and its parent
    return Math.max(leftPath, rightPath) + 1;
  }


  class TreeNode {

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

/*
 * In Meta interview, a follow up to the above question is asked: print the path of the diameter?
 *
 * the solution is very similar to the above solution
 */
class FollowUp {

  private int diameter;
  private ArrayList<Integer> diameterPath;

  public Pair<Integer, ArrayList<Integer>> diameterOfBinaryTree(TreeNode root) {
    diameter = 0;
    diameterPath = new ArrayList<>();
    longestPath(root);
    return new Pair<>(diameter, diameterPath);
  }

  private Pair<Integer, ArrayList<Integer>> longestPath(TreeNode node) {
    if (node == null) {
      return new Pair<>(0, new ArrayList<>());
    }

    Pair<Integer, ArrayList<Integer>> leftPair = longestPath(node.left);
    Pair<Integer, ArrayList<Integer>> rightPair = longestPath(node.right);

    if (leftPair.getKey() + rightPair.getKey() > diameter) {
      diameter = leftPair.getKey() + rightPair.getKey();
      diameterPath = new ArrayList<>();
      diameterPath.addAll(leftPair.getValue());
      diameterPath.add(node.val);

      // note: we have to reverse the right path before adding it to the diameterPath to maintain the correct order
      ArrayList<Integer> rightPathCloned = new ArrayList<>(rightPair.getValue());
      Collections.reverse(rightPathCloned);
      diameterPath.addAll(rightPathCloned);
    }

    int newDiameter;
    ArrayList<Integer> newDiameterPath = new ArrayList<>();
    if (leftPair.getKey() > rightPair.getKey()) {
      newDiameter = 1 + leftPair.getKey();
      newDiameterPath.addAll(leftPair.getValue());
    } else {
      newDiameter = 1 + rightPair.getKey();
      newDiameterPath.addAll(rightPair.getValue());
    }
    newDiameterPath.add(node.val);

    return new Pair<>(newDiameter, newDiameterPath);
  }

  class TreeNode {

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
