import java.util.LinkedList;
import java.util.Queue;

/*
 * Problem 130: Surrounded Regions
 * 
 Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,

X X X X
X O O X
X X O X
X O X X

After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X

 */

public class SurroundedRegions {

    public static void main(String[] args) {

    }

    /*
     * This solution is BFS.
     * Traverse from the boarder to the inside and mark all the 'O's that are not surrounded by 'X' as 'V' (visited).
     * 
     * In the end, we check if it is 'V' then we mark it back as 'O' because they are the spots which are not
     * entirely surrounded by 'X'
     */
    public void solve(char[][] board) {
        if (board.length == 0 || board[0].length == 0)
            return;
        int M = board.length, N = board[0].length;
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (i == 0 || j == 0 || i == M - 1 || j == N - 1) {
                    bfs(board, i, j);
                }
            }
        }
        for (int i = 0; i < M; ++i)
            for (int j = 0; j < N; ++j)
                board[i][j] = (board[i][j] == 'V') ? 'O' : 'X';
    }

    public void bfs(char[][] board, int row, int col) {
        if (board[row][col] != 'O') // We only want to continue if the spot is 'O'
            return;
        int M = board.length, N = board[0].length;
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(row);
        q.offer(col);
        while (!q.isEmpty()) {
            int i = q.poll();
            int j = q.poll();
            if (i < 0 || i == M || j < 0 || j == N)
                continue;
            if (board[i][j] != 'O')
                continue;// important to recheck!
            board[i][j] = 'V';
            q.offer(i - 1);
            q.offer(j);
            q.offer(i + 1);
            q.offer(j);
            q.offer(i);
            q.offer(j - 1);
            q.offer(i);
            q.offer(j + 1);
        }
    }
}
