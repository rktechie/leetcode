/*
 * Problem 221: Maximal Square
 * 
Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Return 4. 
 */

public class MaximalSquare {

    /*
     * Dynamic Programming
     * 
     * 1) Construct a sum matrix S[R][C] for the given M[R][C].
     * a) Copy first row and first columns as it is from M[][] to S[][].
     * b) For other entries, use following expressions to construct S[][]
         If M[i][j] is 1 then
            S[i][j] = min(S[i][j-1], S[i-1][j], S[i-1][j-1]) + 1
         Else If M[i][j] is 0
            S[i][j] = 0
       2) Find the maximum entry in S[R][C].
       3) Using the value and coordinates of maximum entry in S[i], print sub-matrix of M[][].
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0)
            return 0;
        int m = matrix.length, n = matrix[0].length, result = 0;
        int[][] b = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    b[i][j] = Math.min(Math.min(b[i][j - 1], b[i - 1][j - 1]), b[i - 1][j]) + 1;
                    result = Math.max(b[i][j], result); // update result
                }
            }
        }
        return result * result;
    }
}
