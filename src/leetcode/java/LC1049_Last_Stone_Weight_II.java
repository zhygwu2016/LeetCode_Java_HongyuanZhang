package leetcode.java;

// LC1049. Last Stone Weight II
// https://leetcode.com/problems/last-stone-weight-ii/

// https://leetcode.com/problems/last-stone-weight-ii/discuss/295167/Java-beat-100-with-nice-explanation
/**
 This question eaquals to partition an array into 2 subsets whose difference is minimal
 (1) S1 + S2  = S
 (2) S1 - S2 = diff

 ==> -> diff = S - 2 * S2  ==> minimize diff equals to  maximize S2

 Now we should find the maximum of S2 , range from 0 to S / 2, using dp can solve this

 dp[i][j]   = {true if some subset from 1st to j'th has a sum equal to sum i, false otherwise}
 i ranges from (sum of all elements) {1..n}
 j ranges from  {1..n}

 same as 494. Target Sum

 */

public class LC1049_Last_Stone_Weight_II {
    public int lastStoneWeightII(int[] stones) {
        int S = 0, S2 = 0;
        for (int s : stones) S += s;
        int n = stones.length;
        boolean[][] dp = new boolean[S + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            dp[0][i] = true;
        }
        for (int i = 1; i <= n; i++) {
            for (int s = 1; s <= S / 2; s++) {
                if (dp[s][i - 1] || (s >= stones[i - 1] && dp[s - stones[i - 1]][i - 1])) {
                    dp[s][i] = true;
                    S2 = Math.max(S2, s);
                }
            }
        }
        return S - 2 * S2;
    }
}
