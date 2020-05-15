package leetcode.java;

// LC518. Coin Change 2
// https://leetcode.com/problems/coin-change-2/

public class LC518_Coin_Change_2 {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = 0; i < amount + 1; i++) {
                if (i - coin >= 0) {
                    dp[i] += dp[i - coin];
                }
            }
        }

        return dp[amount];
    }
}
