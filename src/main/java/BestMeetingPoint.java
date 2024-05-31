import java.util.ArrayList;
import java.util.List;

/*
296. Best Meeting Point
Hard
Topics
Companies
Hint
Given an m x n binary grid grid where each 1 marks the home of one friend, return the minimal total travel distance.

The total travel distance is the sum of the distances between the houses of the friends and the meeting point.

The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.



Example 1:


Input: grid = [[1,0,0,0,1],[0,0,0,0,0],[0,0,1,0,0]]
Output: 6
Explanation: Given three friends living at (0,0), (0,4), and (2,2).
The point (0,2) is an ideal meeting point, as the total travel distance of 2 + 2 + 2 = 6 is minimal.
So return 6.
Example 2:

Input: grid = [[1,1]]
Output: 1


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 200
grid[i][j] is either 0 or 1.
There will be at least two friends in the grid.
 */
public class BestMeetingPoint {

  /*
   * Approach: Collect Coordinates in Sorted Order
   *
   * 1. First we collect both the row and column coordinates.
   * The way we collect the coordinates, we get both row list and col list in a sorted order (this is extremely crucial to accurately calculate the median)
   *
   * 2. Select their middle elements (i.e. median) because as long as there is equal number of points to the left and right of the meeting point, the total distance is minimized.
   *
   * 3. Calculate min distance for rows and then for columns from the middle element
   *
   * You may argue that the mean is close to the optimal point. But imagine a larger case with many 1's
   * congregating on the right side and just a single 1 on the left-most side. Using the mean as the meeting
   * point would be far from optimal.
   * In fact, the median must be the optimal meeting point.
   *
   * Notice that the Manhattan distance is the sum of two independent variables. Therefore, once we solve
   * the 1D case, we can solve the 2D case as two independent 1D problems.
   *
   * You can calculate the distance without knowing the median using a two pointer approach.
   *
   * Time complexity : O(mn)
   */
  public int minTotalDistance(int[][] grid) {
    List<Integer> rows = collectRows(grid);
    List<Integer> cols = collectCols(grid);

    int row = rows.get(rows.size() / 2);
    int col = cols.get(cols.size() / 2);

    return minDistance1D(rows, row) + minDistance1D(cols, col);
  }

  private List<Integer> collectRows(int[][] grid) {
    List<Integer> rows = new ArrayList<>();
    for (int row = 0; row < grid.length; row++) { // note: here row is outer for loop. This is so we get rows in sorted format
      for (int col = 0; col < grid[0].length; col++) {
        if (grid[row][col] == 1) {
          rows.add(row);
        }
      }
    }
    return rows;
  }

  private List<Integer> collectCols(int[][] grid) {
    List<Integer> cols = new ArrayList<>();
    for (int col = 0; col < grid[0].length; col++) { // note: here col is outer for loop. This is so we get columns in sorted format
      for (int row = 0; row < grid.length; row++) {
        if (grid[row][col] == 1) {
          cols.add(col);
        }
      }
    }
    return cols;
  }

  private int minDistance1D(List<Integer> points, int origin) {
    int distance = 0;
    for (int point : points) {
      distance += Math.abs(point - origin);
    }
    return distance;
  }

}
