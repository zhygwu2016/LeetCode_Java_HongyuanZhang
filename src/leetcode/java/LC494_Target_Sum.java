package leetcode.java;

// LC494. Target Sum
// https://leetcode.com/problems/target-sum/

public class LC494_Target_Sum {
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0)  return 0;
        int sum = 0;
        for (int num : nums) sum += num;
        if (S > sum || S < -sum) return 0;

        // dp[i][j]: 前i+1个元素 和为j-sum的路径有几个
        int[][] dp = new int[nums.length][2 * sum + 1];
        dp[0][sum + nums[0]] = 1;
        dp[0][sum - nums[0]] += 1;

        for (int i = 1; i < nums.length; i++) {
            for (int j = -sum; j <= sum; j++) {
                if (dp[i - 1][j + sum] > 0) {
                    dp[i][j + sum + nums[i]] += dp[i - 1][j + sum];
                    dp[i][j + sum - nums[i]] += dp[i - 1][j + sum];
                }
            }
        }

        return dp[nums.length - 1][S + sum];
    }
}
