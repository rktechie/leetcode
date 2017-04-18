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
	
	// Pow(x, n) = Pow(x, n / 2) * Pow(x, n / 2)
	// Used recursion for this.
	public static double myPow(double x, int n) {
		if (x < 0)
			return (n % 2 == 0) ? myPow(-x, n) : -myPow(-x, n);
		if (x == 0 || x == 1)
			return x;
		if (n < 0)
			return 1.0 / myPow(x, -n);
		if (n == 0)
			return 1.0;
		if (n == 1)
			return x;
		double half = myPow(x, n / 2);
		if (n % 2 == 0)
			return half * half;
		else
			return x * half * half;
	}
}
