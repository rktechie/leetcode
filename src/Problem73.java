/*
Question:
Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place. 
 */
public class Problem73 {

	public static void main(String[] args) {

	}

	// First note down the rows and the columns where 0 had occurred. We do not need exact location, we just need the row number and the column number.
	// Then set those rows and columns to 0.
	public void setZeroes(int[][] matrix) {
		boolean[] row = new boolean[matrix.length];
		boolean[] column = new boolean[matrix[0].length];

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					row[i] = true;
					column[j] = true;
				}
			}
		}
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (row[i] == true || column[j] == true)
					matrix[i][j] = 0;
			}
		}
	}
}
