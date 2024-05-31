import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import javafx.util.Pair;


/*
314. Binary Tree Vertical Order Traversal
Medium
Topics
Companies
Hint
Given the root of a binary tree, return the vertical order traversal of its nodes' values. (i.e., from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.



Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]
Example 2:


Input: root = [3,9,8,4,0,1,7]
Output: [[4],[9],[3,0,1],[8],[7]]
Example 3:


Input: root = [3,9,8,4,0,1,7,null,null,null,2,5]
Output: [[4],[9,5],[3,0,1],[8,2],[7]]


Constraints:

The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
 */
public class BinaryTreeVerticalOrderTraversal {

  /*
   * Approach:
   * In theory, each node would have a 2-dimensional index (denoted as <column, row>)
   * Root node has a column index of 0, then its left child node would have a column index of -1 and its
   * right child node would have a column index of +1, and so on.
   * Let us assume that the root node has a row index of 0, then both its child nodes would have the row index of 1.
   *
   * Algo:
   * 1. First, we create a hash table named columnTable to keep track of the results.
   * 2. Use a queue data structure to keep track of the order we need to visit nodes.
   * We initialize the queue by putting the root node along with its column index value (0).
   * 3. run the BFS traversal with a loop consuming the elements from the queue.
   * 4. At each iteration within the BFS, we pop out an element from the queue. The element consists of
   * a node and its corresponding column index. If the node is not empty, we then populate the columnTable
   * with the value of the node. Subsequently, we then put its child nodes along with their respective
   * column indices (i.e. column-1 and column+1) into the queue.
   * 5. At the end of the BFS traversal, we obtain a hash table that contains the desired node values
   * grouped by their column indices. For each group of values, they are further ordered by their row indices.
   * 6. At the end of the BFS traversal, we would then walk through the column range [min_column, max_column] and retrieve the results accordingly.
   *
   * The key insight is that we only need to know the range of the column index (i.e. [min_column, max_column]).
   * Then we can simply iterate through this range to generate the outputs without the need for sorting.
   * The above insight would work under the condition that there won't be any missing column index in the given range.
   * And the condition always holds, since there won't be any broken branch in a binary tree.
   *
   * Time Complexity: O(N) where N is the number of nodes in the tree.
   *
   */
  public List<List<Integer>> verticalOrder(TreeNode root) {
    List<List<Integer>> output = new ArrayList<>();
    if (root == null) {
      return output;
    }

    Map<Integer, ArrayList<Integer>> columnTable = new HashMap<>();
    // Pair of node and its column offset
    Queue<Pair<TreeNode, Integer>> queue = new ArrayDeque<>();
    int column = 0;
    queue.offer(new Pair<>(root, column));

    int minColumn = 0, maxColumn = 0;

    while (!queue.isEmpty()) {
      Pair<TreeNode, Integer> p = queue.poll();
      root = p.getKey();
      column = p.getValue();

      if (root != null) {
        if (!columnTable.containsKey(column)) {
          columnTable.put(column, new ArrayList<Integer>());
        }
        columnTable.get(column).add(root.val);
        minColumn = Math.min(minColumn, column);
        maxColumn = Math.max(maxColumn, column);

        queue.offer(new Pair<>(root.left, column - 1));
        queue.offer(new Pair<>(root.right, column + 1));
      }
    }

    for (int i = minColumn; i < maxColumn + 1; ++i) {
      output.add(columnTable.get(i));
    }

    return output;
  }

  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }
}
