package leetcode.java;

// LC714. Best Time to Buy and Sell Stock with Transaction Fee
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/

public class LC714_Best_Time_to_Buy_and_Sell_Stock_with_Transaction_Fee {
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0)  return 0;

        // buy/sell: 当前买/卖 max profit
        int buy = -prices[0];
        int sell = 0;

        for (int price : prices) {
            int nextBuy = Math.max(buy, sell - price); //不买 / 买
            int nextSell = Math.max(sell, buy + price - fee);

            buy = nextBuy;
            sell = nextSell;
        }

        return sell;
    }
}
