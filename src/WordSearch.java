/**
 * 
 * Problem 79: Word Search
 * 
 * Given a 2D board and a word, find if the word exists in the grid.
 * 
 * The word can be constructed from letters of sequentially adjacent cell, where
 * "adjacent" cells are those horizontally or vertically neighboring. The same
 * letter cell may not be used more than once.
 * 
 * For example, Given board =
 * 
 * [ ['A','B','C','E'], ['S','F','C','S'], ['A','D','E','E'] ]
 * 
 * word = "ABCCED", -> returns true, word = "SEE", -> returns true, word =
 * "ABCB", -> returns false.
 */

public class WordSearch {

    public static void main(String[] args) {
        char[][] board = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };
        boolean test = exist(board, "SEE");
        System.out.println(test);
    }

    public static boolean exist(char[][] board, String word) {
        int m = board.length;
        if (m == 0)
            return false;
        int n = board[0].length;
        if (n == 0)
            return false;
        if (word.length() == 0)
            return true;
        boolean[][] visited = new boolean[m][n];
        
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == word.charAt(0) && existHelper(board, i, j, word, 0, visited)) {
                    return true;
                }
            }
        }
        
        return false;
    }

    public static boolean existHelper(char[][] board, int x, int y, String word, int index, boolean[][] visited) {
        if (index == word.length())
            return true;
        int m = board.length;
        int n = board[0].length;
        
        if (x < 0 || x >= m || y < 0 || y >= n)
            return false;
        
        if (visited[x][y] == true || (board[x][y] != word.charAt(index)))
            return false;
        
        visited[x][y] = true;
        
        if (existHelper(board, x + 1, y, word, index + 1, visited))
            return true;
        
        if (existHelper(board, x - 1, y, word, index + 1, visited))
            return true;
        
        if (existHelper(board, x, y + 1, word, index + 1, visited))
            return true;
        
        if (existHelper(board, x, y - 1, word, index + 1, visited))
            return true;
        
        visited[x][y] = false;
        
        return false;
    }
}
