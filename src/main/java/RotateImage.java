/*
 * Problem 48: Rotate Image
 * 
You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Follow up:
Could you do this in-place?
 */
public class RotateImage {

	// Note this is not in-place
	// Convert Each column of matrix to a row in result.
	// Then Copy the result to the matrix
	// Note : matrix = result does not work. Java is pass by value and not pass by reference.
	
	public void rotate(int[][] matrix) {
		int[][] result = new int[matrix.length][matrix.length];
		int k = 0;

		if (matrix.length == 1) {
			result = matrix;
		} else {
			//Conversion phase
			for (int j = 0; j < matrix.length; j++) {
				k = 0;
				for (int i = matrix.length - 1; i >= 0; i--) {
					result[j][k] = matrix[i][j];
					k++;
				}
			}
		}
		
		//Copy phase
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				matrix[i][j] = result[i][j];
			}
		}
	}
	
	
	
	
	// This is in-place (From internet)
	/*
	 * Algorithm: 1st Step - Transpose (reflect the matrix over the main diagonal); 
	 * 			  2nd Step - Reverse (exchange each number along the vertical line in the middle)
	 *   
	  		  123   ->  147   ->   741    (preferable)
              456       258        852
              789       369        963
	 */
	public void rotate_1(int[][] matrix) {
		int n = matrix.length;
		if (n <= 1)
			return;
		
		// Transpose
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = tmp;
			}
		}
		
		// Reverse
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n / 2; j++) {
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[i][n - 1 - j];
				matrix[i][n - 1 - j] = tmp;
			}
		}
	}
}
