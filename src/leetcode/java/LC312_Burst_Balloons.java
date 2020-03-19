package leetcode.java;

// LC312. Burst Balloons
// https://leetcode.com/problems/burst-balloons/

public class LC312_Burst_Balloons {
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int len = nums.length;
        // dp[i][j]: [i, j] max value
        // dp[i][j] = max(i <=k<=j){dp[i][k-1] + dp[k+1][j] + val[k]*val[i-1]*val[j+1]}
        int m[][] = new int[len][len];

        for (int start = len - 1; start >= 0; start--) {
            for (int end = start; end < len; end++) {
                int max = 0;
                for (int i = start; i <= end; i++) {
                    int val = 0;
                    val += (i == start ? 0 : m[start][i - 1]);
                    val += (i == end ? 0: m[i + 1][end]);

                    // Here is nums[start-1 / end+1], not nums[i-1/i+1],
                    // because this split is executed in the last step
                    val += (start == 0 ? 1 : nums[start - 1])
                            * nums[i]
                            * (end == (len - 1) ? 1 : nums[end + 1]);

                    max = Math.max(max, val);
                }
                m[start][end] = max;
            }
        }

        return m[0][len - 1];
    }
}
