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
	public static boolean searchMatrix(int[][] matrix, int target) {
		int left = 0;
		int right = 0;
		int mid = 0;
		
		for (int i = 0; i < matrix.length; i++) {
			left = 0;
			right = matrix[i].length - 1;
			if (matrix[i][right] < target)	// Because the first integer of each row is greater than the last integer of the previous row. So if target is greater than last element, then just go to the next row.
				continue;
			while (left <= right) {
				mid = (left + right) / 2;
				if (matrix[i][mid] == target)
					return true;
				if (matrix[i][mid] < target)
					left = mid + 1;
				else
					right = mid - 1;
			}
		}
		
		return false;
	}
}
