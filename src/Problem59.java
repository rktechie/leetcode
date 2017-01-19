
public class Problem59 {

	public static void main(String[] args) {
		int[][] t = generateMatrix(2);
	}
	
	// Similar to Problem 54
	public static int[][] generateMatrix(int n) {
		int[][] matrix = new int[n][n];
		if ( n == 0)
			return matrix;
		int xRow = n, yColumn = n;
		int row = 0, column = -1;
		int value = 1;
		while (true) {
			for (int i = 0; i < yColumn; i++)
				matrix[row][++column] = value++;
			if (--xRow == 0)
				break;
			for (int i = 0; i < xRow; i++)
				matrix[++row][column] = value++;
			if (--yColumn == 0)
				break;
			for (int i = 0; i < yColumn; i++)
				matrix[row][--column] = value++;
			if (--xRow == 0)
				break;
			for (int i = 0; i < xRow; i++)
				matrix[--row][column] = value++;
			if (--yColumn == 0)
				break;
		}
		
		return matrix;
	}
}
