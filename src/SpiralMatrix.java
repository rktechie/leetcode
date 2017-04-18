/*
 * Problem 54: Spiral Matrix
 * 
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].
 */
import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

	public static void main(String[] args) {
		int[][] matrix = { { 2, 5 }, { 8, 4 }, { 0, -1 } };
		List<Integer> t = spiralOrder(matrix);
		for (int x : t)
			System.out.println(x);
	}

	public static List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> list = new ArrayList<Integer>();
		if (matrix.length == 0 || matrix[0].length == 0)
			return list;
		int n = matrix.length, m = matrix[0].length;
		int row = 0, column = -1;
		while (true) {

			for (int i = 0; i < m; i++) {
				list.add(matrix[row][++column]);
			}
			if (--n == 0)
				break;
			for (int i = 0; i < n; i++) {
				list.add(matrix[++row][column]);
			}
			if (--m == 0)
				break;
			for (int i = 0; i < m; i++) {
				list.add(matrix[row][--column]);
			}
			if (--n == 0)
				break;
			for (int i = 0; i < n; i++) {
				list.add(matrix[--row][column]);
			}
			if (--m == 0)
				break;
		}

		return list;
	}
}
