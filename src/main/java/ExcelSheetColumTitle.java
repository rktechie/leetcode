/*
 * Problem 168: Excel Sheet Column Title
 * 
Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
 */

public class ExcelSheetColumTitle {

	public static void main(String args[]) {
		int x = 'A';
		System.out.println(x);
	}

	public String convertToTitle(int n) {
		StringBuffer sb = new StringBuffer();
		while (n > 0) {
			sb.insert(0, (char) ((n - 1) % 26 + 'A'));
			n = (n - 1) / 26;
		}
		return sb.toString();
	}
}
