/*
 * Problem 121: Best Time to Buy and Sell Stock
 * 
Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 */

public class BestTimetoBuyandSellStock {

    public int maxProfit(int[] prices) {
        if (prices.length < 1)
            return 0;
        int lowestBuyPrice = Integer.MAX_VALUE;
        int highestProfit = Integer.MIN_VALUE;
        int currentProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            lowestBuyPrice = Math.min(prices[i], lowestBuyPrice);
            currentProfit = prices[i] - lowestBuyPrice;
            highestProfit = Math.max(currentProfit, highestProfit);
        }
        
        return highestProfit;
    }
}
