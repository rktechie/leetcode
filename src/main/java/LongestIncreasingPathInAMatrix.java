/*
329. Longest Increasing Path in a Matrix
Hard
Topics
Companies
Given an m x n integers matrix, return the length of the longest increasing path in matrix.

From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).



Example 1:


Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
Output: 4
Explanation: The longest increasing path is [1, 2, 6, 9].
Example 2:


Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
Output: 4
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
Example 3:

Input: matrix = [[1]]
Output: 1


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 200
0 <= matrix[i][j] <= 231 - 1
 */
public class LongestIncreasingPathInAMatrix {

  private static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
  private int m, n;

  /*
   * Approach: DFS + Memoization
   *
   * DFS can find the longest increasing path starting from any cell. We can do this for all the cells.
   *
   * Algo:
   * - Each cell can be seen as a vertex in a graph G. If two adjacent cells have value a<b, i.e.
   * increasing then we have a directed edge (a,b).
   * - The problem then becomes: Search the longest path in the directed graph G.
   * - Naively, we can use DFS or BFS to visit all the cells connected starting from a root.
   * - We update the maximum length of the path during search and find the answer when it finished.
   *
   * The key observation is that the sequence is strictly increasing, so it cannot have loops. So we have the following:
   * longest(i,j) = longest increasing path from (i,j) to (k,l) + longest(k,l)
   * where longest(i,j) is longest increasing path starting with (i,j).
   *
   * Traverse all points in matrix, use every point as starting point to do dfs traversal.
   * DFS function returns max increasing path after comparing four return distance from four directions and
   * skip cells that are out of boundary or smaller
   *
   * Time complexity: O(mn)
   * Each vertex/cell will be calculated once and only once, and each edge will be visited once and only once.
   * The total time complexity is then O(V+E). V is the total number of vertices and E is the total number of edges.
   * In our problem, O(V)=O(mn), O(E)=O(4V)=O(mn).
   *
   * Space complexity : O(mn). The cache dominates the space complexity.
   *
   */
  public int longestIncreasingPath(int[][] matrix) {
    if (matrix.length == 0) {
      return 0;
    }
    m = matrix.length;
    n = matrix[0].length;
    int[][] cache = new int[m][n]; // cache[i][j] represents longest increasing path that starts from point matrix[i][j]
    int ans = 0;
    for (int i = 0; i < m; ++i) {
      for (int j = 0; j < n; ++j) {
        ans = Math.max(ans, dfs(matrix, i, j, cache));
      }
    }
    return ans;
  }

  private int dfs(int[][] matrix, int i, int j, int[][] cache) {
    if (cache[i][j] != 0) {
      return cache[i][j];
    }
    for (int[] d : dirs) {
      int x = i + d[0], y = j + d[1];
      if (0 <= x && x < m && 0 <= y && y < n && matrix[x][y] > matrix[i][j]) { // here we check boundary and the increasing condition
        cache[i][j] = Math.max(cache[i][j], dfs(matrix, x, y, cache));
      }
    }
    return ++cache[i][j]; // add 1 since the path includes starting point itself
  }
}
