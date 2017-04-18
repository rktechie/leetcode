/*
 * Problem 63: Unique Paths 2
 * 
Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,

There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]

The total number of unique paths is 2.

Note: m and n will be at most 100.
 */

public class UniquePaths2 {

	public static void main(String[] args) {

	}
	
	// Similar to Problem 62 i.e Unique Paths
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;
		int x, y;

		if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1)
			return 0;

		int[][] dp = new int[m][n];
		dp[0][0] = 1; // The one represents that 1 path exists. This 1 does not represent an empty space or an obstacle.
		
		// If there is an obstacle, i.e == 1 then the unique paths from it is 0. And as this is an obstacle on the first row, then the elements ahead will have same value as the previous one.
		// You cannot jump over the obstacle.
		for (x = 1; x < m; x++)
			dp[x][0] = obstacleGrid[x][0] == 1 ? 0 : dp[x - 1][0];

		for (y = 1; y < n; y++)
			dp[0][y] = obstacleGrid[0][y] == 1 ? 0 : dp[0][y - 1];

		for (x = 1; x < m; x++) {
			for (y = 1; y < n; y++) {
				dp[x][y] = obstacleGrid[x][y] == 1 ? 0 : dp[x - 1][y] + dp[x][y - 1];
			}
		}

		return dp[m - 1][n - 1];
	}
}
