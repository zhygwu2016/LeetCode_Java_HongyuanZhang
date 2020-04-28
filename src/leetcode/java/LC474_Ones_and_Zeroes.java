package leetcode.java;

// LC474. Ones and Zeroes
// https://leetcode.com/problems/ones-and-zeroes/

import java.util.Arrays;

class LC474_Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int l = strs.length;
        int[][][] dp = new int[l+1][m+1][n+1];

        for (int i = 0; i < l+1; i++) {
            int[] nums = new int[]{0,0};
            if (i > 0) {
                nums = calculate(strs[i-1]);
            }
            for (int j = 0; j < m+1; j++) {
                for (int k = 0; k < n+1; k++) {
                    if (i == 0) {
                        dp[i][j][k] = 0;
                    } else if (j>=nums[0] && k>=nums[1]) {
                        dp[i][j][k] = Math.max(dp[i-1][j][k], dp[i-1][j-nums[0]][k-nums[1]]+1);
                    } else {
                        dp[i][j][k] = dp[i-1][j][k];
                    }
                }
            }
        }

        return dp[l][m][n];
    }

    private int[] calculate(String str) {
        int[] res = new int[2];
        Arrays.fill(res, 0);

        for (char ch : str.toCharArray()) {
            if (ch == '0') {
                res[0]++;
            } else if (ch == '1') {
                res[1]++;
            }
        }

        return res;
    }
}

public class LC474_Ones_and_Zeroes {
    public int findMaxForm(String[] strs, int m, int n) {
        // dp[i][j]: 用i个0，j个1可以组成strs中数字的最多个数
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) {
            Arrays.fill(dp[i], 0);
        }

        int[] count = new int[]{0, 0};
        for (String str : strs) {
            count = countZeroesAndOnes(str);
            for (int i = m; i >= count[0]; i--) {
                for (int j = n; j >= count[1]; j--) {
                    if (i >= count[0] && j >= count[1]) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - count[0]][j - count[1]] + 1);
                    } else {
                        dp[i][j] = dp[i][j];
                    }
                }
            }
        }

        return dp[m][n];
    }

    private int[] countZeroesAndOnes(String str) {
        int[] count = new int[2];
        for (int i = 0; i < str.length(); i++) {
            count[str.charAt(i) - '0']++;
        }
        return count;
    }
}
