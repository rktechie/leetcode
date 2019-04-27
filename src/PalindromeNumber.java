/*
 * Problem 9: Palindrome Number
 * 
Determine whether an integer is a palindrome. Do this without extra space.


Some hints:
Could negative integers be palindromes? (ie, -1)

If you are thinking of converting the integer to string, note the restriction of using extra space.

You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", you know that the reversed integer might overflow. How would you handle such case?

There is a more generic way of solving this problem.
 */
public class PalindromeNumber {

	public static void main(String[] args) {
		isPalindrome(12345);
	}

	// Method 1: 
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
			// Remove the first digit (% digits) and the last digit (/ 10)
			x = (x % digits) / 10;
			// As we removed the first and the last digit, so we reduce the size of digits by 2
			digits = digits / 100;
		}
		
		return true;
	}
	
	// Method 2: compare half of the digits in x, so don't need to deal with overflow.
	public boolean isPalindrome2(int x) {
	    if (x<0 || (x!=0 && x%10==0)) return false;
	    int rev = 0;
	    while (x>rev){
	    	rev = rev*10 + x%10;
	    	x = x/10;
	    }
	    return (x==rev || x==rev/10);
	}

}
