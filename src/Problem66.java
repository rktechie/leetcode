import java.util.Arrays;

/*
Question:
Given a non-negative number represented as an array of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.
 */
public class Problem66 {

	public static void main(String[] args) {
		int[] t = plusOne(new int[] {0});
	}

	public static int[] plusOne(int[] digits) {
		int carry = 0;
		int[] result = new int[digits.length + 1];
		
		digits[digits.length - 1] += 1;
		for (int i = digits.length - 1; i >= 0; i--) {
			digits[i] += carry;
			carry = digits[i] / 10;
			result[i + 1] = digits[i] % 10;
		}

		if (carry != 0) {
			result[0] = carry;
			return result;
		}
		if (result[0] == 0)
			return Arrays.copyOfRange(result, 1, result.length);

		return result;
	}
}
