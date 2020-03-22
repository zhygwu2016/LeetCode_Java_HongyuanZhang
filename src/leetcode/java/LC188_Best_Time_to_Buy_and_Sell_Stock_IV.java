package leetcode.java;

// LC188. Best Time to Buy and Sell Stock IV
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/

public class LC188_Best_Time_to_Buy_and_Sell_Stock_IV {
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (k >= len / 2) {
            int sum = 0;
            for (int i = 1; i < len; i++) {
                if (prices[i] - prices[i - 1] > 0) {
                    sum += prices[i] - prices[i - 1];
                }
            }
            return sum;
        }

        // buy[k][i]: [0, i]第k次买, max profit
        // sell[k][i]: [0, i]第k次卖, max profit
        int[][] sell = new int[k + 1][len];
        int[][] buy = new int[k + 1][len];
        for (int i = 1; i <= k; i++) {
            buy[i][0] = -prices[0];
            for (int j = 1; j < len; j++) {
                sell[i][j] = Math.max(sell[i][j - 1], buy[i][j - 1] + prices[j]);
                buy[i][j] = Math.max(buy[i][j - 1], sell[i - 1][j - 1] - prices[j]);
            }
        }

        return sell[k][len - 1];
    }
}
