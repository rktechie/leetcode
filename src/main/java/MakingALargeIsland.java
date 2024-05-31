import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import javafx.util.Pair;

/*
827. Making A Large Island
Hard
Topics
Companies
You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.

Return the size of the largest island in grid after applying this operation.

An island is a 4-directionally connected group of 1s.



Example 1:

Input: grid = [[1,0],[0,1]]
Output: 3
Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
Example 2:

Input: grid = [[1,1],[1,0]]
Output: 4
Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
Example 3:

Input: grid = [[1,1],[1,1]]
Output: 4
Explanation: Can't change any 0 to 1, only one island with area = 4.


Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 500
grid[i][j] is either 0 or 1.
 */
public class MakingALargeIsland {

  public int N = 0;

  /*
   * Approach:
   *
   * 1. Prepare
   * - valid(int x, int y), check if (x, y) is valid in the grid.
   * - move(int x, int y), return all possible next position in 4 directions.
   *
   * 2. Explanation
   * - Explore every island using DFS, count its area, give it an island index and save the result to a {index: area} map.
   * Note the grid elements are updated with their corresponding island index.
   * Since the grid has elements 0 or 1. The island index is initialized with 2
   * - Loop every cell == 0, check its connected islands and calculate total islands area. The possible
   * connected island index is stored in a set to remove duplicate index.
   *
   *
   * Time Complexity - O(N^2)
   * Space Complexity - O(N^2)
   */
  public int largestIsland(int[][] grid) {
    N = grid.length;
    // DFS every island and give it an index of island
    int index = 2, res = 0;
    HashMap<Integer, Integer> area = new HashMap<>();
    for (int x = 0; x < N; ++x) {
      for (int y = 0; y < N; ++y) {
        if (grid[x][y] == 1) {
          area.put(index, dfs(grid, x, y, index));
          res = Math.max(res, area.get(index));
          index++;
        }
      }
    }

    //traverse every 0 cell and count biggest island it can connect
    for (int x = 0; x < N; ++x) {
      for (int y = 0; y < N; ++y) {
        if (grid[x][y] == 0) {
          HashSet<Integer> seen = new HashSet<>();
          int cur = 1;
          for (Pair<Integer, Integer> p : move(x, y)) {
            index = grid[p.getKey()][p.getValue()];
            if (index > 1 && !seen.contains(index)) {
              seen.add(index);
              cur += area.get(index);
            }
          }
          res = Math.max(res, cur);
        }
      }
    }
    return res;
  }

  public List<Pair<Integer, Integer>> move(int x, int y) {
    ArrayList<Pair<Integer, Integer>> res = new ArrayList<>();
    if (valid(x, y + 1)) {
      res.add(new Pair<Integer, Integer>(x, y + 1));
    }
    if (valid(x, y - 1)) {
      res.add(new Pair<Integer, Integer>(x, y - 1));
    }
    if (valid(x + 1, y)) {
      res.add(new Pair<Integer, Integer>(x + 1, y));
    }
    if (valid(x - 1, y)) {
      res.add(new Pair<Integer, Integer>(x - 1, y));
    }
    return res;
  }

  public boolean valid(int x, int y) {
    return 0 <= x && x < N && 0 <= y && y < N;
  }

  public int dfs(int[][] grid, int x, int y, int index) {
    int area = 0;
    grid[x][y] = index; // as we modify the grid here, we dont need to store a visited grid
    for (Pair<Integer, Integer> p : move(x, y)) {
      if (grid[p.getKey()][p.getValue()] == 1) {
        area += dfs(grid, p.getKey(), p.getValue(), index);
      }
    }
    return area + 1;
  }
}