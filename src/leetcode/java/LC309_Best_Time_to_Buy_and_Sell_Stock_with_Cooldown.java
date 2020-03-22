package leetcode.java;

// LC309. Best Time to Buy and Sell Stock with Cooldown
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/

import java.lang.management.MonitorInfo;

class LC309 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)  return 0;
        int len = prices.length;
        int[] buy = new int[len];
        int[] sell = new int[len];
        buy[0] = -prices[0];
        sell[0] = 0;

        for (int i = 1; i < len; i++) {
            buy[i] = Math.max(buy[i - 1], (i - 2 < 0 ? 0 : sell[i - 2]) - prices[i]); // 不买/买
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]); // 不卖/卖
        }
        return sell[len - 1];
    }
}

public class LC309_Best_Time_to_Buy_and_Sell_Stock_with_Cooldown {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)  return 0;
        int len = prices.length;
        int buy2 = -prices[0], sell2 = 0;
        int buy1 = -prices[0], sell1 = 0;

        // sell1 | sell2 | cSell2
        // buy1  | buy2  | cBuy2

        for (int i = 1; i < len; i++) {
            // 买/卖
            int cBuy2 = sell1 - prices[i];
            int cSell2 = Math.max(buy1, buy2) + prices[i];

            // 不买/不卖
            int cBuy1 = Math.max(buy1, buy2);
            int cSell1 = Math.max(sell1, sell2);

            buy2 = cBuy2;
            sell2 = cSell2;
            buy1 = cBuy1;
            sell1 = cSell1;
        }
        return Math.max(sell1, sell2);
    }
}
