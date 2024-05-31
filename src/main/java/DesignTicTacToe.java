/*
348. Design Tic-Tac-Toe
Medium
Topics
Companies
Hint
Assume the following rules are for the tic-tac-toe game on an n x n board between two players:

A move is guaranteed to be valid and is placed on an empty block.
Once a winning condition is reached, no more moves are allowed.
A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
Implement the TicTacToe class:

TicTacToe(int n) Initializes the object the size of the board n.
int move(int row, int col, int player) Indicates that the player with id player plays at the cell (row, col) of the board. The move is guaranteed to be a valid move, and the two players alternate in making moves. Return
0 if there is no winner after the move,
1 if player 1 is the winner after the move, or
2 if player 2 is the winner after the move.


Example 1:

Input
["TicTacToe", "move", "move", "move", "move", "move", "move", "move"]
[[3], [0, 0, 1], [0, 2, 2], [2, 2, 1], [1, 1, 2], [2, 0, 1], [1, 0, 2], [2, 1, 1]]
Output
[null, 0, 0, 0, 0, 0, 0, 1]

Explanation
TicTacToe ticTacToe = new TicTacToe(3);
Assume that player 1 is "X" and player 2 is "O" in the board.
ticTacToe.move(0, 0, 1); // return 0 (no one wins)
|X| | |
| | | |    // Player 1 makes a move at (0, 0).
| | | |

ticTacToe.move(0, 2, 2); // return 0 (no one wins)
|X| |O|
| | | |    // Player 2 makes a move at (0, 2).
| | | |

ticTacToe.move(2, 2, 1); // return 0 (no one wins)
|X| |O|
| | | |    // Player 1 makes a move at (2, 2).
| | |X|

ticTacToe.move(1, 1, 2); // return 0 (no one wins)
|X| |O|
| |O| |    // Player 2 makes a move at (1, 1).
| | |X|

ticTacToe.move(2, 0, 1); // return 0 (no one wins)
|X| |O|
| |O| |    // Player 1 makes a move at (2, 0).
|X| |X|

ticTacToe.move(1, 0, 2); // return 0 (no one wins)
|X| |O|
|O|O| |    // Player 2 makes a move at (1, 0).
|X| |X|

ticTacToe.move(2, 1, 1); // return 1 (player 1 wins)
|X| |O|
|O|O| |    // Player 1 makes a move at (2, 1).
|X|X|X|


Constraints:

2 <= n <= 100
player is 1 or 2.
0 <= row, col < n
(row, col) are unique for each different call to move.
At most n2 calls will be made to move.


Follow-up: Could you do better than O(n2) per move() operation?
 */
public class DesignTicTacToe {

  /*
   * Approach:
   * Let's break the problem into 2 parts:
   * 1. On every move, we must determine whether a player has marked all of the cells in a row or column
   * 2. On every move, we must determine whether a player has marked all of the cells on the main diagonal or anti-diagonal.
   *
   * Algorithm:
   * 1. To implement the first part, for each player, we will build an array col and rows of size n,
   * where rows[i] stores the number of times a player has marked a cell on the i'th row and col.
   * Winning Condition: The player will win if either rows[i] or cols[j] is equal to n after the player
   * has marked the cell on the i'th row and j'th column
   *
   * 2. To implement the second part, we can use a similar idea as above. Since there is only one diagonal
   * and one anti-diagonal, for each player, we only need 2 integer variables diagonal and antiDiagonal.
   * These variables will store how many times a cell has been marked on each of the diagonals.
   * Winning Condition: After a player has marked a cell on a diagonal row, we check if the value of variable
   * diagonal for that player is equal to n. Similarly, after a player has marked a cell on an anti-diagonal
   * row, we check if the value of variable antiDiagonal for that player is equal to n
   *
   * Question - Can we further optimize this algorithm?
   * Yes, we can. Since there are only 2 players, when implementing part 1, we can use the same data structure to store the marked row and column values for both players.
   * One way to implement this is to increment the count when player 1 marks a cell and decrement the count when player 2 marks a cell. With this, we can say that, if the value of rows[i] is equal to n, player 1 has marked i'th
   * row n times. Similarly, if the value of rows[i] is equal to -n, then player 2 has marked the i'th row n times.
   * Similar logic applies to the columns and diagonals.
   *
   * For each move, we must increment/decrement the row, column, diagonal, and anti-diagonal according
   * to who is the current player and which cell was marked. If the current player is player 1, we increment
   * the value and if it is player 2, we decrement the value.
   * We can use an additional variable currentPlayer with the value 1 for player 1 and -1 for player 2,
   * and add the value of currentPlayer to the current row, column, diagonal and anti-diagonal.
   *
   *
   */
  public class TicTacToe {
    int[] rows;
    int[] cols;
    int diagonal;
    int antiDiagonal;

    public TicTacToe(int n) {
      rows = new int[n];
      cols = new int[n];
    }

    public int move(int row, int col, int player) {
      int currentPlayer = (player == 1) ? 1 : -1;
      // update currentPlayer in rows and cols arrays
      rows[row] += currentPlayer;
      cols[col] += currentPlayer;
      // update diagonal
      if (row == col) {
        diagonal += currentPlayer;
      }
      //update anti diagonal
      if (col == (cols.length - row - 1)) {
        antiDiagonal += currentPlayer;
      }
      int n = rows.length;
      // check if the current player wins
      if (Math.abs(rows[row]) == n ||
          Math.abs(cols[col]) == n ||
          Math.abs(diagonal) == n ||
          Math.abs(antiDiagonal) == n) {
        return player;
      }
      // No one wins
      return 0;
    }
  }

}
