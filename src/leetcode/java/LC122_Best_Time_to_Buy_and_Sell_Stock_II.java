package leetcode.java;

// LC122. Best Time to Buy and Sell Stock II
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/

// Greedy Solution
class LC122 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)  return 0;

        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }
}

class LC122_DP {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)  return 0;

        // buy/sell: 当前买/卖 max profit
        int buy = Integer.MIN_VALUE;
        int sell = 0;

        for (int price : prices) {
            int nextBuy = Math.max(buy, sell - price); //不买 / 买
            int nextSell = Math.max(sell, buy + price);

            buy = nextBuy;
            sell = nextSell;
        }

        return sell;
    }
}

public class LC122_Best_Time_to_Buy_and_Sell_Stock_II {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)  return 0;

        int i = 0;
        int valley = prices[0];
        int peak = prices[0];
        int maxprofit = 0;
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1])
                i++;
            valley = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1])
                i++;
            peak = prices[i];
            maxprofit += peak - valley;
        }
        return maxprofit;
    }
}


