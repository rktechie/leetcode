/*
695. Max Area of Island
Medium
Topics
Companies
You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

The area of an island is the number of cells with a value 1 in the island.

Return the maximum area of an island in grid. If there is no island, return 0.



Example 1:


Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
Output: 6
Explanation: The answer is not 11, because the island must be connected 4-directionally.
Example 2:

Input: grid = [[0,0,0,0,0,0,0,0]]
Output: 0


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 50
grid[i][j] is either 0 or 1.
 */
public class MaxAreaOfIsland {

  int max = 0, maxNow = 0;

  /*
   * Approach: DFS
   *
   * Time Complexity: O(R*C), where R is the number of rows in the given grid, and C is the number of columns.
   * We visit every square once.
   *
   * Space complexity: O(R*C), the space used by seen to keep track of visited squares and the space used
   * by the call stack during our recursion.
   */
  public int maxAreaOfIsland(int[][] grid) {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 1) {
          maxNow = 0;
          dfs(i, j, grid);
        }
      }
    }
    return max;
  }

  private void dfs(int i, int j, int[][] grid) {
    if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != 1) {
      return;
    }

    grid[i][j] = -1; // marking part of island visited not to check it next time
    maxNow++;

    dfs(i - 1, j, grid);
    dfs(i + 1, j, grid);
    dfs(i, j + 1, grid);
    dfs(i, j - 1, grid);

    max = Math.max(max, maxNow);
  }
}
