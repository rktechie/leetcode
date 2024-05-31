/*
 * Problem 70: Climbing Stairs
 * 
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer.
 */
public class ClimbingStairs {

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
