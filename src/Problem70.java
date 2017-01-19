
public class Problem70 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	// This is correct BUT tle
	public static int climbStairs(int n) {
		if (n == -1)
			return 0;
		if (n == 0 || n == 1)
			return n;
		
		int sum = climbStairs(n - 1) + climbStairs(n - 2);
		
		return sum;
	}
	
	// This is completely correct.
	public static int climbStairs1(int n) {
        int[] f = new int[n+1];
        f[0] = 1; f[1] = 1;
        for (int i = 2; i <= n; ++i)
            f[i] = f[i-1] + f[i-2];
        return f[n];
    }
}
