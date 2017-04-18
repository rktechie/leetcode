/*
 * Problem 43: Multiply Strings
 * 
Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2.

Note:

The length of both num1 and num2 is < 110.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.

------------------------------------
Algorithm: Multiple in the reverse way we do with pen and paper. 
Then individually check each array element before printing it.
 *
 * 
Another algo: The method shown is in reverse order we do on pen and paper. 
We can also get the answer by following exactly how we multiply with pen and paper.
 */
public class MultiplyStrings {

	public static void main(String[] args) {
		 String t = multiply("99", "10");
		 System.out.println(t);
	}
	
	// Solution is from the internet
	public static String multiply(String num1, String num2) {
		int[] result = new int[num1.length() + num2.length()];
		
		if (num1.length() == 0 || num2.length() == 0) {
			return "";
		}
		if (num1.charAt(0) == '0' || num2.charAt(0) == '0') {
			return "0";
		}
		
		// Eg: 98 * 10
		// In the algo, we start multiple from the first digit and not the last. Therefore, we multiply in this order - 1*9, 1*8, 0*9, 0*8
		for (int i = 0; i < num1.length(); i++) {
			for (int j = 0; j < num2.length(); j++) {
				result[i+j+1] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0'); 
			}
		}
		
		int carry = 0;
		StringBuilder builder = new StringBuilder();
		for (int i = result.length - 1; i >=1; i--) {
			result[i] = result[i] + carry;
			builder.append(result[i] % 10);
			carry = result[i] / 10;
		}
		if (carry != 0 || result[0] != 0) {
			builder.append(carry+result[0]);
		}
		
		return builder.reverse().toString();
	}

}
