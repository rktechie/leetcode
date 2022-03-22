/*
 * Problem : Add Strings
 * 
Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

Note:

The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class AddStrings {

	// start add from the last positions of both the strings
	public String addStrings(String num1, String num2) {
		int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
		StringBuilder sb = new StringBuilder();

		while (i >= 0 || j >= 0 || carry != 0) {
			if (i >= 0)
				carry += num1.charAt(i--) - '0';
			if (j >= 0)
				carry += num2.charAt(j--) - '0';
			sb.append(carry % 10);
			carry /= 10;
		}

		return sb.reverse().toString();
	}
}
