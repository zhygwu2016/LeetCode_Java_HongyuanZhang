package leetcode.java;

// LC486. Predict the Winner
// https://leetcode.com/problems/predict-the-winner/

// 左右拿球，自己的写法
public class LC486_Predict_the_Winner {
    public boolean PredictTheWinner(int[] nums) {
        if (nums == null || nums.length == 0)  throw new IllegalArgumentException();

        int len = nums.length;
        if (len == 1)  return true;
        // 原题中，二人拿的值相等也算先手赢
        // if (len == 2 && nums[0] == nums[1])  return false;
        if (len == 2) return true;

        // create an array of cumulative sum
        int[] sums = new int[len];
        sums[0] = nums[0];
        for (int i = 1; i < len; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }

        // 如果先手在[0, len - 1]取得的最大值不小于后手，则一定赢，返回true，反之false
        return winner(nums, sums, 0, len - 1)
                >= sums[len - 1] - winner(nums, sums, 0, len - 1);
    }

    private int winner(int[] nums, int[] sums, int start, int end) {
        int len = nums.length;
        // dp[i][j]: 先手在[i, j]中play的max value
        int[][] dp = new int[len][len];

        dp[len - 1][len - 1] = nums[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            for (int j = i; j <= len - 1; j++) {
                if (i == j) {
                    dp[i][j] = nums[i];
                } else {
                    int chooseLeft = nums[i] + cumulativeSum(nums, sums, i + 1, j) - dp[i + 1][j];
                    int chooseRight = nums[j] + cumulativeSum(nums, sums, i, j - 1) - dp[i][j - 1];
                    // 先手拿左边和拿右边时的最大值，取最优
                    dp[i][j] = Math.max(chooseLeft, chooseRight);
                }
            }
        }

        return dp[0][len - 1];
    }

    // O(1)求得nums在[i, j]的sum
    private int cumulativeSum(int[] nums, int[] sums, int start, int end) {
        if (start == 0)  return sums[end];
        if (start == end) return nums[start];
        return sums[end] - sums[start - 1];
    }
}
