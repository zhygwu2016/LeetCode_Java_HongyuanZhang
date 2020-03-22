package leetcode.java;

// LC123. Best Time to Buy and Sell Stock III
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/

public class LC123_Best_Time_to_Buy_and_Sell_Stock_III {
    public int maxProfit(int[] prices) {
        // buy1: 当前第一次买 max profit
        // sell1: 当前第一次卖 max profit
        // buy2: 当前第二次买 max profit
        // sell2: 当前第二次卖 max profit
        int buy1 = Integer.MIN_VALUE;
        int buy2 = Integer.MIN_VALUE;
        int sell1 = 0;
        int sell2 = 0;

        for (int price : prices) {
            buy2 = Math.max(buy2, sell1 - price); //max(不买；买)
            sell2 = Math.max(sell2, buy2 + price); //max(不卖；卖)

            buy1 = Math.max(buy1, -price);
            sell1 = Math.max(sell1, buy1 + price);
        }

        return Math.max(sell1, sell2); // 可以卖一次，也可以卖两次
    }
}
