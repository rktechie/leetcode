/*
 * Problem : Sparse Matrix Multiplication
 * 
Given two sparse matrices A and B, return the result of AB.

You may assume that A's column number is equal to B's row number.

Example:

Input:

A = [
  [ 1, 0, 0],
  [-1, 0, 3]
]

B = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
]

Output:

     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                  | 0 0 1 |
     
NOTE: A SPARSE MATRIX IS A MATRIX WITH MAJORITY OF THE ELEMENTS ZERO
 */
public class SparseMatrixMultiplication {

	/*
	 * Note: https://leetcode.com/problems/sparse-matrix-multiplication/discuss/76151/54ms-Detailed-Summary-of-Easiest-JAVA-solutions-Beating-99.9
	 * MUST go through the above link to understand the "smartness" of the solution below.
	 * 
	 * 
	 * For brute force solution, for each C[ i ] [ j ], 
	 * it uses C[ i ] [ j ] += A[ i ] [ k ] * B[ k ] [ j ] where k = [ 0, n].
	 * Note: even A[ i ] [ k ] or B[ k ] [ j ] is 0, the multiplication is still executed.
	 * 
	 * For the smart solution, if A[ i ] [ k ] == 0 or B[ k ] [ j ] == 0, it just skip the multiplication . 
	 * This is achieved by moving for-loop" for ( k = 0; k < n; k++ ) " from inner-most loop to middle loop,
	 * so that we can use if-statement to tell whether A[ i ] [ k ] == 0 or B[ k ] [ j ] == 0. 
	 */
    public int[][] multiply(int[][] A, int[][] B) {
        int m = A.length, n = A[0].length, nB = B[0].length;
        int[][] C = new int[m][nB];

        for(int i = 0; i < m; i++) {
            for(int k = 0; k < n; k++) { // note: this is k and not j
                if (A[i][k] != 0) {
                    for (int j = 0; j < nB; j++) {
                        if (B[k][j] != 0) 
                        	C[i][j] += A[i][k] * B[k][j];
                    }
                }
            }
        }
        return C;   
    }
}
