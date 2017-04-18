/*
 * Problem 200: Number of Islands
 * 
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

11110
11010
11000
00000

Answer: 1

Example 2:

11000
11000
00100
00011

Answer: 3
 */

public class NumberOfIslands {

    /*
     * In current solution, if we encounter '1' then we mark all the surrounding 1's as X so that we dont count it again. This is DFS and time complexity is O(m*n).
     * Another way of doing is to use a boolean matrix of same size and mark '1' and all the surrounding 1's as true in the boolean matrix. So in this way original matrix wont be changed.
     * But if we use a boolean matrix then we are using extra space.
     */
    public int numIslands(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0)
            return 0;

        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    merge(grid, i , j);
                    count++;
                }
            }
        }
        
        return count;
    }
    
    public static void merge(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1')
            return;
        
        grid[i][j] = 'X';
        merge(grid, i, j - 1);
        merge(grid, i - 1, j);
        merge(grid, i, j + 1);
        merge(grid, i + 1, j);
    }
}
