import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
1091. Shortest Path in Binary Matrix
Medium
Topics
Companies
Hint
Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.

A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:

All the visited cells of the path are 0.
All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
The length of a clear path is the number of visited cells of this path.



Example 1:


Input: grid = [[0,1],[1,0]]
Output: 2
Example 2:


Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
Output: 4
Example 3:

Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
Output: -1


Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 100
grid[i][j] is 0 or 1
 */
public class ShortestPathInBinaryMatrix {

  private static final int[][] directions =
      new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

  /*
   * Approach: Breadth-first Search
   *
   * Was asked a follow up to this question in the fb interview, look at the 2nd class of this file.
   */
  public int shortestPathBinaryMatrix(int[][] grid) {
    // Firstly, we need to check that the start and target cells are open.
    if (grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) {
      return -1;
    }

    // Set up the BFS.
    Queue<int[]> queue = new ArrayDeque<>();
    queue.add(new int[]{0, 0});
    boolean[][] visited = new boolean[grid.length][grid[0].length];
    visited[0][0] = true;
    int currentDistance = 1;

    // Carry out the BFS
    while (!queue.isEmpty()) {
      // Process all nodes at the current distance from the top-left cell.
      int nodesAtCurrentDistance = queue.size();
      for (int i = 0; i < nodesAtCurrentDistance; i++) {
        int[] cell = queue.remove();
        int row = cell[0];
        int col = cell[1];
        if (row == grid.length - 1 && col == grid[0].length - 1) {
          return currentDistance;
        }
        for (int[] neighbour : getNeighbours(row, col, grid)) {
          int neighbourRow = neighbour[0];
          int neighbourCol = neighbour[1];
          if (visited[neighbourRow][neighbourCol]) {
            continue;
          }
          visited[neighbourRow][neighbourCol] = true;
          queue.add(new int[]{neighbourRow, neighbourCol});
        }
      }
      // We'll now be processing all nodes at current_distance + 1
      currentDistance++;
    }

    // The target was unreachable.
    return -1;
  }

  private List<int[]> getNeighbours(int row, int col, int[][] grid) {
    List<int[]> neighbours = new ArrayList<>();
    for (int i = 0; i < directions.length; i++) {
      int newRow = row + directions[i][0];
      int newCol = col + directions[i][1];
      if (newRow < 0 || newCol < 0 || newRow >= grid.length
          || newCol >= grid[0].length
          || grid[newRow][newCol] != 0) {
        continue;
      }
      neighbours.add(new int[]{newRow, newCol});
    }
    return neighbours;
  }
}

/*
 * I had my FB phone screen and I was asked a different version of this problem - which is to return
 * the cells in the path from the top-left to bottom-right cell and not the shortest distance. I solved it using DFS not BFS.
 * But if asked give the shortest path cells and not just any path, then need to solve
 * using BFS instead of DFS as DFS will be very inefficient. DFS is very inefficient in this case as we will have to go
 * through each and every path to find the shortest path whereas for BFS we get the shorted path in this case
 * much much faster.
 *
 * This code first initializes a queue for BFS traversal and a visited array to keep track of visited cells.
 * It then performs BFS traversal while maintaining a parent map to reconstruct the path from the exit to the entrance.
 * If a path is found, it reconstructs the path and returns it; otherwise, it returns an empty list
 */
class ShortestPathInBinaryMatrixFollowUp {
  public static List<int[]> findPath(int[][] board) {
    List<int[]> path = new ArrayList<>();
    int rows = board.length;
    int cols = board[0].length;

    // Queue for BFS
    Queue<int[]> queue = new LinkedList<>();
    queue.offer(new int[]{0, 0});

    // Visited array to keep track of visited cells
    boolean[][] visited = new boolean[rows][cols];
    visited[0][0] = true;

    // Parent map to reconstruct the path
    Map<int[], int[]> parent = new HashMap<>();

    // Directions: right, down, left, up
    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    // BFS
    while (!queue.isEmpty()) {
      int[] curr = queue.poll();
      int row = curr[0];
      int col = curr[1];

      if (row == rows - 1 && col == cols - 1) {
        // Reconstruct path
        int[] node = new int[]{row, col};
        while (node != null) {
          path.add(0, node);
          node = parent.get(node);
        }
        return path;
      }

      for (int[] dir : dirs) {
        int newRow = row + dir[0];
        int newCol = col + dir[1];

        if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols
            && board[newRow][newCol] == 0 && !visited[newRow][newCol]) {
          queue.offer(new int[]{newRow, newCol});
          visited[newRow][newCol] = true;
          parent.put(new int[]{newRow, newCol}, curr);
        }
      }
    }

    return Collections.emptyList();
  }

  public static void main(String[] args) {
    int[][] board = {
        {0, 0, 0, 0, 0, 0, 0},
        {0, 0, 1, 0, 0, 1, 0},
        {0, 0, 1, 0, 1, 1, 0},
        {0, 0, 1, 0, 1, 0, 1},
        {1, 1, 1, 0, 0, 0, 0}
    };

    List<int[]> path = findPath(board);
    if (!path.isEmpty()) {
      for (int[] point : path) {
        System.out.printf("(%d, %d) -> ", point[0], point[1]);
      }
      System.out.println("exit");
    } else {
      System.out.println("No path found.");
    }
  }
}
