/*
 * Problem 62: Unique Paths
 * 
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?
Note: m and n will be at most 100.
 */


/*
Explanation of the Solution: https://github.com/tg123/leetcode/tree/gh-pages/unique-paths
 */

public class UniquePaths {

	public static void main(String[] args) {

	}

	public int uniquePaths(int m, int n) {
		if (m == 0 || n == 0)
			return 0;

		int[][] matrix = new int[m][n];

		int x, y;

		for (x = 0; x < m; x++)
			matrix[x][0] = 1;

		for (y = 0; y < n; y++)
			matrix[0][y] = 1;

		for (x = 1; x < m; x++) {
			for (y = 1; y < n; y++) {
				matrix[x][y] = matrix[x - 1][y] + matrix[x][y - 1];
			}
		}

		return matrix[m - 1][n - 1];
	}
}
