/*
 * Problem 74: Search a 2D Matrix
 * 
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

    Integers in each row are sorted from left to right.
    The first integer of each row is greater than the last integer of the previous row.

For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]

Given target = 3, return true.
 */

public class Search2DMatrix {

	public static void main(String[] args) {
		boolean t = searchMatrix(new int[][] {{1,1}}, 0);
		System.out.println(t);
	}

	// Use binary search in each row to find the target.
	/*
	 * We start search the matrix from top right corner, initialize the current position to top right corner, 
	 * if the target is greater than the value in current position, then the target can not be in entire row of 
	 * current position because the row is sorted, if the target is less than the value in current position, 
	 * then the target can not in the entire column because the column is sorted too. 
	 * 
	 * We can rule out one row or one column each time, so the time complexity is O(m+n).
	 */
	public static boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length < 1 || matrix[0].length <1) {
            return false;
        }
        int col = matrix[0].length-1;
        int row = 0;
        while(col >= 0 && row <= matrix.length-1) {
            if(target == matrix[row][col]) {
                return true;
            } else if(target < matrix[row][col]) {
                col--;
            } else if(target > matrix[row][col]) {
                row++;
            }
        }
        return false;
    }
}
