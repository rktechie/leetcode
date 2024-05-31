import java.util.ArrayList;
import java.util.List;

/*
2049. Count Nodes With the Highest Score
Medium
Topics
Companies
Hint
There is a binary tree rooted at 0 consisting of n nodes. The nodes are labeled from 0 to n - 1. You are given a 0-indexed integer array parents representing the tree, where parents[i] is the parent of node i. Since node 0 is the root, parents[0] == -1.

Each node has a score. To find the score of a node, consider if the node and the edges connected to it were removed. The tree would become one or more non-empty subtrees. The size of a subtree is the number of the nodes in it. The score of the node is the product of the sizes of all those subtrees.

Return the number of nodes that have the highest score.



Example 1:

example-1
Input: parents = [-1,2,0,2,0]
Output: 3
Explanation:
- The score of node 0 is: 3 * 1 = 3
- The score of node 1 is: 4 = 4
- The score of node 2 is: 1 * 1 * 2 = 2
- The score of node 3 is: 4 = 4
- The score of node 4 is: 4 = 4
The highest score is 4, and three nodes (node 1, node 3, and node 4) have the highest score.
Example 2:

example-2
Input: parents = [-1,2,0]
Output: 2
Explanation:
- The score of node 0 is: 2 = 2
- The score of node 1 is: 2 = 2
- The score of node 2 is: 1 * 1 = 1
The highest score is 2, and two nodes (node 0 and node 1) have the highest score.


Constraints:

n == parents.length
2 <= n <= 105
parents[0] == -1
0 <= parents[i] <= n - 1 for i != 0
parents represents a valid binary tree.
 */
public class CountNodesWithTheHighestScore {

  long maxScore;
  int ans;

  /*
   * Approach: Graph, DFS, Post-order Traversal, O(N)
   *
   * - Intuition: Maximum product of 3 branches, need to know how many nodes in each branch, use DFS to start with
   * - Build graph
   * - Find left, right, parent (number of nodes) for each node
   *    - left: use recursion
   *    - right: use recursion
   *    - parent: n - 1 - left - right
   * - Calculate score store in a dictionary
   * - Return count of max key
   *
   * Why traversing the tree in DFS style, we keep calculating the count and product of its children assuming we remove the current node
   *
   * why at max we say it will be a product of 3 subtrees/branch? because if we remove the current node, at most we
   * will have 3 subtrees remaining which is - parent subtree, left subtree and right subtree
   *
   * need to know how many nodes in each branch because this will give us the count which is used to
   * calculate the product
   *
   * Time: O(n)
   */
  public int countHighestScoreNodes(int[] parents) {
    int n = parents.length;
    maxScore = 0L; // stores the maximum score
    ans = 0; // store the number of nodes that have the maximum score
    List<Integer>[] tree = new ArrayList[n]; // at max there will be n nodes in the tree
    for (int i = 0; i < n; ++i) {
      tree[i] = new ArrayList<>();
    }

    for (int i = 1; i < n; ++i) {
      tree[parents[i]].add(i); // Indirectly, we are adding the left and right children of the current node. It will always be max 2.
    }

    dfs(tree, 0, n);
    return ans;
  }

  // returns the number of nodes in the subtree with root v.
  private int dfs(List<Integer>[] tree, int v, int n) {
    int total = 0; // stores total number of nodes in the subtree with root v, excluding v.
    long prod = 1L;

    // traversing children in the subtree (always max 2)
    for (int child : tree[v]) {
      int cnt = dfs(tree, child, n); // number of nodes in the subtree with root child.
      total += cnt;
      prod *= cnt;
    }

    // finding how many nodes are remaining in the parent subtree = n - total - 1
    // where:
    // n = Total nodes in the whole tree
    // total = count of left subtree of the current node + count of right subtree of the current node
    // 1 = current node (as we want to assume we are removing the current node)
    //
    // why math.max(1, n - total - 1) because it is possible that on removing the current node there
    // is no parent subtree remaining, so multiplying by 0 can mess the calculation
    prod *= Math.max(1, n - total - 1);

    // updating maxScore and count.
    if (prod > maxScore) {
      maxScore = prod;
      ans = 1;
    } else if (prod == maxScore) {
      ans += 1;
    }

    return total + 1; // returning total number of nodes in the subtree with node v.
  }

}
