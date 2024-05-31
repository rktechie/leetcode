/*
 * Problem 36: Valid Sudoku
 * 
Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.


A partially filled sudoku which is valid.

Note:
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.


 */
import java.util.HashSet;

public class ValidSudoku {

	public static void main(String[] args) {

	}

	public boolean isValidSudoku(char[][] board) {
		int rowLength = board.length;
		int columnLength = board[0].length;
		HashSet<Character> hashSet = new HashSet<Character>();
		
		// Check if each row contains one number only once
		for (int i = 0; i < rowLength; i++) {
			for (int j = 0; j < columnLength; j++) {
				if (board[i][j] != '.') {
					if (hashSet.contains(board[i][j]))
						return false;
					hashSet.add(board[i][j]);
				}
			}
			hashSet.clear();
		}

		// Check if each column contains one number only once
		for (int j = 0; j < columnLength; j++) {
			for (int i = 0; i < rowLength; i++) {
				if (board[i][j] != '.') {
					if (hashSet.contains(board[i][j]))
						return false;
					hashSet.add(board[i][j]);
				}
			}
			hashSet.clear();
		}

		// Check each block
		for (int i = 0; i < rowLength; i = i + 3) {
			for (int j = 0; j < columnLength; j = j + 3) {
				for (int k = i; k < i + 3; k++) {
					for (int l = j; l < j + 3; l++) {
						if (board[k][l] != '.') {
							if (hashSet.contains(board[k][l]))
								return false;
							hashSet.add(board[k][l]);
						}
					}
				}
				hashSet.clear();
			}
			hashSet.clear();
		}
		
		return true;
	}
}
