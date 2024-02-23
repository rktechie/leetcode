/*
 * Problem 50: Pow(x, n)
 * 
Implement pow(x, n).
 */
public class Pow {

	public static void main(String[] args) {
		double t = myPow(8, -3);
		System.out.println(t);
	}

	/**
	 * Solution 1: Binary exponentiation, also known as exponentiation by squaring, is a technique for efficiently computing the power
	 * of a number. By repeatedly squaring x and halving n, we can quickly compute x^n using a logarithmic number of multiplications
	 *
	 * eg: we need to find 2^100, we will do the following
	 * 2^100 = (2*2)^50
	 * 4^50 = (4∗4)^25
	 * 16^25 = 16*(16∗16)^12
	 *
	 * Time complexity: O(logn)
	 * At each recursive call we reduce n by half, so we will make only logn number of calls
	 */
	public static double myPow(double x, int n) {
		return binaryExp(x, (long) n);
	}

	private static double binaryExp(double x, long n) {
		// Base case, to stop recursive calls.
		if (n == 0) {
			return 1;
		}

		// Handle case where, n < 0.
		if (n < 0) {
			return 1.0 / binaryExp(x, -1 * n);
		}

		// Perform Binary Exponentiation.
		// If 'n' is odd we perform Binary Exponentiation on 'n - 1' and multiply result with 'x'.
		if (n % 2 == 1) {
			return x * binaryExp(x * x, (n - 1) / 2);
		}
		// Otherwise we calculate result by performing Binary Exponentiation on 'n'.
		else {
			return binaryExp(x * x, n / 2);
		}
	}

	// Solution 2:
	// Pow(x, n) = Pow(x, n / 2) * Pow(x, n / 2)
	// Used recursion for this.
	public static double myPow2(double x, int n) {
		if (x < 0)
			return (n % 2 == 0) ? myPow2(-x, n) : -myPow2(-x, n);
		if (x == 0 || x == 1)
			return x;
		if (n < 0)
			return 1.0 / myPow2(x, -n);
		if (n == 0)
			return 1.0;
		if (n == 1)
			return x;
		double half = myPow2(x, n / 2);
		if (n % 2 == 0)
			return half * half;
		else
			return x * half * half;
	}

}
