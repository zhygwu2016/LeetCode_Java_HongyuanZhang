package leetcode.java;

// LC416. Partition Equal Subset Sum
// https://leetcode.com/problems/partition-equal-subset-sum/

// 01背包
// https://leetcode.com/problems/partition-equal-subset-sum/discuss/90592/01-knapsack-detailed-explanation
public class LC416_Partition_Equal_Subset_Sum {
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) return false;

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0)  return false;

        int target = sum / 2;

        int len = nums.length;
        // dp[n][target]: 前n个数，是否能组成和为target的组合
        boolean[][] dp = new boolean[len + 1][target + 1];

        dp[0][0] = true;
        for (int i = 1; i < len + 1; i++) {
            dp[i][0] = true;
        }
        for (int j = 1; j < target + 1; j++) {
            dp[0][j] = false;
        }

        for (int i = 1; i < len + 1; i++) {
            for (int j = 1; j < target + 1; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= nums[i - 1]) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        return dp[len][target];
    }
}
