import java.util.Arrays;

/*
 * Problem 322: Coin Change
 * 
You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:
Input: coins = [1, 2, 5], amount = 11
Output: 3 
Explanation: 11 = 5 + 5 + 1

Example 2:
Input: coins = [2], amount = 3
Output: -1
Note:
You may assume that you have an infinite number of each kind of coin.
 */
public class CoinChange {

	/*
	 * Dynamic programming - Bottom up
	 * 
	 * Time complexity : O(S*n). On each step the algorithm finds the next
	 * F(i) in n iterations, where S1≤i≤S. Therefore in total
	 * the iterations are S*n.
	 *
	 * Space complexity : O(S). We use extra space for the memoization table.
	 */
	public int coinChange(int[] coins, int amount) {
		int max = amount + 1;
		int[] dp = new int[amount + 1];
		Arrays.fill(dp, max);
		dp[0] = 0;
		for (int i = 1; i <= amount; i++) {
			for (int j = 0; j < coins.length; j++) {
				if (coins[j] <= i) {
					dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1); // we do "+1" because we are including coins[j] element, so incrementing the count of coins by 1
				}
			}
		}
		return dp[amount] > amount ? -1 : dp[amount];
	}
}
