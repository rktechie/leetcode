
public class Problem9 {

	public static void main(String[] args) {
		isPalindrome(12345);
	}

	static public boolean isPalindrome(int x) {
		// Negative integers cannot be a palindrome
		if(x < 0) {
			return false;
		}
		
		// Count the number of digits. Eg: for input = 12345, the digits = 10000 
		int digits = 1;
		while(x / digits >= 10) {
			digits *= 10;
		}
		
		while(x != 0) {
			int left = x / digits;
			int right = x % 10;
			if(left != right) {
				return false;
			}
			// Remove the first digit
			x = (x % digits) / 10;
			// Remove the last digit
			digits = digits / 100;
		}
		
		return true;
	}

}
