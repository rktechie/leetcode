/*
2291. Maximum Profit From Trading Stocks
Medium
Topics
Companies
Hint
You are given two 0-indexed integer arrays of the same length present and future where present[i] is the current price of the ith stock and future[i] is the price of the ith stock a year in the future. You may buy each stock at most once. You are also given an integer budget representing the amount of money you currently have.

Return the maximum amount of profit you can make.



Example 1:

Input: present = [5,4,6,2,3], future = [8,5,4,3,5], budget = 10
Output: 6
Explanation: One possible way to maximize your profit is to:
Buy the 0th, 3rd, and 4th stocks for a total of 5 + 2 + 3 = 10.
Next year, sell all three stocks for a total of 8 + 3 + 5 = 16.
The profit you made is 16 - 10 = 6.
It can be shown that the maximum profit you can make is 6.
Example 2:

Input: present = [2,2,5], future = [3,4,10], budget = 6
Output: 5
Explanation: The only possible way to maximize your profit is to:
Buy the 2nd stock, and make a profit of 10 - 5 = 5.
It can be shown that the maximum profit you can make is 5.
Example 3:

Input: present = [3,3,12], future = [0,3,15], budget = 10
Output: 0
Explanation: One possible way to maximize your profit is to:
Buy the 1st stock, and make a profit of 3 - 3 = 0.
It can be shown that the maximum profit you can make is 0.


Constraints:

n == present.length == future.length
1 <= n <= 1000
0 <= present[i], future[i] <= 100
0 <= budget <= 1000
 */
public class MaximumProfitFromTradingStocks {

  /*
   * Approach: Bottom-Up 1D DP | Buy or Not Buy
   *
   * For each new stock, we can either buy or not buy, so for each budget, we check if we can buy or not buy.
   * This means that the outer for loop is the stock with the inner loop being the budget.
   * We will only buy a stock when doing so makes a profit.
   *
   * Let dp[i][j] be the maximum profit that can be made with budget j and 0 ~ ith stock.
   * Then, we have dp[i][j] = max(dp[i-1][j - present[i]] + future[i] - present[i], dp[i-1][j])
   * This dp only depends on the previous row, hence we have no need for 2D dp, let's write it in 1D &&
   * since j - present[i] is only ever smaller than j, we can loop it backward to get rid of the need to create a tmp 1D array.
   *
   * Time Complexity: O(budget * len of stock)
   */
  public int maximumProfit(int[] present, int[] future, int budget) {
    int[] dp = new int[budget+1];
    for (int i = 0; i < present.length; i++){
      for (int j = budget; j >= 0; j--){
        if (j >= present[i] && present[i] < future[i]){
          dp[j] = Math.max(dp[j], dp[j - present[i]] + future[i] - present[i]);
        }
      }
    }
    return dp[budget];
  }

}
