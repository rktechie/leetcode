/*
289. Game of Life
Medium
Topics
Companies
According to Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

The board is made up of an m x n grid of cells, where each cell has an initial state: live (represented by a 1) or dead (represented by a 0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population.
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously. Given the current state of the m x n grid board, return the next state.



Example 1:


Input: board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
Output: [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
Example 2:


Input: board = [[1,1],[1,0]]
Output: [[1,1],[1,1]]


Constraints:

m == board.length
n == board[i].length
1 <= m, n <= 25
board[i][j] is 0 or 1.


Follow up:

Could you solve it in-place? Remember that the board needs to be updated simultaneously: You cannot update some cells first and then use their updated values to update other cells.
In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches upon the border of the array (i.e., live cells reach the border). How would you address these problems?
 */
public class GameOfLife {

  /*
  - To solve in place, we start marking the cells next generation state with a new value in that same block.
  But in such a way, that the other cells which are not processed yet, can understand what was their
  neightbors original state so that we can accurately calculate their next state.

  - In practice, We have to simultaneously update each cells state for the next generation. Updating
  one cells state should have NO impact in the current simulation on the neighbors evaluation of next
  generation state.

  Original/current state  (1st bit) -> next state (2nd bit) => resultant tag
  0 (dead) -> 0 (dead) => 0
  1 (live) -> 0 (dead) => 1
  0 (dead) -> 1 (live) => 2
  1 (live) -> 1 (live) => 3
   */
  public void gameOfLife(int[][] board) {
    if (board == null || board.length == 0) {
      return;
    }
    int m = board.length, n = board[0].length;

    int rows = board.length;
    int cols = board[0].length;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        int around = countLive(i, j, board);
        if (board[i][j] == 0 && around == 3) {
          board[i][j] = 3;
        } else if (board[i][j] == 1) {
          if (around == 2 || around == 3) {
            continue;
          }
          if (around < 2 || around > 3) {
            board[i][j] = 2;
          }
        }
      }
    }

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (board[i][j] == 2) {
          board[i][j] = 0;
        }
        if (board[i][j] == 3) {
          board[i][j] = 1;
        }
      }
    }
  }

  private int countLive(int i, int j, int[][] board) {
    int count = 0;
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    for (int[] dir : dirs) {
      int x = i + dir[0];
      int y = j + dir[1];
      if (x >= 0 && y >= 0 && x < board.length && y < board[0].length) {
        if (board[x][y] == 1 || board[x][y] == 2) {
          count++;
        }
      }
    }

    return count;
  }
}
