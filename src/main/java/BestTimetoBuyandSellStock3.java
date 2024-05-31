/*
 * Problem 123: Best Time to Buy and Sell Stock III
 * 
 * 
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 */
public class BestTimetoBuyandSellStock3 {

    public int maxProfit(int[] prices) {
        if (prices.length < 2)
            return 0;

        int[] left = new int[prices.length];
        int[] right = new int[prices.length];
        int left_min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            left_min = Math.min(left_min, prices[i]);
            left[i] = Math.max(prices[i] - left_min, left[i - 1]);
        }
        int right_max = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--) {
            right_max = Math.max(right_max, prices[i]);
            right[i] = Math.max(right_max - prices[i], right[i + 1]);
        }
        int m = 0;
        for (int i = 0; i < prices.length; i++) {
            m = Math.max(m, left[i] + right[i]);
        }

        return m;
    }
}
