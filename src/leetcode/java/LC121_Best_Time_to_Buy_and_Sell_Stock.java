package leetcode.java;

// LC121. Best Time to Buy and Sell Stock
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/

// 算法加强
class LC121 {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int minPri = Integer.MAX_VALUE;
        int max = 0;

        for (int i = 0; i < len; i++) {
            if (prices[i] - minPri > max) {
                max = prices[i] - minPri;
            }
            minPri = Math.min(minPri, prices[i]);
        }
        return max;
    }
}

public class LC121_Best_Time_to_Buy_and_Sell_Stock {
    public int maxProfit(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice) {
                minprice = prices[i];
            } else if (prices[i] - minprice > maxprofit) {
                maxprofit = prices[i] - minprice;
            }
        }
        return maxprofit;
    }
}
