/*
 * Problem 91 : Decode Ways
 * 
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 */
public class DecodeWays {
	
	public static void main(String[] args) {
		int res = numDecodings("226");
		System.out.println(res);
	}

	/*
	 * Solution: DP
	 * It's right to initialize dp[0] = 1, but it's not accurate to say "an empty string will have one way to decode". 
	 * In dynamic programming, we usually set dp[0] = 1.
	 * 
	 * dp[1] : when there's one character, if it is not zero, it can only be 1 decode way. 
	 * If it is 0, there will be no decode ways.
	 * 
	 * First check if substring (i-1,i) is 0 or not. If it is 0, skip it, continue right 
	 * to check substring (i-2,i), cuz 0 can only be decoded by being together with the char before 0.
	 * 
	 * Second, check if substring (i-2,i) falls in 10~26. 
	 * If it does, it means there are dp[i-2] more new decode ways.
	 * 
	 * Time: should be O(n), where n is the length of String
	 */
	public static int numDecodings(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		
		int n = s.length();
		int[] dp = new int[n + 1]; // note size is n + 1
		dp[0] = 1; // an empty string will have one way to decode
		dp[1] = s.charAt(0) != '0' ? 1 : 0; // means the way to decode a string of size 1.
		
		for (int i = 2; i <= n; i++) {
			int first = Integer.valueOf(s.substring(i - 1, i)); // prev digit
			int second = Integer.valueOf(s.substring(i - 2, i)); // prev 2 digits
			System.out.println("First: " + first + " Second: " + second);
			if (first >= 1 && first <= 9) {
				dp[i] = dp[i - 1];
			}
			if (second >= 10 && second <= 26) {
				dp[i] += dp[i - 2];
			}
		}

		return dp[n];
	}
}
